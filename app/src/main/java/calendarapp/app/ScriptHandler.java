package calendarapp.app;

import calendarapp.api.*;

import org.python.core.*;
import org.python.util.*;

public class ScriptHandler
{
    private CalendarAPI calendarAPI;
    
    public ScriptHandler(CalendarAPI calendarAPI)
    {
        this.calendarAPI = calendarAPI;
    }
    
    public void runScript(String pythonScriptStr)
    {
        // Sanitise script
        pythonScriptStr = sanitiseScript(pythonScriptStr);

        // Pass API and run script
        try(PythonInterpreter interpreter = new PythonInterpreter();)
        {
            interpreter.set("calendarAPI", calendarAPI);
            interpreter.exec(pythonScriptStr);
        }
        catch(PyException e)
        {
            System.out.println("Error: error occured when executing Python script. " +
                "More info: " + e.getMessage());
        }
    }
    
    /**
     * Remove double pairs of double quotes. Preferrably do security checks here.
     */
    private String sanitiseScript(String pythonScriptStr)
    {
        return pythonScriptStr.replaceAll("\"\"", "\"");
    }
}
