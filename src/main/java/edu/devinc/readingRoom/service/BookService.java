package edu.devinc.readingRoom.service;

import edu.devinc.readingRoom.entity.Book;

import java.util.List;

public interface BookService {
    int EXPIRY_TERM = 5; // количество дней на которое может быть зарезервирована книга
    int BOOK_COUNT_4_1_USER = 5; // количество книг в одном заказе

    Book getById(Integer id);

    void save(Book book);

    List<Book> getAll();
}
