package at.kaismi;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateTimeApi {

    public static void main(String args[]) {
        System.out.println("Date time API 1:");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);

        println("Date time API 2:");
        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("Month: " + month + " day: " + day + " seconds: " + seconds);

        println("Date time API 3:");
        LocalDateTime date2 = currentTime.withDayOfMonth(1).withYear(2012);
        System.out.println("date2: " + date2);

        println("Date time API 4:");
        LocalDate date3 = LocalDate.of(2014, Month.SEPTEMBER, 12);
        System.out.println("date3: " + date3);

        println("Date time API 5:");
        LocalTime date4 = LocalTime.of(23, 15);
        System.out.println("date4: " + date4);
        //parse a string
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

        println("Date time API 6:");
        // Get the current date and time
        ZonedDateTime dateZoned = ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
        System.out.println("date1: " + dateZoned);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("CurrentZone: " + currentZone);

        println("Date time API 7:");
        //Get the current date
        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);

        //add 1 week to the current date
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week: " + nextWeek);
        //add 1 month to the current date
        LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + nextMonth);
        //add 1 year to the current date
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Next year: " + nextYear);
        //add 10 years to the current date
        LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
        System.out.println("Date after ten year: " + nextDecade);

        println("Date time API 8:");
        //Get the current date
        LocalDate date1Now = LocalDate.now();
        System.out.println("Current date: " + date1Now);

        //add 1 month to the current date
        LocalDate date2Now = date1.plus(1, ChronoUnit.MONTHS);
        System.out.println("Next month: " + date2Now);

        Period period = Period.between(date2Now, date1Now);
        System.out.println("Period: " + period);

        println("Date time API 9:");
        //Get the current date
        LocalDate date9 = LocalDate.now();
        System.out.println("Current date: " + date9);

        //get the next tuesday
        LocalDate nextTuesday = date9.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println("Next Tuesday on : " + nextTuesday);

        //get the second saturday of next month
        LocalDate firstInYear = LocalDate.of(date9.getYear(), date9.getMonth(), 1);
        LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("Second Saturday on : " + secondSaturday);

        println("Date time API 10:");
        //Get the current date
        Date currentDate = new Date();
        System.out.println("Current date: " + currentDate);

        //Get the instant of current date in terms of milliseconds
        Instant now = currentDate.toInstant();
        System.out.println("Instant now: " + now.getEpochSecond());
        ZoneId currentZoneTemp = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZoneTemp);
        System.out.println("Local date: " + localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, currentZoneTemp);
        System.out.println("Zoned date: " + zonedDateTime);

    }

    private static void println(String msg) {
        System.out.println("\n" + msg);
    }

}
