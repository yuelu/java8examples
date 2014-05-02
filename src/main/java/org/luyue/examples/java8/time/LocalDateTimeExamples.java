package org.luyue.examples.java8.time;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import org.junit.Test;

/**
 * This class provide examples of how to work with local date and time, such as create instance, parse from string etc.
 * {@link LocalDate} A date without a time-zone in the ISO-8601 calendar system, such as 2007-12-03. <br/>
 * {@link LocalTime} A time without time-zone in the ISO-8601 calendar system, such as 10:15:30. <br/>
 * {@link LocalDateTime} A date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30.
 * 
 * @author jlu
 *
 */
public class LocalDateTimeExamples {

    @Test
    public void testCreateInstanceByOf() {

        final String expectedTimePoint = "2014-04-28T21:45:50";

        // Create by setting year(MIN_YEAR,MAX_YEAR), month(1-12), day(1-31), hour(0-23), minute(0-59), second(0-59).
        LocalDateTime timePoint = LocalDateTime.of(2014, 4, 28, 21, 45, 50);
        assertThat("toString() should print object in ISO-8601 format uuuu-MM-dd'T'HH:mm:ss", timePoint.toString(),
                is(expectedTimePoint));

        // If the value of any field is out of range, DateTimeException will be thrown
        try {
            LocalDateTime.of(2014, 2, 30, 21, 45, 50);
            fail("Should fail because there's no 30th day of the Feb");
        } catch (DateTimeException e) {
            System.err.println(e);
        }

        // Calendar is not doing validation of the field range
        Calendar cal = Calendar.getInstance();
        // month is 0 based in Calendar, this setting means 2014-02-30 and since this is an invalid date, calendar will
        // automatically convert to 2014-03-02 (i.e. two days after Feb 28)
        cal.set(2014, 1, 30);
        assertThat(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()), is("2014-03-02"));
    }

    @Test
    public void testCreateInstanceByParse() {

        final String expectedTimePoint = "2014-04-28T21:45:50";
        assertThat(LocalDateTime.parse(expectedTimePoint).toString(), is(expectedTimePoint));

        try {
            LocalDateTime.parse("2014-04-30");
            fail("2014-04-30 can't be parsed to LocalDateTime instance, you should at least provide hour and minutes!");
        } catch (DateTimeParseException e) {
            System.err.println(e);
        }
    }

    @Test
    public void testTruncation() {
        // truncate to minutes
        LocalDateTime timePoint = LocalDateTime.parse("2014-04-28T21:45:50").truncatedTo(ChronoUnit.MINUTES);
        assertThat(timePoint.toString(), is("2014-04-28T21:45"));
    }
}
