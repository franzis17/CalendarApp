package calendarapp.app;

/**
 * Represents a row in the calendar, which contains the time of the event,
 * and the 7 columns that contains event details.
 */
public class EventRow
{
    private static final int MIN_TIME = -1;
    private static final int MAX_TIME = 23;
    
    // row heading  = represents the time of the event that will be displayed in the calendar
    private int time;
    // row contents = each index represents the date of the event
    private String[] events = {"", "", "", "", "", "", ""};


    public EventRow(int time)
    {
        if(invalidTime(time))
        {
            throw new IllegalArgumentException("An event's time must be in the range of -1 to 24. "
                + "With -1 registered to all-day events and 0 to 23 is the time of the event.");
        }
        this.time = time;
    }

    
    public int getTime()
    {
        return time;
    }

    public String[] getEvents()
    {
        return events;
    }


    /**
     * Add the event title on the appropriate column date it is meant to be displayed at
     * 
     * @param   dateIndex   the column index the event should be displayed in
     * @param   eventTitle  the title of the event
     */
    public void addEvent(int dateIndex, String eventTitle)
    {
        events[dateIndex] = events[dateIndex] + "- " + eventTitle + "\n";
    }
    
    
    // [ Validations ]
    
    /**
     * Time range must be (-1 to 24). Time of -1 is registered for all-day events
     */
    public boolean invalidTime(int timeToValidate)
    {
        if(timeToValidate < MIN_TIME || timeToValidate > MAX_TIME)
        {
            return true;
        }
        return false;
    }
}
