package edu.devinc.readingRoom.service;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;

import java.util.List;

public interface OrderService {


    Order getById(Integer id);

    void save(Order order);

    void delete(Integer id);

    List<Order> getAll();

    Order getLastOrder(Book book);

}
