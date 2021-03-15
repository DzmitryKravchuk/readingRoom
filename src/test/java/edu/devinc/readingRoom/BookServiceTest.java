package edu.devinc.readingRoom;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BookServiceTest {
    @Autowired
    private ApplicationContext context;

    @Test
    void reserveBook() {
        BookService bookService = context.getBean(BookService.class);
        OrderService orderService = context.getBean(OrderService.class);
        Book book = bookService.getById(1);
        System.out.println("Свободная книга из базы по id");
        System.out.println(book);
        orderService.setBookReserved(book, "Пушкин");
        System.out.println("Зарезервированная книга из базы по id");
        book = bookService.getById(1);
        System.out.println(book);
    }


}
