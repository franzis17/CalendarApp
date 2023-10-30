package calendarapp.app;

import calendarapp.api.*;

import java.util.*;

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
    
    public void addPlugin(PluginsAPI plugin) throws NullPointerException
    {
        if(plugin == null)
        {
            throw new NullPointerException("plugin cannot be null when adding it to PluginHandler.");
        }
        plugins.add(plugin);
    }
    
    public void loadPlugins()
    {
        System.out.println(">>> Loading Plugins...");
        if(pluginClasspaths.size() == 0)
        {
            System.out.println("No plugins were found.");
            return;
        }

        // Load all plugins using Reflection
        for(String pluginClasspath : pluginClasspaths)
        {
            System.out.println("> Loading plugin: " + pluginClasspath);
            try
            {
                // IMPORTANT: Class names passed inside `Class.forName()` MUST BE a *classpath*
                Class<?> pluginClass = Class.forName(pluginClasspath);
                PluginsAPI pluginObj = (PluginsAPI)pluginClass.getConstructor().newInstance();
                
                if(pluginObj != null)
                {
                    plugins.add(pluginObj);
                    pluginObj.start(calendarAPI);
                    System.out.println("Added+Started " + pluginClasspath + " to the list of plugins.");
                }
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("!!! ERROR: " + e.getMessage());
                e.printStackTrace();
            }
            catch(RuntimeException e)
            {
                e.printStackTrace();
            }
            catch(ReflectiveOperationException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    // run script, to be called by the FileParser
}
