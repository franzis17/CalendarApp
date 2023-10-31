package edu.curtin.calplugins;

// package dependencies
import calendarapp.api.*;

// external dependencies
import java.util.*;
import java.time.*;
import java.time.format.*;

/**
 * A plugin that allows the user to add multiple events given "n" days.
 * Can add either an All-Day Event or an Hourly Event and repeat the event for every "n" days
 * until a year after startDate.
 * 
 * Potentially use FluentInterface to create the Repeat object based on key-value pairs.
 */
public class Repeat implements PluginsAPI
{
    private final static int ONE_YEAR = 365;

    private HashMap<String, String> arguments = new HashMap<>();
    
    private CalendarAPI calendarAPI;

    private LocalDate startDate = null;
    private String title = "";
    private LocalTime startTime = null;
    private int duration = 0;
    private int repeat =  0;

    private final static String START_DATE = "startDate";
    private final static String TITLE = "title";
    private final static String START_TIME = "startTime";
    private final static String DURATION = "duration";
    private final static String REPEAT = "repeat";
    
    public Repeat()
    {
        initialiseArguments();
    }
    
    /**
     * Argument values will be added once the plugin is loaded from the file
     */
    public void initialiseArguments()
    {
        arguments.put(START_DATE, "");
        arguments.put(TITLE, "");
        arguments.put(START_TIME, "");
        arguments.put(DURATION, "");
        arguments.put(REPEAT, "");
    }


    @Override
    public void start(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
    
    /** Add all key-value pairs of a Repeat Plugin */
    @Override
    public void addArgument(String key, String val) throws IllegalArgumentException
    {
        if(!arguments.containsKey(key))
        {
            throw new IllegalArgumentException(
                "Key is invalid. Key should be one of the following: " +
                "title, startDate, repeat (optional: startTime, duration)");
        }
        if(key.equals(START_DATE))
        {
            if(isInvalidDate(val))
            {
                throw new IllegalArgumentException("Date is invalid. Must be in the format 'yyyy-mm-dd'");
            }
            startDate = LocalDate.parse(val);
        }
        if(key.equals(TITLE))
        {
            if(val.isEmpty())
            {
                throw new IllegalArgumentException("Title is empty");
            }
            title = val;
        }
        if(key.equals(START_TIME))
        {
            if(isInvalidTime(val))
            {
                throw new IllegalArgumentException("Time is invalid. Must be in the format 'hr:min:sec'");
            }
            startTime = LocalTime.parse(val);
        }
        if(key.equals(DURATION))
        {
            if(isNonNumeric(val))
            {
                throw new IllegalArgumentException("Duration value should be numeric");
            }
            duration = Integer.parseInt(val);
        }
        if(key.equals(REPEAT))
        {
            if(isNonNumeric(val))
            {
                throw new IllegalArgumentException("Repeat value should be numeric");
            }
            repeat = Integer.parseInt(val);
        }
    }
    
    /**
     * Using the arguments that was added. Do the task of the plugin.
     * Repeat Plugin's job is to repeatedly create events based on the "repeat" value
     */
    @Override
    public void doTask()
    {
        repeatedlyAddEvents();
    }
    
    
    // [ MainApp functions ]
    
    /** To be called AFTER key-value pairs have all been parsed.  */
    // public void addEvent(String date, String title, String startTime, String duration)
    // {
    //     calendarAPI.addEvent(date, title, startTime, duration);
    // }
    
    public void repeatedlyAddEvents() throws IllegalArgumentException
    {
        // must have date, title, and repeat
        validateRepeatEvent();
        
        if(isAllDayEvent())
        {
            repeatedlyAddAllDayEvent();
        }
        
        // repeatedly add hourly events
    }
    
    public void repeatedlyAddAllDayEvent()
    {
        System.out.println(">>> Repeatedly adding All Day Events...");
        
        // Add the very first event
        calendarAPI.addAllDayEvent(startDate, title);

        // Add the next date, depending on how many days provided, do this for a year
        int nextDay = repeat;
        LocalDate newDate = startDate;
        while(nextDay < ONE_YEAR)
        {
            // Repeat adding of the all day event for a year
            newDate = startDate.plusDays(nextDay);
            calendarAPI.addAllDayEvent(newDate, title);
            nextDay = nextDay + repeat;
        }
    }
    
    // public void addHourlyEvent(
    //     LocalDate date, String title, LocalTime startTime,
    //     int durationMins, int repeatDays
    // ){
    //     // Add the very first event
    //     calendarAPI.addHourlyEvent(date, title, startTime, durationMins);

    //     // Add the next date, depending on how many days provided, do this for a year
    //     int nextDay = repeatDays;
    //     LocalDate newDate = date;
    //     while(nextDay < ONE_YEAR)
    //     {
    //         // Repeat adding of the hourly event for a year
    //         newDate = date.plusDays(nextDay);
    //         calendarAPI.addHourlyEvent(newDate, title, startTime, durationMins);
    //         nextDay = nextDay + repeatDays;
    //     }
    // }
    
    
    // [ Validations ]
    
    /**
     * Must have date, title, and repeat arguments. Without these, then do not add events.
     */
    public void validateRepeatEvent() throws IllegalArgumentException
    {
        if(startDate == null)
        {
            throw new IllegalArgumentException("StartDate was not provided in Repeat Plugin.");
        }
        if(title.isEmpty())
        {
            throw new IllegalArgumentException("Title was not provided in Repeat Plugin.");
        }
        if(repeat == 0)
        {
            throw new IllegalArgumentException("Repeat was not provided in Repeat Plugin.");
        }
    }
    
    
    /**
     * If there is no startTime field, then presume it is an "AllDayEvent"
     */
    private boolean isAllDayEvent()
    {
        String startTime = arguments.get(START_TIME);
        if(startTime.isEmpty())
        {
            return true;
        }
        return false;
    }
    
    private boolean isNumeric(String strNum)
    {
        boolean isNumeric = false;
        try
        {
            double d = Double.parseDouble(strNum);
            isNumeric = true;
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error: " + strNum + " is not a number.");
        }
        return isNumeric;
    }
    
    private boolean isNonNumeric(String strNum)
    {
        return !isNumeric(strNum);
    }
    
    private boolean isValidDate(String strDate)
    {
        boolean isValidDate = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try
        {
            LocalDate.parse(strDate, dtf);
            isValidDate = true;
        }
        catch(DateTimeParseException e)
        {
            System.out.println("Error: Invalid date. Must be in the format 'yyyy-MM-dd'.");
        }
        return isValidDate;
    }
    
    private boolean isInvalidDate(String strDate)
    {
        return !isValidDate(strDate);
    }
    
    private boolean isValidTime(String strTime)
    {
        boolean isValidTime = false;
        try
        {
            LocalTime.parse(strTime);
            isValidTime = true;
        }
        catch(DateTimeParseException e)
        {
            System.out.println("Error: Invalid time. Must be in the format: 'hr:min:sec'");
        }
        return isValidTime;
    }
    
    private boolean isInvalidTime(String strTime)
    {
        return !isValidTime(strTime);
    }
}
