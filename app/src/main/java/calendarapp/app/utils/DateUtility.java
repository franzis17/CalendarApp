package calendarapp.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtility
{
    private static DateTimeFormatter dmyFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
    
    public static String getStrCurrentDate_dmyFormat()
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
}
