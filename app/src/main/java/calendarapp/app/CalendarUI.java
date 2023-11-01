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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Displays a calendar that shows 7 days from now and events happening within the days.
 */
public class CalendarUI
{
    private static final Scanner USER_INPUT = new Scanner(System.in);
    
    private CalendarHandler calendar;
    
    /** The pointer to which date the calendar displays */
    private LocalDate startDate;
    
    /** Default locale is the system's */
    private Locale locale = Locale.getDefault();
    private ResourceBundle menuBundle = ResourceBundle.getBundle("menu", locale);
    
    public CalendarUI(CalendarHandler calendar)
    {
        this.calendar = calendar;
        startDate = LocalDate.now();
    }
    

    public void display()
    {
        // Display Calendar first then display menu
        displayCalendar();
        displayMainMenu();
        
        // Ensure to close scanner after user input
        USER_INPUT.close();
    }
    
    public void displayCalendar()
    {
        System.out.println("\n");
        // Info to display in the Calendar
        LocalDate[] sevenDays = DateUtility.getNextSevenDays(startDate);
        String[] columnDates = DateUtility.formatDateToDayMonthYear(sevenDays);
        String[] rowEventTimes = new String[25];          // Max 24 hours in a day + all day hour
        String[][] rowEventContents = new String[25][7];  // 30 rows(hour time), 7 columns(dates)
        
        // Find events happening in seven days
        AtomicInteger rowIdx = new AtomicInteger(0);  // allows int to be used inside lambda
        Map<Integer, EventRow> timeMap = calendar.findEventsInGivenDays(sevenDays);
        timeMap.forEach((timeKey, eventRowValue) ->
        {
            int eventTime = eventRowValue.getTime();
            String timeToDisplay;
            
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
            
            rowEventTimes[rowIdx.get()] = timeToDisplay;
            rowEventContents[rowIdx.get()] = eventsArr;
            rowIdx.incrementAndGet();
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
                printChooseAnOption();
                for(int i = 0; i < options.length; i++)
                {
                    System.out.println("  "+(i+1)+". " +
                        menuBundle.getString("main_menu_option." + i));
                }

                printEnterOption();
                int numOption = USER_INPUT.nextInt();

                switch(numOption)
                {
                    case 1:
                        displayDateOptions();
                        break;
                    case 2:
                        changeLocale();
                        displayCalendar();
                        break;
                    case 3:
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid option, only pick the following option.");
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Error: Ensure input is a number. More info: " + e.getMessage());
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private void displayDateOptions()
    {
        String[] options = {"+d", "+w", "+m", "+y", "-d", "-w", "-m", "-y", "t", "back"};
        boolean loop = true;
        
        while(loop)
        {
            printChooseAnOption();
            for(String option : options)
            {
                System.out.println("  " + option);
            }
            
            printEnterOption();
            String dateOption = USER_INPUT.nextLine();
            
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
            
            // After shifting date, display calendar.
            displayCalendar();
        }
    }
    
    private void changeLocale() throws IllegalArgumentException
    {
        System.out.println("Available translations:\n" + 
            "  - 'en-au' = Australian English (default)\n" +
            "  - 'fl' = Filipino/Tagalog");
        System.out.println("Enter locale to change to: ");
        String localeInput = USER_INPUT.next();
        
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
    
    
    public void printChooseAnOption()
    {
        System.out.println("\n" + menuBundle.getString("choose_an_option") + ":");
    }
    
    public void printEnterOption()
    {
        System.out.println(menuBundle.getString("prompt_option") + ": ");
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
