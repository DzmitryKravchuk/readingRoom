package edu.devinc.readingRoom.service.impl;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.repository.OrderRepository;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;
    @Autowired
    BookService bookService;

    @Override
    public void save(Order order) {
        repository.save(order);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public ResponseEntity<Order> deleteOrder(Integer bookId) {
        if (bookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Book book = this.bookService.getById(bookId);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // проверка что книга зарезервирована
        if (book.isFree()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Order order = getLastOrder(book);
        delete(order.getOrderId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @Override
    public void setBookReserved(Book book, String userName) {
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Order order = new Order();
        order.setBook(book);
        order.setUserName(userName);
        order.setDate(currentDate);
        save(order);
    }
}
