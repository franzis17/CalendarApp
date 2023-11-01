package calendarapp.app;

// package dependencies
import calendarapp.app.Event.AllDayEvent;
import calendarapp.app.Event.HourlyEvent;

// external dependencies
import java.util.*;
import java.time.LocalDate;

/**
 * Contains all events in the Calendar, and operations these events.
 */
public class CalendarHandler
{
    private static final int ALLDAY_TIME = -1;
    
    private List<Event> events = new ArrayList<>();
    private Map<Integer, EventRow> timeMap = new HashMap<>();
    
    public CalendarHandler()
    {
    }
    
    /**
     * Map each time (from 0 - 23) to an "EventRow". EventRow contains the events happening on that
     * time, with the column being the date of the event.
     * 
     * It is initialised so that all-day events are in the first row and then the hourly events
     * are automatically ordered from 0 to 23.
     */
    private void initialiseTimes()
    {
        // Initialise all-day event row
        timeMap.put(ALLDAY_TIME, new EventRow(ALLDAY_TIME));
        
        // Initialise hourly event rows
        for(int i = 0; i < 24; i++)
        {
            timeMap.put(i, new EventRow(i));
        }
    }


    public void addEvent(Event event) throws IllegalArgumentException
    {
        if(event == null)
        {
            throw new IllegalArgumentException("Event cannot be null when adding it");
        }
        events.add(event);
    }
    
    /**
     * Check if there are any events within the 7 days requested.
     */
    public Map<Integer, EventRow> findEventsInGivenDays(LocalDate[] dates)
    {
        timeMap.clear();
        initialiseTimes();

        // Find all events that are happening in the given days
        for(Event event : events)
        {
            // Loop all seven days to check if the event is happening on one of the 7 days
            for(int i = 0; i < dates.length; i++)
            {
                addIfEventIsHappeningInGivenDay(event, dates[i], i);
            }
        }
        
        return timeMap;
    }
    
    private void addIfEventIsHappeningInGivenDay(Event event, LocalDate date, int dateIndex)
    {
        LocalDate eventDate = event.getDate();
        String eventTitle = event.getTitle();

        if(eventDate.equals(date))
        {
            if(event instanceof AllDayEvent)
            {
                // Add the event in the all-day event row
                EventRow eventRow = timeMap.get(ALLDAY_TIME);
                eventRow.addEvent(dateIndex, eventTitle);
            }
            else if(event instanceof HourlyEvent)
            {
                // Add the event depending on its time
                HourlyEvent hourlyEvent = (HourlyEvent)event;
                int timeOfEvent = hourlyEvent.getStartTime().getHour();
                
                EventRow eventRow = timeMap.get(timeOfEvent);
                eventRow.addEvent(dateIndex, eventTitle);
            }
        }
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
