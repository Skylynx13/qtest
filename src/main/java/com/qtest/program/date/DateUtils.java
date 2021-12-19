package com.qtest.program.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public String showLastModifiedDateString(long lastModified) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(lastModified));
    }
}
