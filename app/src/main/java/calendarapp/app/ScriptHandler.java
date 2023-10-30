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
        // Initialise the interpreter
        PythonInterpreter interpreter = new PythonInterpreter();

        // Bind the API to the script environment
        interpreter.set("calendarAPI", calendarAPI);

        // Run the script
        interpreter.exec(pythonScriptStr);
    }
}
