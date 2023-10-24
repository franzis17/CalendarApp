package calendarapp.app;

public class EventRow
{
    private int time;
    private String[] events = new String[7];
    
    public EventRow(int time)
    {
        this.time = time;
    }
    
    public String[] getEvents()
    {
        return events;
    }
}
