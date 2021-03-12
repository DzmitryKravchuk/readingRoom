package edu.devinc.readingRoom.service.impl;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.repository.OrderRepository;
import edu.devinc.readingRoom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;

    @Override
    public Order getById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public void save(Order order) {
        repository.save(order);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order getLastOrder(Book book) {
        List<Order> orders = book.getOrders();
        if (orders.size() > 0) {
            orders.sort(Comparator.comparing(Order::getDate));
            Order lastOrder = orders.get(orders.size() - 1);
            return lastOrder;
        } else {
            return null;
        }
    }
}
