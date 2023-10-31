package calendarapp.api;

public interface PluginsAPI
{
    void start(CalendarAPI calendarAPI);
    void addArgument(String key, String val);
    void doTask();
}
