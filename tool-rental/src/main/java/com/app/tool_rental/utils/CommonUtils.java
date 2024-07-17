package com.app.tool_rental.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CommonUtils {
    public static int countHolidays(LocalDate startDate, LocalDate endDate) {
        int startYear = startDate.getYear();
        int endYear = endDate.getYear();
        int holidayCount = 0;
        if (startYear == endYear) {
            LocalDate independenceDay = getIndependenceDay(startYear);
            LocalDate laborDay = getLaborDay(startYear);
            if (independenceDay != null) {
                holidayCount += ((startDate.isEqual(independenceDay) || startDate.isBefore(independenceDay)) && (endDate.isEqual(independenceDay) || endDate.isAfter(independenceDay))) ? 1 : 0;
            }
            if (laborDay != null) {
                holidayCount += ((startDate.isEqual(laborDay) || startDate.isBefore(laborDay)) && (endDate.isEqual(laborDay) || endDate.isAfter(laborDay))) ? 1 : 0;
            }
        } else {
            int yearDifference = endYear - startYear;
            LocalDate startYearIndependenceDay = getLaborDay(startYear);
            LocalDate endYearIndependenceDay = getLaborDay(endYear);
            LocalDate startYearLaborDate = getLaborDay(startYear);
            LocalDate endYearLaborDate = getLaborDay(endYear);
            if (startYearIndependenceDay != null) {
                if (startYearIndependenceDay.isAfter(startDate) || startYearIndependenceDay.isEqual(startDate)) {
                    holidayCount += 1;
                }
            }

            if (endYearIndependenceDay != null) {
                if (endYearIndependenceDay.isBefore(endDate) || endYearIndependenceDay.isEqual(endDate)) {
                    holidayCount += 1;
                }
            }
            if (startYearLaborDate != null) {
                if (startYearLaborDate.isAfter(startDate) || startYearLaborDate.isEqual(startDate)) {
                    holidayCount += 1;
                }
            }

            if (endYearLaborDate != null) {
                if (endYearLaborDate.isBefore(endDate) || endYearLaborDate.isEqual(endDate)) {
                    holidayCount += 1;
                }
            }

            return holidayCount + (yearDifference - 1);
        }

        return holidayCount;
    }

    public static LocalDate getIndependenceDay(int year) {
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return independenceDay.plusDays(1);
        } else {
            return independenceDay;
        }
    }

    public static LocalDate getLaborDay(int year) {
        LocalDate firstDayOfSeptember = LocalDate.of(year, 9, 1);
        LocalDate seventhDayOfSeptember = LocalDate.of(year, 9, 7);
        for (LocalDate current = firstDayOfSeptember; !current.isAfter(seventhDayOfSeptember); current = current.plusDays(1)) {
            if (current.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                return current;
            }
        }
        return null;// This statement is to just return if we get any exception in above code.
    }

    public static int countWeekends(LocalDate startDate, LocalDate endDate) {
        int weekends = 0;
        for (LocalDate current = startDate; !current.isAfter(endDate); current = current.plusDays(1)) {
            if (current.getDayOfWeek().equals(DayOfWeek.SATURDAY) || current.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                weekends++;
            }
        }
        return weekends;
    }
}
