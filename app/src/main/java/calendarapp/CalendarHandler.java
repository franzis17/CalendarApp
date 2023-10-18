package calendarapp.app;

import java.util.*;

public class CalendarHandler
{
    private List<EventRow> events = new ArrayList<>();
    
    
    public void addEvent(EventRow event)
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
     * @return  EventRow    the event that was searched
     */
    public EventRow searchEvent(String searchEventInput)
    {
        for(EventRow event : events)
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
