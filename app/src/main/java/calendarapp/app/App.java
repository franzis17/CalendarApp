package calendarapp.app;

import calendarapp.app.testapp.*;

import java.io.*;

public class App
{
    public static void main(String[] args)
    {
        //runCalendarApp(args);
        testFileParser(args);
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

            //FileParser fileParser = new FileParser(new FileReader(args[0]));

            CalendarUI ui = new CalendarUI(calendar);
            ui.display();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("!!! ERROR MSG: " + e.getMessage());
            e.printStackTrace();
        }
        // catch(ParseException e)
        // {
        //     System.out.println("!!! ERROR: Failed to parse file.");
        //     e.printStackTrace();
        // }
        // catch(FileNotFoundException e)
        // {
        //     System.out.println("!!! ERROR: File not found.");
        //     e.printStackTrace();
        // }
        catch(Exception e)
        {
            System.out.println("!!! ERROR MSG: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void testDate()
    {
        TestDate.runTest();
    }
    
    public static void testFileParser(String[] args)
    {
        TestFileParser.parseFile(args);
    }
}
