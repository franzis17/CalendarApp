package calendarapp.app;

public class EventRow
{
    // row heading
    private String time;
    // row contents
    private String[] events = {"", "", "", "", "", "", ""};

    public EventRow(String time)
    {
        this.time = time;
    }


    public void addEvent(int i, String eventTitle)
    {
        events[i] = events[i] + "- " + eventTitle + "\n";
    }
    
    public String[] getEvents()
    {
        return events;
    }
}
