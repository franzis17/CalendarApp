package calendarapp.app.testapp;

import calendarapp.app.*;
import calendarapp.api.*;

import java.io.*;
import java.util.*;

public class TestMain
{
    private String[] args;
    
    private CalendarHandler calendarHandler;
    private CalendarControls calendarAPI;
    private CalendarUI ui;

    // private FileParser fileParser;
    
    private PluginHandler pluginHandler;
    private ScriptHandler scriptHandler;
    
    public TestMain(String[] args)
    {
        this.args = args;

        // Create initial calendar objects
        calendarHandler = new CalendarHandler();
        calendarAPI = new CalendarControls(calendarHandler);
        ui = new CalendarUI(calendarHandler);

        // Plugins/Scripts
        pluginHandler = new PluginHandler(calendarAPI);
        scriptHandler = new ScriptHandler(calendarAPI);
    }
    
    public void startTest()
    {
        //testLoadPlugin();
        testDate();
    }
    
    public void testLoadPlugin()
    {
        System.out.println(">>> Testing plugin loader...");
        PluginsAPI plugin = pluginHandler.loadPlugin("edu.curtin.calplugins.Repeat");
        System.out.println(">>> Finished testing plugin loader");
    }

    public void testDate()
    {
        TestDate.runTest();
    }
    
    public void testFileParser(String[] args)
    {
        TestFileParser.parseFile(args);
    }
    
    public void testLocale(String[] args)
    {
        TestLocale testLocale = new TestLocale();
        testLocale.runTest();
    }
}
