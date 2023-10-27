package edu.curtin.calplugins;

// package dependencies
import calendarapp.api.PluginsAPI;

// external dependencies
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Can add either an All-Day Event or an Hourly Event and repeat the event for every "n" days
 * until a year after startDate.
 */
public class RepeatPlugin implements PluginsAPI
{
    private final static int ONE_YEAR = 365;
    
    private CalendarAPI calendarAPI;

    @Override
    public void start(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
    

    // [ MainApp function calls ]
    
    public void addAllDayEvent(LocalDate date, String title, int repeatDays)
    {
        // Add the very first event
        calendarAPI.addAllDayEvent(date, title);

        // Add the next date, depending on how many days provided, do this for a year
        int nextDay = repeatDays;
        LocalDate newDate = date;
        while(nextDay < ONE_YEAR)
        {
            // Add the next date
            newDate = date.plusDays(nextDay);
            calendarAPI.addAllDayEvent(newDate, title);

            nextDay = nextDay + repeatDays;
        }
    }
    
    public void addHourlyEvent(
        LocalDate date, String title, LocalTime startTime,
        int durationMins, int repeatDays
    ){
        // Add the very first event
        calendarAPI.addHourlyEvent(date, title, startTime, durationMins);

        // Add the next date, depending on how many days provided, do this for a year
        int nextDay = repeatDays;
        LocalDate newDate = date;
        while(nextDay < ONE_YEAR)
        {
            // Add the next date
            newDate = date.plusDays(nextDay);
            calendarAPI.addHourlyEvent(newDate, title, startTime, durationMins);

            nextDay = nextDay + repeatDays;
        }
    }
}
