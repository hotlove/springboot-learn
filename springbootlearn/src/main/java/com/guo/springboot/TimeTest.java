package com.guo.springboot;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeTest {

    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // LocalDateTime fromDate= LocalDateTime.now();
// LocalDateTime toDate= LocalDateTime.now();
        LocalDateTime t = LocalDateTime.parse("2019-11-12 10:30", dateTimeFormatter);
        Date datet = toDate(t);

        List<Date> dateList = new ArrayList<>(4);
        LocalDateTime f1 = LocalDateTime.parse("2019-11-12 09:00", dateTimeFormatter);
        dateList.add(TimeTest.toDate(f1));

        LocalDateTime f2 = LocalDateTime.parse("2019-11-12 09:30", dateTimeFormatter);
        dateList.add(TimeTest.toDate(f2));

        LocalDateTime f3 = LocalDateTime.parse("2019-11-12 10:00", dateTimeFormatter);
        dateList.add(TimeTest.toDate(f3));

        LocalDateTime f4 = LocalDateTime.parse("2019-11-12 10:30", dateTimeFormatter);
        dateList.add(TimeTest.toDate(f4));

        System.out.println(dateList.contains(datet));


        LocalDateTime f = LocalDateTime.parse("2019-11-12 09:00", dateTimeFormatter);
        LocalDateTime s = LocalDateTime.parse("2019-11-12 09:20", dateTimeFormatter);

        LocalDateTime e = f.plusMinutes(80);
        System.out.println(e.toString());
        System.out.println(e.minusMinutes(80).toString());

        ZoneId zone = ZoneId.systemDefault();
        Instant fromDate = f.atZone(zone).toInstant();
        Instant toDate = s.atZone(zone).toInstant();

//        Instant fromDate = f.toInstant(ZoneOffset.UTC);
//        Instant toDate = s.toInstant(ZoneOffset.UTC);

        long minutes = ChronoUnit.MINUTES.between(fromDate, toDate);

        System.out.println(minutes);

        int gapMin = (s.getMinute()- f.getMinute());
        System.out.println(gapMin);
        long factor = (minutes / 30);

        System.out.println(factor);
    }
}
