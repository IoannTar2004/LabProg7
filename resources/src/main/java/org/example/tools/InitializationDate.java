package org.example.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for initializing date when program starts
 */
public class InitializationDate {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
    private static String date;
    static {
        date = formatter.format(new Date());
    }
    /**
     * Forms a normal view of initialization date.
     * @return formed date
     */

    public static String getDate() {
        return date;
    }
}
