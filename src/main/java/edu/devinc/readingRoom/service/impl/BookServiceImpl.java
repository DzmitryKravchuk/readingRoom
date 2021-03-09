package edu.devinc.readingRoom.service.impl;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
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
        checkOrderExpiryTerm(book);

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
            checkOrderExpiryTerm(book);
        }
        return books;
    }


    private void checkOrderExpiryTerm(Book book) {
        Order lastOrder = getLastOrder(book);
        if (lastOrder == null) {
            book.setFree(true);
        } else book.setFree(getDaysPassedLastOrder(lastOrder) > BookService.EXPIRY_TERM);


        //           calendar.setTime(book.getOrder().getDate());
        //           calendar.add(Calendar.DATE, +BookService.EXPIRY_TERM);
        //          book.setReservationExpiryDate(calendar.getTime());
        //       }
        repository.save(book);
    }

    private int getDaysPassedLastOrder(Order lastOrder) {
        Calendar calendar = new GregorianCalendar();
        Date currentDate = calendar.getInstance().getTime();
        long diff = currentDate.getTime() - lastOrder.getDate().getTime();
        int daysPassed = (int) (diff / 1000 / 60 / 60 / 24);
        return daysPassed;
    }

    @Override
    public Order getLastOrder(Book book) {
        List<Order> orders=book.getOrders();
        if (orders.size() > 0) {
            orders.sort(Comparator.comparing(Order::getDate));
            Order lastOrder = orders.get(orders.size() - 1);
            return lastOrder;
        } else {
            return null;
        }
    }


}
