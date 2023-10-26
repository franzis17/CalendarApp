package calendarapp.app;

public class App
{
    public static void main(String[] args)
    {
        //test();
        try
        {
            CalendarHandler calendar = new CalendarHandler();
            CalendarControls controlAPI = new CalendarControls(calendar);
            PluginHandler pluginHandler = new PluginHandler(controlAPI);
            // pluginHandler.loadPlugins();
            CalendarUI ui = new CalendarUI(calendar);
            ui.display();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("!!! ERROR MSG: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("!!! ERROR MSG: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void test()
    {
        TestTerminalGrid.start();
    }
}
