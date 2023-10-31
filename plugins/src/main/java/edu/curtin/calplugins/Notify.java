package edu.curtin.calplugins;

// package dependencies
import calendarapp.api.*;

/**
 * Notifies the user if an event with a title that contains the "text" specified in the file.
 */
public class Notify implements PluginsAPI
{
    private CalendarAPI calendarAPI;

    @Override
    public void start(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
    
    @Override
    public void addArgument(String key, String val) throws IllegalArgumentException
    {
        
    }
    
    @Override
    public void doTask()
    {
        
    }
}
