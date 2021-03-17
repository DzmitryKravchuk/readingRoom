package edu.devinc.readingRoom;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@SpringBootTest
@Transactional
public class BookServiceTest {
    @Autowired
    private ApplicationContext context;

    @Test
    void reserveBook() {
        BookService bookService = context.getBean(BookService.class);
        OrderService orderService = context.getBean(OrderService.class);
        Book book = bookService.getById(6);
        System.out.println("Свободная книга из базы по id");
        System.out.println(book);
        Order order = new Order();
        order.setBook(book);
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        order.setDate(currentDate);
        order.setUserName("King Kong");
        orderService.save(order);

        System.out.println("Заказ на книгу");

        System.out.println(book.getOrders());
    }
}
