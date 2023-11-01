package calendarapp.app.utils;

import java.time.*;
import java.time.format.*;

public class DateUtility
{
    private static DateTimeFormatter dmyFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
    
    public static String getStrCurrentDateInDMYFormat()
    {
        return LocalDate.now().format(dmyFormat);
    }
    
    /**
     * Get next 7 days starting from the "startDate" that was passed.
     * These dates are displayed in the column of the calendar.
     */
    public static LocalDate[] getNextSevenDays(LocalDate startDate)
    {
        LocalDate[] columnDates = new LocalDate[7];
        
        LocalDate currentDate = startDate;
        
        // First add the start date, then get the next day after until all seven days are retrieved
        for(int i = 0; i < 7; i++)
        {
            columnDates[i] = currentDate;
            currentDate = currentDate.plusDays(1);
        }
        
        return columnDates;
    }


    // [ Formatting Date ]

    public static String formatDateToString(LocalDate date)
    {
        return date.format(dmyFormat);
    }
    
    /**
     * Formats the date to a string with the format "day-month-year"
     */
    public static String[] formatDateToDayMonthYear(LocalDate[] dates)
    {
        String[] strDates = new String[dates.length];

        for(int i = 0; i < dates.length; i++)
        {
            String formattedDate = dates[i].format(dmyFormat);
            strDates[i] = formattedDate;
        }

        return strDates;
    }
    
    /**
     * String Date must be in the format "yyyy-mm-dd", with all values to be a number
     */
    public static LocalDate stringToDate(String dateStr)
    {
        LocalDate localDate = null;
        try
        {
            String[] dates = dateStr.split("-");
            int year = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[2]);
            localDate = LocalDate.of(year, month, day);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error: Date is not a number. Date must be in the format 'yyyy-mm-dd'.");
        }
        return localDate;
    }
    
    public static LocalDate newStringToDate(String dateStr)
    {
        LocalDate parsedDate = null;
        try
        {
            parsedDate = LocalDate.parse(dateStr);
        }
        catch(DateTimeParseException e)
        {
            System.out.println("Error: Date must be in the format 'yyyy-mm-dd' and not " + 
                "'"+dateStr+"'");
        }
        return parsedDate;
    }
    
    
    // [ Time functions ]

    /**
     * Time string must be in the format "hr:min:sec", with all values to be numeric
     */
    public static LocalTime stringToTime(String timeStr)
    {
        LocalTime localTime = null;
        try
        {
            String[] times = timeStr.split(":");
            int hr = Integer.parseInt(times[0]);
            int min = Integer.parseInt(times[1]);
            int sec = Integer.parseInt(times[2]);
            localTime = LocalTime.of(hr, min, sec);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error: Time is not a number. " +
                "Time must be in the format 'hr:min:sec'");
        }
        return localTime;
    }
}
