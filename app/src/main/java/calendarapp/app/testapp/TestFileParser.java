package calendarapp.app.testapp;

import calendarapp.app.*;

import java.io.*;

public class TestFileParser
{
    public static void parseFile(String[] args)
    {
        System.out.println("[TESTING] >>> File Parser...");
        try
        {
            FileParser fileParser = new FileParser(new FileReader(args[0]));
            fileParser.parseFile();
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
    }
}
