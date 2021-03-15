package edu.devinc.readingRoom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "book_order")
@Getter
@Setter
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "date")
    Date date;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", userName='" + userName + '\'' +
                ", book=" + book.getBookId() +
                '}';
    }
}
