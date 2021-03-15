package edu.devinc.readingRoom.service.impl;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.repository.BookRepository;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import edu.devinc.readingRoom.service.util.DTOConverter;
import edu.devinc.readingRoom.service.util.OrderChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;
    @Autowired
    OrderChecker checker;
    @Autowired
    private DTOConverter converter;
    @Autowired
    private OrderService orderService;

    @Override
    public Book getById(Integer id) {
        Book book = repository.getOne(id);
        book.setFree(checker.ifBookIsFree(book));
        repository.save(book);
        return book;
    }

    @Override
    public ResponseEntity<BookDTO> getBookById(Integer id) {
        try {
            Book book = getById(id);
            BookDTO bookDTO = converter.convertToDTO(book);

            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        List<BookDTO> unReservedBooks = getUnreservedBooks();

        if (unReservedBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(unReservedBooks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = getAll();
        List<BookDTO> bookDTOList = books.stream().map(book -> converter.convertToDTO(book)).collect(Collectors.toList());

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookDTO> reserveBook(BookDTO bookDTO, String userName) {
        HttpHeaders headers = new HttpHeaders();

        // проверка введенного имени пользователя
        if (userName == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Проверка свободна ли книга
        Book book = getById(bookDTO.getBookId());
        if (!book.isFree()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        //Проверка количества книг, зарезервированных за 1 пользователем
        List<BookDTO> booksOf1User = getReservedBooks(userName);
        if (booksOf1User.size() == BookService.BOOK_COUNT_4_1_USER) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // добавление книги в заказ
        orderService.setBookReserved(book, userName);
        book = getById(book.getBookId());
        BookDTO bDTO = converter.convertToDTO(book);

        return new ResponseEntity<>(bDTO, headers, HttpStatus.CREATED);
    }

    private List<BookDTO> getReservedBooks(String userName) {
        List<Book> books = getAll();
        List<BookDTO> reservedBooks = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDto = converter.convertToDTO(book);
            if (!bookDto.isFree() && bookDto.getUserName().equals(userName)) {
                reservedBooks.add(bookDto);
            }
        }

        return reservedBooks;
    }

    private List<BookDTO> getUnreservedBooks() {
        List<Book> books = getAll();
        List<BookDTO> bookDTOList = books.stream().map(book -> converter.convertToDTO(book)).collect(Collectors.toList());
        List<BookDTO> unReservedBooks = bookDTOList.stream().filter(b -> b.isFree()).collect(Collectors.toList());
        return unReservedBooks;
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = repository.findAll();
        for (Book book : books) {
            book.setFree(checker.ifBookIsFree(book));
            repository.save(book);
        }
        return books;
    }
}
