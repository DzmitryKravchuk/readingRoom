package edu.devinc.readingRoom.service.impl;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.exception.MyException;
import edu.devinc.readingRoom.repository.BookRepository;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import edu.devinc.readingRoom.service.util.DTOConverter;
import edu.devinc.readingRoom.service.util.OrderChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Book book = repository.findById(id).orElse(null);
        if (book == null) {
            throw new MyException("Book with index " + id + " not found");
        }
        book.setFree(checker.ifBookIsFree(book));
        return book;
    }

    @Override
    public BookDTO getBookById(Integer id) {
        Book book = getById(id);
        BookDTO bookDTO = converter.convertToDTO(book);
        return bookDTO;
    }

    @Override
    public List<BookDTO> getFreeBooks() {
        List<BookDTO> unReservedBooks = getUnreservedBooks();
        if (unReservedBooks.isEmpty()) {
            throw new MyException("No free books left");
        }
        return unReservedBooks;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = getAll();
        List<BookDTO> bookDTOList = books.stream().map(book -> converter.convertToDTO(book)).collect(Collectors.toList());
        if (books.isEmpty()) {
            throw new MyException("No books found");
        }
        return bookDTOList;
    }

    @Override
    public List<BookDTO> getReservedBooks(String userName) {
        List<Book> books = getAll();
        List<BookDTO> reservedBooks = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDto = converter.convertToDTO(book);
            if (!bookDto.isFree() && orderService.getLastOrder(book).getUserName().equals(userName)) {
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
        }
        return books;
    }
}
