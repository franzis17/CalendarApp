package calendarapp.app;

// package dependencies
import calendarapp.app.Event.AllDayEvent;
import calendarapp.app.Event.HourlyEvent;

// external dependencies
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Contains all events in the Calendar, and operations these events.
 */
public class CalendarHandler
{
    private static final int ALLDAY_TIME = -1;
    
    private List<Event> events = new ArrayList<>();
    private HashMap<Integer, EventRow> timeMap = new HashMap<>();
    
    public CalendarHandler()
    {
        initialiseTimes();
    }
    
    /**
     * Map each time (0 - 23) to an "EventRow". EventRow contains the events happening on that
     * time, with the column being the date of the event.
     * 
     * It is initialised so that all-day events are in the first row and then the hourly events
     * are automatically ordered from 0 to 23.
     */
    private void initialiseTimes() throws IllegalArgumentException
    {
        // Initialise all-day event row
        timeMap.put(ALLDAY_TIME, new EventRow(ALLDAY_TIME));
        
        // Initialise hourly event rows
        for(int i = 0; i < 24; i++)
        {
            timeMap.put(i, new EventRow(i));
        }
    }


    public void addEvent(Event event)
    {
        System.out.println(">>> Adding an event...");
        if(event == null)
        {
            throw new NullPointerException("Event cannot be null when adding it");
        }
        System.out.println("+ Event:\n" + 
            "\t Date = " + event.getDate() + "\n" +
            "\tTitle = " + event.getTitle()
        );
        events.add(event);
    }
    
    public void addAllDayEvent(LocalDate date, String title)
    {
        events.add(new AllDayEvent(date, title));
    }
    
    public void addHourlyEvent(LocalDate date, String title, LocalTime startTime, int durationMins)
    {
        events.add(new HourlyEvent(date, title, startTime, durationMins));
    }


    /**
     * Check if there are any events within the 7 days requested.
     */
    public HashMap<Integer, EventRow> findEventsInGivenDays(LocalDate[] dates)
    {
        System.out.println(">>> Finding events that are happening in seven days...");

        // Find all events that are happening in the given days
        for(Event event : events)
        {
            LocalDate eventDate = event.getDate();
            String eventTitle = event.getTitle();
            
            for(int i = 0; i < dates.length; i++)
            {
                // Check if the event equals to one of the seven days
                if(eventDate.equals(dates[i]))
                {
                    System.out.println("i. Event:\n" + 
                        "\t Date = " + eventDate + "\n" +
                        "\tTitle = " + eventTitle
                    );
                    if(event instanceof AllDayEvent)
                    {
                        EventRow eventRow = timeMap.get(ALLDAY_TIME);
                        eventRow.addEvent(i, eventTitle);
                    }
                    else if(event instanceof HourlyEvent)
                    {
                        HourlyEvent hourlyEvent = (HourlyEvent)event;
                        int timeOfEvent = hourlyEvent.getStartTime().getHour();
                        
                        EventRow eventRow = timeMap.get(timeOfEvent);
                        eventRow.addEvent(i, eventTitle);
                    }
                }
            }
        }
        
        return timeMap;
    }
    
    /**
     * [ Search an Event ]
     * 
     * Searches all events starting from the current date and onwards (up to a year)
     * 
     * @param   searchInput   the event title that the user is searching for
     * 
     * @return  Event    the event that was searched
     */
    public Event searchEvent(String searchInput)
    {
        for(Event event : events)
        {
            String eventTitle = event.getTitle().toLowerCase();

            if(eventTitle.contains(searchInput.toLowerCase()))
            {
                // 1. output matching event details (of first match only)
                return event;
            }
        }
        return null;
    }
}
