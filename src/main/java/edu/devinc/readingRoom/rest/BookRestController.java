package edu.devinc.readingRoom.rest;

import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import edu.devinc.readingRoom.service.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        return bookService.getBookById(id);
    }

    // резервирование книги
    @RequestMapping(value = "/reserve&user_name={name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> reserveBook(@RequestBody BookDTO bookDTO, @PathVariable("name") String userName) {
        return bookService.reserveBook(bookDTO, userName);
    }

    // вывод информации о всех свободных книгах
    @RequestMapping(value = "/free_books", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        return bookService.getFreeBooks();
    }

    // просмотр всех книг
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return bookService.getAllBooks();
    }

    //  отмена резерва
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Integer id) {
        return orderService.deleteOrder(id);
    }
}
