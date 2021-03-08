package edu.devinc.readingRoom.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String publisher;

    @Column
    private int year;

    @Column
    private String translator;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private Order order;

    private Date reservationExpiryDate;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getReservationExpiryDate() {
        return reservationExpiryDate;
    }

    public void setReservationExpiryDate(Date reservationExpiryDate) {
        this.reservationExpiryDate = reservationExpiryDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", translator='" + translator + '\'' +
                ", description='" + description + '\'' +
                ", order=" + order +
                ", reservationExpiryDate=" + reservationExpiryDate +
                '}';
    }
}
