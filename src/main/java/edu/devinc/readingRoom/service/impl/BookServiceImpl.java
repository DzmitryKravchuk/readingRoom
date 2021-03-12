package edu.devinc.readingRoom.service.impl;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.repository.BookRepository;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.util.OrderExpiryTermChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;
    @Autowired
    OrderExpiryTermChecker checker;

    @Override
    public Book getById(Integer id) {
        Book book = repository.getOne(id);
        book.setFree(checker.ifBookIsFree(book));
        repository.save(book);
        return book;
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = repository.findAll();
        for (Book book : books) {
            book.setFree(checker.ifBookIsFree(book));
            ;
            repository.save(book);
        }
        return books;
    }


}
