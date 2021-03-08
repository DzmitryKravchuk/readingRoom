package edu.devinc.readingRoom;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SampleTest {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2021, Calendar.FEBRUARY , 2);
        Date orderDate = calendar.getTime();

        Date currentDate = calendar.getInstance().getTime();

        calendar.add(Calendar.DATE, +5);

        Date expiryDate = calendar.getTime();

        long diff = currentDate.getTime() - orderDate.getTime();

        System.out.println ("Days: " + diff / 1000 / 60 / 60 / 24);
        System.out.println ("new expiry date: " +expiryDate);




    }
}
