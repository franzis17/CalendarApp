package calendarapp.app;

import calendarapp.app.testapp.*;

public class App
{
    public static void main(String[] args)
    {
        runCalendarApp(args);
        //test();a
    }
    
    public static void runCalendarApp(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println("Error: Unable to start the application, please provide a file name as an argument.");
            return;
        }

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
        TestDate testDate = new TestDate();
        testDate.main();
    }
}
