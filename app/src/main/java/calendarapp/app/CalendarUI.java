package calendarapp.app;

// package dependencies
import calendarapp.terminalgrid.TerminalGrid;
import calendarapp.app.Event.AllDayEvent;
import calendarapp.app.Event.HourlyEvent;

// external dependencies
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Displays a calendar that shows 7 days from now and events happening within the days.
 */
public class CalendarUI
{
    private CalendarHandler calendar;
    
    public CalendarUI(CalendarHandler calendar)
    {
        this.calendar = calendar;
    }
    
    
    public void display()
    {
        // [TEST] - Add a mock event to test how to map the event
        Event newEvent = new AllDayEvent(
            LocalDate.of(2023, 10, 27), 
            "Meeting 1"
        );
        System.out.println("Event to add:\n" + 
            " Date = " + newEvent.getDate() + "\n" +
            "Title = " + newEvent.getTitle()
        );
        calendar.addEvent(newEvent);
        
        
        LocalDate[] sevenDays = DateUtility.getNextSevenDays();
        HashMap<String, EventRow> timeMap = calendar.findEventsInGivenDays(sevenDays);
        timeMap.forEach((timeKey, eventRowValue) ->
        {
            String[] eventsArr = eventRowValue.getEvents();
            String events = String.join("", eventsArr);
            
            System.out.println("Time = " + timeKey + "\nEvents = " + events);
        });
        
        // TO DO:
        // 1. Turn the timeMap into rows to be displayed
        
        
        String[] columnDates = DateUtility.formatDateToDayMonthYear(sevenDays);
        String[] eventTimes = {"All-day", "12 AM", "1 AM"};
        String[][] rowEventContents =
        {
            {"", "", "", "", "", "", ""},  // row-1
            {"", "", "", "", "", "", ""},  // row-2
            {"", "", "", "", "", "", ""},  // row-3
        };
        
        // Initialise Terminal Grid
        var terminalGrid = TerminalGrid.create();
        terminalGrid.setTerminalWidth(200);
        
        // Print out the Calendar
        terminalGrid.print(rowEventContents, eventTimes, columnDates);
        System.out.println();
    }
    
    /** 
     * Check if any events exist in the 7 days and list them within the rows
     */
    public void checkForEvents()
    {
        
    }

    /**
     * Starting from the current date, get all 7 days to display.
     * These dates will be displayed in the columns of the calendar.
     */
    public String[] getNextSevenDays()
    {
        String[] columnDates = new String[7];
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dmyFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println("Original Format (of Current date) = " + currentDate);
        
        // Add the other 7 days from now
        for(int i = 0; i < 7; i++)
        {
            String formattedDate = currentDate.format(dmyFormat);
            columnDates[i] = formattedDate;
            
            // Add the next date
            currentDate = currentDate.plusDays(1);
        }
        
        return columnDates;
    }
    
    /**
     * 1. Grab user input: The user enters an input to search for an event.
     * 2. Looks at the calendar to find the event inputted.
     * 3a. If not found:
     *      i. OUTPUT "Not found"
     * 3b. If event is found:
     *      i. OUTPUT event details
     *      ii. shift current date to the date of the event
     */
    public void handleSearch()
    {
        // 1. Grab user input
        String searchInput = "christmas";  // TBD: make a search input

        // 2. Find the searchInput in the calendar
        Event eventSearched = calendar.searchEvent(searchInput);
        
        if(eventSearched == null)
        {
            System.out.println("No event with title '"+searchInput+"' was found");
        }
        else
        {
            // Output Event details
            System.out.println();
        }
    }
}
