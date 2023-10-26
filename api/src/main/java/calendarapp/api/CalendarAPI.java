package calendarapp.api;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * `CalendarControls` from the Main Calendar App implements this API to define the methods of this
 * interface. This allows the Plugin to use the Main App's functionalities.
 */
public interface CalendarAPI
{
    void addAllDayEvent(LocalDate date, String title);
    void addHourlyEvent(LocalDate date, String title, LocalTime startTime, int durationMins);
}
