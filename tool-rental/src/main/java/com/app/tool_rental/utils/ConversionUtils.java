package com.app.tool_rental.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConversionUtils {
    public static LocalDate from(String date) {
        String[] dateArray = date.split("/");
        return LocalDate.of(Integer.parseInt("20" + dateArray[2]), Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]));
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }
}
