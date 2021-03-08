package edu.devinc.readingRoom.service.impl;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.repository.BookRepository;
import edu.devinc.readingRoom.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;

    @Override
    public Book getById(Integer id) {
        Book book = repository.getOne(id);
        if (book.getOrder() != null) {
            checkOrderExpiryTerm(book);
        }
        return book;
    }

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = repository.findAll();
        for (Book book : books) {
            if (book.getOrder() != null) {
                checkOrderExpiryTerm(book);
            }
        }
        return books;
    }

    private void checkOrderExpiryTerm(Book book) {
        Calendar calendar = new GregorianCalendar();
        Date currentDate = calendar.getInstance().getTime();
        long diff = currentDate.getTime() - book.getOrder().getDate().getTime();
        int daysPassed = (int) (diff / 1000 / 60 / 60 / 24);
        if (daysPassed > BookService.EXPIRY_TERM) {
            book.setOrder(null);

        } else {
            calendar.setTime(book.getOrder().getDate());
            calendar.add(Calendar.DATE, +BookService.EXPIRY_TERM);
            book.setReservationExpiryDate(calendar.getTime());
        }
        repository.save(book);
    }


}
