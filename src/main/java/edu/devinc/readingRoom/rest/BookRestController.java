package edu.devinc.readingRoom.rest;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import edu.devinc.readingRoom.service.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BookRestController {

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DTOConverter converter;

    // вывод информации о книге по ее id
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book book = this.bookService.getById(id);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookDTO bookDTO = converter.convertToDTO(book);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    // резервирование книги
    @RequestMapping(value = "/reserve&user_name={name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> reserveBook(@RequestBody BookDTO bookDTO, @PathVariable("name") String userName) {
        HttpHeaders headers = new HttpHeaders();

        // проверка введенного имени пользователя
        if (userName == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //Проверка вободна ли книга
        Book book = bookService.getById(bookDTO.getBookId());
        if (!book.isFree()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        //Проверка количества книг, зарезервированных за 1 пользователем
        List<BookDTO> reservedBooks = getReservedBooks();
        List<BookDTO> booksOf1User = reservedBooks.stream().filter(b -> b.getUserName().equals(userName)).collect(Collectors.toList());
        if (booksOf1User.size() == BookService.BOOK_COUNT_4_1_USER) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // добавление книги в заказ
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Order order = new Order();
        order.setBook(book);
        order.setUserName(userName);
        order.setDate(currentDate);
        this.orderService.save(order);
        book = this.bookService.getById(bookDTO.getBookId());
        BookDTO bDTO = converter.convertToDTO(book);

        return new ResponseEntity<>(bDTO, headers, HttpStatus.CREATED);
    }

    private List<BookDTO> getReservedBooks() {
        List<Book> books = bookService.getAll();
        List<BookDTO> reservedBooks = new ArrayList<>();
        for (Book b : books) {
            if (!b.isFree()) {
                BookDTO dto = converter.convertToDTO(b);
                reservedBooks.add(dto);
            }
        }
        return reservedBooks;
    }

    // вывод информации о всех свободных книгах
    @RequestMapping(value = "/free_books", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        List<BookDTO> unReservedBooks = getUnreservedBooks();

        if (unReservedBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(unReservedBooks, HttpStatus.OK);
    }

    private List<BookDTO> getUnreservedBooks() {
        List<Book> books = bookService.getAll();
        List<BookDTO> unReservedBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.isFree()) {
                BookDTO dto = converter.convertToDTO(b);
                unReservedBooks.add(dto);
            }
        }
        return unReservedBooks;
    }

    // просмотр всех книг
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = this.bookService.getAll();
        List<BookDTO> bookDTOList = books.stream().map(book -> converter.convertToDTO(book)).collect(Collectors.toList());

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    //  отмена резерва
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Integer id) {

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book book = this.bookService.getById(id);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // проверка что книга зарезервирована
        if (book.isFree()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        Order order = this.orderService.getLastOrder(book);
        this.orderService.delete(order.getOrderId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
