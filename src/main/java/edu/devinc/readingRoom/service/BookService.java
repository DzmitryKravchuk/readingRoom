package edu.devinc.readingRoom.service;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.BookDTO;

import java.util.List;

public interface BookService {
    int EXPIRY_TERM = 5; // количество дней на которое может быть зарезервирована книга

    Book getById(Integer id);

    BookDTO getBookById(Integer id);

    List<BookDTO> getFreeBooks();

    List<BookDTO> getAllBooks();

    void save(Book book);

    List<Book> getAll();

    List<BookDTO> getReservedBooks(String userName);
}
