package edu.devinc.readingRoom.service;

import edu.devinc.readingRoom.entity.Book;

import java.util.List;

public interface BookService {
    int EXPIRY_TERM = 5; // количество дней на которое может быть зарезервирована книга

    Book getById(Integer id);

    void save(Book book);

    void delete(Integer id);

    List<Book> getAll();

}
