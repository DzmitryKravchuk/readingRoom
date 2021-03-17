package edu.devinc.readingRoom.service;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.entity.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    int MAX_BOOK_COUNT = 5; // количество книг для одного клиента

    void save(Order order);

    void delete(Integer id);

    void deleteOrder(Integer bookId);

    Order getLastOrder(Book book);

    void reserveBook(OrderDTO orderDTO);

    void setBookReserved (Book book, String userName);
}
