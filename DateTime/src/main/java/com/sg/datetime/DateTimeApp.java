/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Minul
 */
public class DateTimeApp {

    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        System.out.println(ld);

        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);

        ld = LocalDate.parse("02/07/2010", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);

        String isoDate = ld.toString();
        System.out.println(isoDate);

        ld = LocalDate.parse(isoDate);
        System.out.println(ld);

        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);

        formatted = ld.format(DateTimeFormatter.ofPattern("MM==dd==yyyy+_+_+_"));
        System.out.println(formatted);

        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        System.out.println(formatted);

        // Take a date time and add/substract/count future and past dates
        LocalDate past = ld.minusDays(8);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        past = ld.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);

        Period difference = ld.until(past);
        System.out.println(difference);
        System.out.println(difference.getMonths());
        difference = past.until(ld);
        System.out.println(difference.getMonths()); // positive difference

        // Local datetimes and convert b/w mdoern API adn legacy
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        formatted = ldt.format(DateTimeFormatter.ofPattern("yyy-MMM-dd hh:mm:ss"));
        System.out.println(formatted);

        // OLD API 
        Date legacyDate = new Date();
        System.out.println(legacyDate);

        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);

        // Zone Date Time (taking legacy date and turning it into a uinit of time instant)
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);

        zdt = legacyCalendar.toZonedDateTime();
        ld = zdt.toLocalDate();
        System.out.println(ld);
    }
}
