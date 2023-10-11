package calendarapp.app;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Displays a calendar that shows 7 days from now and events happening within the days.
 */
public class CalendarUI {

    private List<String> dates = new ArrayList<>();

    public void display() {
        // Starting from the current date, get all 7 days to display.
        // These dates will be displayed in the *columns* of the calendar
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dmyFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println("Original Format = " + currentDate);
        
        for (int i = 0; i < 7; i++) {
            String dateFormatted = currentDate.format(dmyFormat);
            dates.add(dateFormatted);
            
            // Add the next date
            currentDate = currentDate.plusDays(1);
        }
        
        int date_i = 1;
        for (String date : dates) {
            System.out.println(date_i + ". Date = " + date);
            date_i++;
        }
    }
}
