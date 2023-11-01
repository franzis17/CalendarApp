package calendarapp.app;

import calendarapp.api.*;

import java.util.*;

/**
 * Helps load plugins from the parser by using Reflection to create the Plugin's class
 */
public class PluginHandler
{
    private List<String> pluginClasspaths = new ArrayList<>();
    private List<PluginsAPI> plugins = new ArrayList<>();

    private CalendarAPI calendarAPI;
    
    public PluginHandler(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
    
    public void addPluginByClasspath(String pluginClasspath) throws IllegalArgumentException
    {
        if(pluginClasspath.isEmpty())
        {
            throw new IllegalArgumentException("Plugin classpath cannot be empty.");
        }
        pluginClasspaths.add(pluginClasspath);
    }
    
    public void addPlugin(PluginsAPI plugin) throws IllegalArgumentException
    {
        if(plugin == null)
        {
            throw new IllegalArgumentException("plugin cannot be null when adding it to PluginHandler.");
        }
        plugins.add(plugin);
    }
    
    public PluginsAPI loadPlugin(String classpath)
    {
        PluginsAPI plugin = null;

        try
        {
            Class<?> pluginClass = Class.forName(classpath);
            PluginsAPI pluginObj = (PluginsAPI)pluginClass.getConstructor().newInstance();
            plugin = pluginObj;
            
            if(plugin != null)
            {
                plugin.start(calendarAPI);
                plugins.add(plugin);
            }
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error: No classes found with the name '"+classpath+"'");
        }
        catch(ReflectiveOperationException e)
        {
            System.out.println("Error: Reflection error. More info: " + e.getMessage());
        }
        
        return plugin;
    }
    
    public void loadAllPlugins()
    {
        if(pluginClasspaths.size() == 0)
        {
            System.out.println("No plugins were found.");
            return;
        }

        // Load all plugins using Reflection
        for(String pluginClasspath : pluginClasspaths)
        {
            loadPlugin(pluginClasspath);
        }
    }
}
