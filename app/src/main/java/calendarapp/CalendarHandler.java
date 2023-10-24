package calendarapp.app;

import java.util.*;
import java.time.LocalDate;

public class CalendarHandler
{
    private List<Event> events = new ArrayList<>();
    private HashMap<String, EventRow> timeMap = new HashMap<>();

    /**
     * Check if there are any events within the 7 days requested
     */
    public void checkForEvents(String[] dates)
    {
        for(Event event : events)
        {
            for(int i = 0; i < dates.length; i++)
            {
                LocalDate eventDate = event.getDate();
            }
        }
    }

    public void initialiseTimeMap()
    {
        // timeMap.put("all-day", );
        // for(int i = 0; i < 24; i++)
        // {
        //     if(i < 12)
        //     {
        //         // AM
        //         timeMap.put();
        //     }
        //     else
        //     {
        //         // PM
        //         timeMap.put();
        //     }
        // }
    }
    
    public void addEvent(Event event)
    {
        System.out.println(">>> Adding an event...");
        if(event == null)
        {
            throw new NullPointerException("Event cannot be null when adding it");
        }
        events.add(event);
    }
    
    /**
     * [ Search an Event ]
     * 
     * Searches all events starting from the current date and onwards (up to a year)
     * 
     * @return  Event    the event that was searched
     */
    public Event searchEvent(String searchEventInput)
    {
        for(Event event : events)
        {
            String eventTitle = event.getTitle().toLowerCase();
            if(eventTitle.contains(searchEventInput.toLowerCase()))
            {
                // 1. output matching event details (of first match only)
                return event;
            }
        }
        return null;
    }
}
