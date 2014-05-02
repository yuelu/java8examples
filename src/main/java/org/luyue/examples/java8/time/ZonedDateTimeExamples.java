package org.luyue.examples.java8.time;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

import org.junit.Test;

/**
 * This class provide examples of how to work with local date and time, such as create instance, parse from string etc. <br/>
 * {@link OffsetDateTime} A date-time with an offset from UTC/Greenwich in the ISO-8601 calendar system, such as
 * 2007-12-03T10:15:30+01:00. <br/>
 * {@link OffsetTime} A time with an offset from UTC/Greenwich in the ISO-8601 calendar system, such as 10:15:30+01:00. <br/>
 * {@link ZonedDateTime} A date-time with a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30+01:00
 * Europe/Paris. <br/>
 * {@link ZoneId} A time-zone ID, such as Europe/Paris.<br/>
 * {@link ZoneOffset} A time-zone offset from Greenwich/UTC, such as +02:00.
 * 
 * @author jlu
 *
 */
public class ZonedDateTimeExamples {

    @Test
    public void testZonedDateTime() {

        LocalDateTime timePoint = LocalDateTime.parse("2014-04-28T21:45:50");

        ZoneId newyork = ZoneId.of("America/New_York");
        ZonedDateTime newYorkTime = ZonedDateTime.of(timePoint, newyork);
        assertThat(newYorkTime.toString(), is("2014-04-28T21:45:50-04:00[America/New_York]"));

        ZoneId paris = ZoneId.of("Europe/Paris");
        ZonedDateTime parisTime = ZonedDateTime.of(timePoint, paris);
        assertThat(parisTime.toString(), is("2014-04-28T21:45:50+02:00[Europe/Paris]"));

        assertTrue(parisTime.isBefore(newYorkTime));
    }

    @Test
    public void testZoneId() {

        // print all zone ids
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("Available Zone Ids(" + zoneIds.size() + ")...");
        System.out.println(zoneIds);

        // get system default zoneId
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();
        System.out.println("System default zone id: " + systemDefaultZoneId);

        // print zone id and display names
        ZoneId newyork = ZoneId.of("America/New_York");
        System.out.println("zone id:" + newyork.getId());
        System.out.println("display name in english:" + newyork.getDisplayName(TextStyle.FULL, Locale.US));
        System.out.println("display name in chinese:" + newyork.getDisplayName(TextStyle.FULL, Locale.CHINA));
    }

}
