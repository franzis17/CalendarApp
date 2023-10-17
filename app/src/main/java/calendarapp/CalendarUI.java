package calendarapp.app;

// package dependencies
import calendarapp.terminalgrid.TerminalGrid;

// external dependencies
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Displays a calendar that shows 7 days from now and events happening within the days.
 */
public class CalendarUI {

    public void display() {
        String[] columnDates = getNextSevenDays();
        String[] rowEventTimes = {"12 AM", "1 AM"};
        String[][] rowEventContents = {
            {"", "a_two", "a_three", "a_four", "a_five", "a_six", "a_seven"},  // row1
            {"b_one", "b_two", "b_three", "b_four", "b_five", "b_six", "b_seven"},  // row2
        };
        
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
    public String[] getNextSevenDays() {
        String[] columnDates = new String[7];
        
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dmyFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println("Original Format (of Current date) = " + currentDate);
        
        // Add the other 7 days from now
        for (int i = 0; i < 7; i++) {
            String formattedDate = currentDate.format(dmyFormat);
            columnDates[i] = formattedDate;
            
            // Add the next date
            currentDate = currentDate.plusDays(1);
        }
        
        int date_i = 1;
        for (String date : columnDates) {
            //System.out.println(date_i + ". Date = " + date);
            date_i++;
        }
        
        return columnDates;
    }
}
