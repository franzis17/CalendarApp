package edu.curtin.calplugins;

// package dependencies
import calendarapp.api.*;

public class Notify implements PluginsAPI
{
    private CalendarAPI calendarAPI;

    @Override
    public void start(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
}
