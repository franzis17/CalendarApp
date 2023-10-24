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
    private static final String ALL_DAY = "all-day";
    
    private List<Event> events = new ArrayList<>();
    private HashMap<String, EventRow> timeMap = new HashMap<>();
    
    public CalendarHandler()
    {
        // Pre-initialise the all-day event row
        timeMap.put(ALL_DAY, new EventRow(ALL_DAY));
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
     * Check if there are any events within the 7 days requested.
     */
    public HashMap<String, EventRow> findEventsInGivenDays(LocalDate[] dates)
    {
        System.out.println("> Finding events that are happening in seven days");

        // Find all events that are happening in the given days
        for(Event event : events)
        {
            LocalDate eventDate = event.getDate();
            String eventTitle = event.getTitle();

            System.out.println("+ Event:\n" + 
                "\tTitle = " + eventTitle + "\n" +
                "\t Date = " + eventDate
            );
            
            for(int i = 0; i < dates.length; i++)
            {
                // Check if the event equals to one of the seven days
                if(eventDate.equals(dates[i]))
                {
                    System.out.println("> The event is happening on the seven days.\n" +
                        "Event Date = " + eventDate + "\n" +
                        "  dates["+i+"] = " + dates[i]
                    );
                    // All-day events must be added to all-day event row AND
                    // Hourly events must be mapped dynamically
                    if(event instanceof AllDayEvent)
                    {
                        // add to all-day event row
                        EventRow eventRow = timeMap.get(ALL_DAY);
                        eventRow.addEvent(i, eventTitle);
                    }
                    else if(event instanceof HourlyEvent)
                    {
                        HourlyEvent hourlyEvent = (HourlyEvent)event;
                        String timeStr = Integer.toString(hourlyEvent.getStartTime().getHour());
                        
                        if(timeMap.containsKey(timeStr))
                        {
                            // If time has already been mapped, simply append the event
                            EventRow eventRow = timeMap.get(timeStr);
                            eventRow.addEvent(i, eventTitle);
                        }
                        else
                        {
                            // Unmapped time must be mapped dynamically depending on the
                            // Time of the event
                            EventRow eventRow = new EventRow(timeStr);
                            eventRow.addEvent(i, eventTitle);
                            timeMap.put(timeStr, eventRow);
                        }
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
