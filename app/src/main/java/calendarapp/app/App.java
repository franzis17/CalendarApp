package calendarapp.app;

import calendarapp.app.testapp.*;

import java.io.*;

public class App
{
    public static void main(String[] args)
    {
        runCalendarApp(args);
        //testFileParser(args);
    }
    
    public static void runCalendarApp(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println("Error: Unable to start the application, please provide a file name as an argument.");
            System.out.println("Example file can be found in: ./src/main/resources/calendar.utf8.cal");
            return;
        }

        try
        {
            // Create initial calendar objects
            CalendarHandler calendar = new CalendarHandler();
            CalendarControls controlAPI = new CalendarControls(calendar);
            PluginHandler pluginHandler = new PluginHandler(controlAPI);
            CalendarUI ui = new CalendarUI(calendar);
            FileParser fileParser = new FileParser(new FileReader(args[0]));

            fileParser.setPluginHandler(pluginHandler);
            fileParser.parseFile();
            
            pluginHandler.loadPlugins();

            ui.display();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("!!! Illegal arg: " + e.getMessage());
            e.printStackTrace();
        }
        catch(NullPointerException e)
        {
            System.out.println("!!! ERROR: Ran in to null problem. More: " + e.getMessage());
            e.printStackTrace();
        }
        catch(ParseException e)
        {
            System.out.println("!!! ERROR: Failed to parse file.");
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("!!! ERROR: File not found.");
            e.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("!!! ERROR: " + e.getMessage());
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
