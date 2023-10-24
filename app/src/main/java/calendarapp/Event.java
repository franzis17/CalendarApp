package calendarapp.app;

import java.time.LocalDate;

public class Event
{
    private LocalDate date;
    private String title;
    

    public Event(String title)
    {
        this.title = title;
    }
    
    
    /* GETTERS */
    
    public LocalDate getDate()
    {
        return date;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    
    /* SETTERS */
    
    public void setDate(LocalDate date)
    {
        this.date = date;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
}
