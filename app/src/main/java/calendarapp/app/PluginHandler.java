package calendarapp.app;

import calendarapp.api.PluginsAPI;

import java.util.*;

public class PluginHandler
{
    private List<PluginsAPI> plugins = new ArrayList<>();
    private CalendarControls controlAPI;
    
    public PluginHandler(CalendarControls controlAPI)
    {
        this.controlAPI = controlAPI;
    }
    
    public void loadPlugins()
    {
        System.out.println(">>> Loading Plugins...");
        
        // Read file that contains all plugins (each plugins should have its classpath)
        // example classpath:  weatherapp.plugin1.CLVWeatherPlugin
        
    }
}
