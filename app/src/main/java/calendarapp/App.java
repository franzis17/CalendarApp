package calendarapp.app;

public class App
{
    public static void main(String[] args)
    {
        //test();
        CalendarHandler calendar = new CalendarHandler();
        CalendarUI ui = new CalendarUI(calendar);
        ui.display();
    }
    
    public static void test()
    {
        TestTerminalGrid.start();
    }
}
