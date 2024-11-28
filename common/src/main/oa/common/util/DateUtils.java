package oa.common.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    DateUtils(){}
    /**
     * Returns the current date formatted according to the specified pattern
     * @param format the date format pattern
     * @return formatted date string
     */
    public static String getToday(String format) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Returns the current date in YYYYMMdd format
     * @return formatted date string
     */
    public static String getTodayDefault() {
        return getToday("YYYYMMdd");
    }

    /**
     * Returns the current time formatted according to the specified pattern
     * @param format the time format pattern
     * @return formatted time string
     */
    public static String getTime(String format) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Returns the current time in HH:mm:ss format
     * @return formatted time string
     */
    public static String getTimeDefault() {
        return getTime("HH:mm:ss");
    }
}
