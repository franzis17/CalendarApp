package edu.curtin.calplugins;

// package dependencies
import calendarapp.api.PluginsAPI;

// external dependencies
import java.time.LocalDate;
import java.time.LocalTime;

public class RepeatPlugin implements PluginsAPI
{
    private CalendarAPI calendarAPI;
    
    private String title;
    private LocalDate startDate;
    private LocalTime startTime;
    private int durationMins;
    private int repeat;
    
    /**
     * All-Day Events that need to be repeated
     */
    public RepeatPlugin(String title, LocalDate startDate, int repeat)
    {
        this.title = title;
        this.startDate = startDate;
        this.repeat = repeat;
    }
    
    /**
     * Hourly Events that need to be repeated
     */
    public RepeatPlugin(
        String title, LocalDate startDate, LocalTime startTime,
        int durationMins, int repeat
    ){
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.durationMins = durationMins;
        this.repeat = repeat;
    }

    @Override
    public void start(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
    

    // [ MainApp function calls ]
    
    public void addAllDayEvent(LocalDate date, String title)
    {
        calendarAPI.addAllDayEvent(date, title);
    }
    
    public void addHourlyEvent(LocalDate date, String title, LocalTime startTime, int durationMins)
    {
        calendarAPI.addHourlyEvent(date, title, startTime, durationMins);
    }
}
