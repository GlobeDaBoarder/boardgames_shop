package ua.rivnegray.reservations.timeslot.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

public class SortByDateString implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter
                .ofPattern("yyyy-MM-dd", Locale.ENGLISH);

        LocalDate dateA = LocalDate.parse(a, dateTimeFormat);
        LocalDate dateB = LocalDate.parse(b, dateTimeFormat);

        return dateA.compareTo(dateB);
    }
}
