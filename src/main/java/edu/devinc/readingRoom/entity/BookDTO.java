package edu.devinc.readingRoom.entity;

import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class BookDTO {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;


    private Integer bookId;
    private String author;
    private String title;
    private String publisher;
    private int year;
    private String translator;
    private String description;
    private boolean isFree;
    private String userName;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
