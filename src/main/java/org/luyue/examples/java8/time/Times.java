package org.luyue.examples.java8.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Years;

public class Times {

    private static final String datetimePattern = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {

        final String startDate = "2014-04-15 09:20:30";
        final String endDate = "2015-06-18 11:45:50";

        printTimeDiffJava8(startDate, endDate);

        printTimeDiffJoda(startDate, endDate);
    }

    public static void printTimeDiffJava8(final String start, final String end) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern(datetimePattern);

        // Parse datetime string to java.time.LocalDateTime instance
        LocalDateTime startDate = LocalDateTime.parse(start, format);
        LocalDateTime endDate = LocalDateTime.parse(end, format);

        System.out.println("Java 8 API - Time Difference ");
        System.out.println("in years: " + ChronoUnit.YEARS.between(startDate, endDate));
        System.out.println("in months: " + ChronoUnit.MONTHS.between(startDate, endDate));
        System.out.println("in days: " + ChronoUnit.DAYS.between(startDate, endDate));
        System.out.println("in hours: " + ChronoUnit.HOURS.between(startDate, endDate));
        System.out.println("in minutes: " + ChronoUnit.MINUTES.between(startDate, endDate));
        System.out.println("in seconds: " + ChronoUnit.SECONDS.between(startDate, endDate));
        System.out.println();
    }

    public static void printTimeDiffJoda(final String start, final String end) {

        SimpleDateFormat format = new SimpleDateFormat(datetimePattern);

        try {
            // Parse datetime string to org.joda.time.DateTime instance
            DateTime startDate = new DateTime(format.parse(start));
            DateTime endDate = new DateTime(format.parse(end));

            System.out.println("Joda Time API - Time Difference ");
            System.out.println("in years: " + Years.yearsBetween(startDate, endDate).getYears());
            System.out.println("in months: " + Months.monthsBetween(startDate, endDate).getMonths());
            System.out.println("in days: " + Days.daysBetween(startDate, endDate).getDays());
            System.out.println("in hours: " + Hours.hoursBetween(startDate, endDate).getHours());
            System.out.println("in minutes: " + Minutes.minutesBetween(startDate, endDate).getMinutes());
            System.out.println("in seconds: " + Seconds.secondsBetween(startDate, endDate).getSeconds());
            System.out.println();

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
