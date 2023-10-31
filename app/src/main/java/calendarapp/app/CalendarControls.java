package calendarapp.app;

// package dependencies
import calendarapp.app.utils.*;
import calendarapp.api.CalendarAPI;
import calendarapp.app.Event.AllDayEvent;
import calendarapp.app.Event.HourlyEvent;

// external dependencies
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class implements the CalendarAPI to allow Plugins to use the Main App's functions that is 
 * specified within this class.
 */
public class CalendarControls implements CalendarAPI
{
    private CalendarHandler calendar;
    
    public CalendarControls(CalendarHandler calendar)
    {
        this.calendar = calendar;
    }
    
    
    // Events
    
    @Override
    public void addAllDayEvent(LocalDate date, String title)
    {
        calendar.addEvent(new AllDayEvent(date, title));
    }
    
    
    @Override
    public void addAllDayEvent(String dateStr, String title)
    {
        LocalDate date = DateUtility.stringToDate(dateStr);
        if(date != null)
        {
            calendar.addEvent(new AllDayEvent(date, title));
        }
    }
    
    @Override
    public void addHourlyEvent(LocalDate date, String title, LocalTime startTime, int durationMins)
    {
        calendar.addEvent(new HourlyEvent(date, title, startTime, durationMins));
    }
    

    // Notify
    
    
    /** Test printing from the script */
    public void testPrint()
    {
        System.out.println("Successfully called by the script");
    }
}
