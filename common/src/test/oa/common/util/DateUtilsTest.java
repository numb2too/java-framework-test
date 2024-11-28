package oa.common.util;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtilsTest {

    private static final Logger logger = Logger.getLogger(DateUtils.class.getName());

    @Test
    public void testGetToday() {
        // Given
        String format = "YYYYMMdd";

        // When
        String result = DateUtils.getToday(format);
        logger.log(Level.INFO, "result: {0}", result);

        // Then
        String expected = LocalDate.now().format(DateTimeFormatter.ofPattern(format));
        assertEquals(expected, result);
    }

    @Test
    public void testGetTodayWithDifferentFormat() {
        // Given
        String format = "dd-MM-YYYY";

        // When
        String result = DateUtils.getToday(format);

        logger.log(Level.INFO, "result: {0}", result);
        // Then
        String expected = LocalDate.now().format(DateTimeFormatter.ofPattern(format));
        assertEquals(expected, result);
    }

    @Test
    public void testGetTodayDefault() {
        // Given
        String expectedFormat = "YYYYMMdd";

        // When
        String result = DateUtils.getTodayDefault();
        logger.log(Level.INFO, "result: {0}", result);


        // Then
        String expected = LocalDate.now().format(DateTimeFormatter.ofPattern(expectedFormat));
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTodayWithInvalidFormat() {
        // When/Then
        DateUtils.getToday("invalid-format");
    }

    @Test
    public void testGetTime() {
        // Given
        String format = "HH:mm:ss";

        // When
        String result = DateUtils.getTime(format);
        logger.log(Level.INFO, "result: {0}", result);

        // Then
        String expected = LocalTime.now().format(DateTimeFormatter.ofPattern(format));
        assertEquals(expected, result);
    }

    @Test
    public void testGetTimeWithDifferentFormat() {
        // Given
        String format = "h:mm a";  // 12-hour format with AM/PM

        // When
        String result = DateUtils.getTime(format);
        logger.log(Level.INFO, "result: {0}", result);

        // Then
        String expected = LocalTime.now().format(DateTimeFormatter.ofPattern(format));
        assertEquals(expected, result);  // Use direct comparison instead of regex
    }

    @Test
    public void testGetTimeDefault() {
        // When
        String result = DateUtils.getTimeDefault();
        logger.log(Level.INFO, "result: {0}", result);

        // Then
        String expected = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTimeWithInvalidFormat() {
        // When/Then
        DateUtils.getTime("invalid-format");
    }
}