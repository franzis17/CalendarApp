package calendarapp.app;

// package dependencies
import calendarapp.terminalgrid.TerminalGrid;
import calendarapp.app.Event.AllDayEvent;
import calendarapp.app.Event.HourlyEvent;

// external dependencies
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
        // Info to display in the Calendar
        LocalDate[] sevenDays = DateUtility.getNextSevenDays();
        String[] columnDates = DateUtility.formatDateToDayMonthYear(sevenDays);
        String[] rowEventTimes = new String[25];          // Max 24 hours in a day
        String[][] rowEventContents = new String[25][7];  // 30 rows(times), 7 columns(dates)
        
        
        // [ START TEST]
        // Add a mock event to test how to map the event
        Event allDayEvent = new AllDayEvent(
            LocalDate.of(2023, 10, 27), 
            "Meeting 1"
        );
        Event hourlyEvent = new HourlyEvent(
            LocalDate.of(2023, 10, 27),
            "One hour meeting",
            LocalTime.of(13, 00),
            60
        );
        Event hourlyEvent2 = new HourlyEvent(
            LocalDate.of(2023, 10, 27),
            "Buy pizza for meeting",
            LocalTime.of(13, 00),
            60
        );
        calendar.addEvent(allDayEvent);
        calendar.addEvent(hourlyEvent);
        calendar.addEvent(hourlyEvent2);
        // [ END TEST ]
        
        
        // Find events happening in seven days
        int[] i_row = { 0 };
        HashMap<Integer, EventRow> timeMap = calendar.findEventsInGivenDays(sevenDays);
        timeMap.forEach((timeKey, eventRowValue) ->
        {
            int eventTime = eventRowValue.getTime();
            String timeToDisplay = "";
            
            // Format the time to either AM or PM
            if (eventTime == -1)
            {
                timeToDisplay = "All-Day";
            }
            else if (eventTime == 0)
            {
                timeToDisplay = "12 AM";
            }
            else if(eventTime < 12)
            {
                timeToDisplay = eventTime + " AM";
            }
            else if(eventTime == 12)
            {
                timeToDisplay = "12 PM";
            }
            else
            {
                timeToDisplay = (eventTime - 12) + " PM";
            }

            String[] eventsArr = eventRowValue.getEvents();
            rowEventTimes[i_row[0]] = timeToDisplay;
            rowEventContents[i_row[0]] = eventsArr;
            i_row[0]++;
        });
        
        
        // Initialise Terminal Grid
        var terminalGrid = TerminalGrid.create();
        terminalGrid.setTerminalWidth(200);
        
        // Print out the Calendar
        terminalGrid.print(rowEventContents, rowEventTimes, columnDates);
        System.out.println();
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
