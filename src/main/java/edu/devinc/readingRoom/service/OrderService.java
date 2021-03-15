package edu.devinc.readingRoom.service;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface OrderService {

    void save(Order order);

    void delete(Integer id);

    ResponseEntity<Order> deleteOrder(Integer bookId);

    Order getLastOrder(Book book);

    void setBookReserved (Book book, String userName);
}
