package com.qtest.program.date;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {
    @Test
    public void showLastModifiedDateStringTest() {
        DateUtils dateUtils = new DateUtils();
        long lastModified = 1639319953512L;
        String dateString = dateUtils.showLastModifiedDateString(lastModified);
        assertEquals("2021-12-12 22:39:13.512", dateString);
    }
}
