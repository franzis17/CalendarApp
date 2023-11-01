package calendarapp.app;

import calendarapp.app.testapp.*;

import java.io.*;

public class App
{
    public static void main(String[] args)
    {
        runCalendarApp(args);
        //test(args);
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
            CalendarHandler calendarHandler = new CalendarHandler();
            CalendarControls calendarAPI = new CalendarControls(calendarHandler);
            CalendarUI ui = new CalendarUI(calendarHandler);
            FileParser fileParser = new FileParser(new FileReader(args[0]));

            // Plugins/Scripts
            PluginHandler pluginHandler = new PluginHandler(calendarAPI);
            ScriptHandler scriptHandler = new ScriptHandler(calendarAPI);
            fileParser.setCalendarHandler(calendarHandler);
            fileParser.setPluginHandler(pluginHandler);
            fileParser.setScriptHandler(scriptHandler);

            fileParser.parseFile();
            
            ui.display();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        catch(ParseException e)
        {
            System.out.println("Error: Failed to parse file. More info: " + e.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: File not found. More info: " + e.getMessage());
        }
    }
    
    public static void test(String[] args)
    {
        TestMain testMain = new TestMain(args);
        testMain.startTest();
    }
}
