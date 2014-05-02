package org.luyue.examples.java8.time;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;

import org.junit.Test;

/**
 * This class provide examples of how to work with local date and time, such as create instance, parse from string etc. <br/>
 * {@link Clock} A clock providing access to the current instant, date and time using a time-zone. <br/>
 * {@link Duration} A time-based amount of time, such as '34.5 seconds'. <br/>
 * {@link Period} A date-based amount of time in the ISO-8601 calendar system, such as '2 years, 3 months and 4 days'.<br/>
 * {@link Instant} An instantaneous point on the time-line.<br/>
 * 
 * 
 * @author jlu
 *
 */
public class TimeUtilExamples {

    @Test
    public void testClock() {

        // Get current system time in milliseconds, Java8
        System.out.println(Clock.systemUTC().millis());

        // Get current system time in milliseconds, pre-Java8
        System.out.println(System.currentTimeMillis());

        // Get current time in Europe/Paris
        Clock parisClock = Clock.system(ZoneId.of("Europe/Paris"));
        System.out.println(LocalDateTime.now(parisClock));
    }

    @Test
    public void testDuration_PlusMinusDuration() {

        // 1 hour, 5 mins, 5 secs
        Duration duration = Duration.parse("PT1H5M5S");

        LocalDateTime dateTime = LocalDateTime.parse("2014-04-30T21:25:10");
        // minus dateTime with the duration to get a passed time
        assertThat(dateTime.minus(duration).toString(), is("2014-04-30T20:20:05"));
        // plus dateTime with the duration to get a future time
        assertThat(dateTime.plus(duration).toString(), is("2014-04-30T22:30:15"));
    }

    @Test
    public void testDuration_CalculateDatesDiff() {

        LocalDateTime startInclusive = LocalDateTime.parse("2014-04-30T21:25:00");
        LocalDateTime endExclusive = LocalDateTime.parse("2014-05-02T22:30:05");

        Duration duration = Duration.between(startInclusive, endExclusive);

        // 2 days, 1 hour, 5 mins, 5 secs
        assertThat(duration.toDays(), is(2L));
        assertThat(duration.toHours(), is(2 * 24 + 1L));
        assertThat(duration.toMinutes(), is(duration.toHours() * 60 + 5L));
        assertThat(duration.getSeconds(), is(duration.toMinutes() * 60 + 5L));
        assertThat(duration.toMillis(), is((duration.toMinutes() * 60 + 5) * 1000L));
    }

    @Test
    public void testPeriod_PlusMinusPeriod() {

        Period period = Period.of(1, 2, 3);
        LocalDate date = LocalDate.of(2014, 4, 30);
        // minus date with the period to get a passed time
        assertThat(date.minus(period).toString(), is("2013-02-25"));
        // plus date with the period to get a future time
        assertThat(date.plus(period).toString(), is("2015-07-03"));
    }

    @Test
    public void testPeriod_CalculateDatesDiff() {

        LocalDate startDateInclusive = LocalDate.parse("2014-04-30");
        LocalDate endDateExclusive = LocalDate.parse("2015-06-02");

        Period period = Period.between(startDateInclusive, endDateExclusive);

        assertThat(period.getYears(), is(1));
        assertThat(period.getMonths(), is(1));
        assertThat(period.getDays(), is(3));
        assertThat(period.toTotalMonths(), is(13L));
    }
}
