package edu.curtin.calplugins;

import calendarapp.api.PluginsAPI;

public class Repeat implements PluginsAPI
{
    private CalendarAPI calendarAPI;
    
    
    @Override
    public void start(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
}
