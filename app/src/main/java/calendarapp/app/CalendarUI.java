package calendarapp.app;

// package dependencies
import calendarapp.app.utils.*;
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
    
    /** The pointer to which date the calendar displays */
    private LocalDate startDate;
    
    /** Default locale is the system's */
    private Locale locale;
    private ResourceBundle menuBundle;
    
    public CalendarUI(CalendarHandler calendar)
    {
        this.calendar = calendar;
        startDate = LocalDate.now();
        locale = Locale.getDefault();
        menuBundle = ResourceBundle.getBundle("menu", locale);
    }
    

    public void display()
    {
        // Display Calendar first then display menu
        displayCalendar();
        displayMainMenu();
    }
    
    public void displayCalendar()
    {
        System.out.println("\n\n");
        // Info to display in the Calendar
        LocalDate[] sevenDays = DateUtility.getNextSevenDays(startDate);
        String[] columnDates = DateUtility.formatDateToDayMonthYear(sevenDays);
        String[] rowEventTimes = new String[25];          // Max 24 hours in a day + all day hour
        String[][] rowEventContents = new String[25][7];  // 30 rows(hour time), 7 columns(dates)
        
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

        System.out.println("Locale = " + locale);
    }
    
    public void displayMainMenu()
    {
        String[] options = {"shift date", "change locale", "quit"};
        boolean loop = true;
        
        while(loop)
        {
            try
            {
                System.out.println("\n" + menuBundle.getString("choose_an_option") + ":");
                for(int i = 0; i < options.length; i++)
                {
                    System.out.println("  "+(i+1)+". " + options[i]);
                }

                System.out.print("Enter number option: ");
                int numOption = UserInput.getIntInput();

                switch(numOption)
                {
                    case 1:
                        displayDateOptions();
                        break;
                    case 2:
                        changeLocale();
                        break;
                    case 3:
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid option, only pick the following option.");
                }
                
                displayCalendar();
            }
            catch(InputMismatchException e)
            {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace();
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    private void displayDateOptions()
    {
        String[] options = {"+d", "+w", "+m", "+y", "-d", "-w", "-m", "-y", "t", "back"};
        boolean loop = true;
        
        while(loop)
        {
            System.out.println("\nPlease select one of the options below:");
            for(String option : options)
            {
                System.out.println("  " + option);
            }
            
            System.out.print("Enter an option above: ");
            String dateOption = UserInput.getStrInput();
            
            /*
              Preferrably do HashMap of options with the key as the option and the value
              is a Runnable function.
            */
            if(dateOption.equals(options[0]))
            {
                // go forward 1 day
                startDate = startDate.plusDays(1);
            }
            else if(dateOption.equals(options[1]))
            {
                // go forward 1 week
                startDate = startDate.plusWeeks(1);
            }
            else if(dateOption.equals(options[2]))
            {
                // go forward 1 month
                startDate = startDate.plusMonths(1);
            }
            else if(dateOption.equals(options[3]))
            {
                // go forward 1 year
                startDate = startDate.plusYears(1);
            }
            else if(dateOption.equals(options[4]))
            {
                // go back 1 day
                startDate = startDate.minusDays(1);
            }
            else if(dateOption.equals(options[5]))
            {
                // go back 1 week
                startDate = startDate.minusWeeks(1);
            }
            else if(dateOption.equals(options[6]))
            {
                // go back 1 month
                startDate = startDate.minusMonths(1);
            }
            else if(dateOption.equals(options[7]))
            {
                // go back 1 year
                startDate = startDate.minusYears(1);
            }
            else if(dateOption.equals(options[8]))
            {
                // return to today
                System.out.println("Returning to today");
                startDate = LocalDate.now();
            }
            else if(dateOption.equals(options[9]))
            {
                // go back to main menu
                loop = false;
            }
            else
            {
                System.out.println("Not a valid option.");
            }
            
            displayCalendar();
        }
    }
    
    private void changeLocale() throws IllegalArgumentException
    {
        System.out.print("Enter locale to change to: ");
        String localeInput = UserInput.getStrInput();
        System.out.println("localeInput = " + localeInput);
        
        locale = Locale.forLanguageTag(localeInput);
        menuBundle = ResourceBundle.getBundle("menu", locale);
        
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
    
    
    

    // [ Test Data ]
    
    public void addMockEvents()
    {
        LocalDate twoDaysFromToday = LocalDate.now().plusDays(2);
        
        // Add a mock event to test how to map the event
        Event allDayEvent = new AllDayEvent(
            twoDaysFromToday, 
            "Meeting 1"
        );
        Event hourlyEvent = new HourlyEvent(
            twoDaysFromToday,
            "One hour meeting",
            LocalTime.of(13, 00),
            60
        );
        Event hourlyEvent2 = new HourlyEvent(
            twoDaysFromToday,
            "Buy pizza for meeting",
            LocalTime.of(13, 00),
            60
        );
        calendar.addEvent(allDayEvent);
        calendar.addEvent(hourlyEvent);
        calendar.addEvent(hourlyEvent2);
    }
}
