package edu.curtin.calplugins;

import java.util.*;
import calendarapp.api.*;

/**
 * Notifies the user if an event with a title that contains the "text" specified in the file.
 */
public class Notify implements PluginsAPI
{
    private static final String TEXT = "text";

    private CalendarAPI calendarAPI;
    private Map<String, String> arguments = new HashMap<>();
    
    private String text = "";
    
    public Notify()
    {
        initialiseArguments();
    }
    
    /**
     * Argument values will be added once the plugin is loaded from the file
     */
    public void initialiseArguments()
    {
        arguments.put(TEXT, "");
    }

    @Override
    public void start(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
    
    @Override
    public void addArgument(String key, String val) throws IllegalArgumentException
    {
        if(!arguments.containsKey(key))
        {
            throw new IllegalArgumentException(
                "Argument is invalid. Argument should be one of the following: " +
                "text");
        }
        text = val;
    }
    
    @Override
    public void doTask()
    {
        searchAndNotify();
    }
    
    /**
     * [Callback function]
     * register callback function using the calendarAPI
     */
    public void searchAndNotify()
    {
        
    }
}
