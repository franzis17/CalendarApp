package calendarapp.app;

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
     * Get 7 days starting from the current date.
     * These dates are displayed in the column of the calendar.
     */
    public static LocalDate[] getNextSevenDays()
    {
        LocalDate[] columnDates = new LocalDate[7];
        
        // Starting from the currentDate, get the next seven days to display as column.
        LocalDate currentDate = LocalDate.now();
        
        // First add the current date, then get the next day after until all seven days are retrieved
        for(int i = 0; i < 7; i++)
        {
            columnDates[i] = currentDate;
            currentDate = currentDate.plusDays(1);
        }
        
        // [LOG - display each date]
        System.out.println("+++ Displaying the seven dates:");
        int date_i = 1;
        for(LocalDate date : columnDates)
        {
            System.out.println(date_i + ". Date = " + date);
            date_i++;
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
