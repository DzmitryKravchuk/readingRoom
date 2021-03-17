package edu.devinc.readingRoom.rest;

import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.entity.OrderDTO;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import edu.devinc.readingRoom.service.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    // вывод информации о книге по ее id
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") Integer id) {
        BookDTO dto = bookService.getBookById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // вывод информации о всех свободных книгах
    @RequestMapping(value = "/free_books", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        List<BookDTO> bookDTOList = bookService.getFreeBooks();
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);

    }

    // просмотр всех книг
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOList = bookService.getAllBooks();
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    //  отмена резерва
    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Integer id) {

        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
