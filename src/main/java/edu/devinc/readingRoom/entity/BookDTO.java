package edu.devinc.readingRoom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookDTO {

    private Integer bookId;
    private String author;
    private String title;
    private String publisher;
    private int year;
    private String translator;
    private String description;
    private boolean isFree;
    private String userName;
}
