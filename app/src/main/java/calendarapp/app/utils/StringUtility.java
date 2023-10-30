package calendarapp.app.utils;

import java.util.*;
import java.time.*;

public class StringUtility
{
    /**
     * Removes first and last character from a String. Exclude 0th and str.length() char.
     */
    public static String removeFirstAndLastChar(String str)
    {
        return str.substring(1, str.length()-1);
    }
    
    /**
     * Parse the date string into LocalDate object, assuming the string is in the form
     * of example: "2023-11-01"
     */
    public static LocalDate stringToDate(String dateStr) throws DateTimeException
    {
        String[] dates = dateStr.split("-");
        if(dates.length != 3)
        {
            throw new IllegalArgumentException("Date must be in the form 'yyyy-mm-dd', including dash");
        }

        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);

        return LocalDate.of(year, month, day);
    }
    
    public static LocalTime stringToTime(String timeStr) throws DateTimeException
    {
        String[] times = timeStr.split(":");
        if(times.length != 3)
        {
            throw new IllegalArgumentException("Time must be in the form 'hour:mins:sec', including colon");
        }

        int hour = Integer.parseInt(times[0]);
        int time = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);

        return LocalTime.of(hour, time, second);
    }
}
