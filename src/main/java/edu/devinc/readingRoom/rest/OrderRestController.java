package edu.devinc.readingRoom.rest;

import edu.devinc.readingRoom.entity.BookDTO;
import edu.devinc.readingRoom.entity.OrderDTO;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderRestController {
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;

    // резервирование книги
    @RequestMapping(value = "/reserve", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> reserveBook(@RequestBody OrderDTO orderDTO) {
        HttpHeaders headers = new HttpHeaders();
        orderService.reserveBook(orderDTO);
        return new ResponseEntity<>(orderDTO,headers, HttpStatus.OK);
    }
}
