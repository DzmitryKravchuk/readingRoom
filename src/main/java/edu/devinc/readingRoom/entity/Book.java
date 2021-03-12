package edu.devinc.readingRoom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "year")
    private int year;

    @Column(name = "translator")
    private String translator;

    @Column(name = "description")
    private String description;

    @Column(name = "is_free")
    private boolean isFree;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Order> orders;
}
