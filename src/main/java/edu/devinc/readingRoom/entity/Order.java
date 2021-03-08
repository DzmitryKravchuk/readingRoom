package edu.devinc.readingRoom.entity;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Order")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column
    Date date;

    @Column
    private String userName;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Book> books;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", userName='" + userName + '\'' +
                ", books=" + books +
                '}';
    }
}
