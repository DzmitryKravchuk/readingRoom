package edu.devinc.readingRoom;

import java.util.*;

public class SampleTest {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(2021, Calendar.FEBRUARY, 2);
        Date orderDate = calendar.getTime();
        List<Date> dateList = new ArrayList<>();

        Date currentDate = calendar.getInstance().getTime();

        calendar.add(Calendar.DATE, +5);

        Date expiryDate = calendar.getTime();

        long diff = currentDate.getTime() - orderDate.getTime();

        dateList.add(currentDate);
        dateList.add(orderDate);
        System.out.println(dateList);
        dateList.sort(Comparator.comparing(Date::getDate));

        System.out.println("Days: " + diff / 1000 / 60 / 60 / 24);
        System.out.println("new expiry date: " + expiryDate);

        System.out.println(dateList);
    }
}
