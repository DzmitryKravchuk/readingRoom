package edu.devinc.readingRoom.service.util;

import edu.devinc.readingRoom.entity.Book;
import edu.devinc.readingRoom.entity.Order;
import edu.devinc.readingRoom.service.BookService;
import edu.devinc.readingRoom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Component
public class OrderExpiryTermChecker {
    @Autowired
    OrderService orderService;

    public boolean ifBookIsFree(Book book) {
        Order lastOrder = orderService.getLastOrder(book);
        if (lastOrder == null) {
            return true;
        }
        return (getDaysPassedLastOrder(lastOrder) > BookService.EXPIRY_TERM);
    }

    private int getDaysPassedLastOrder(Order lastOrder) {
        Calendar calendar = new GregorianCalendar();
        Date currentDate = calendar.getInstance().getTime();
        long diff = currentDate.getTime() - lastOrder.getDate().getTime();
        int daysPassed = (int) (diff / 1000 / 60 / 60 / 24);
        return daysPassed;
    }
}
