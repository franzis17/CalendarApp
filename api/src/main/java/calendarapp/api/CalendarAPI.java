package calendarapp.api;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CalendarAPI
{
    void addAllDayEvent(LocalDate date, String title);
    void addHourlyEvent(LocalDate date, String title, LocalTime startTime, int durationMins);
}
