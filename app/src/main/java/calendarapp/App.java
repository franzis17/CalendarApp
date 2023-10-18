package calendarapp.app;

public class App
{
    public static void main(String[] args)
    {
        //test();
        CalendarUI ui = new CalendarUI();
        ui.display();
    }
    
    public static void test()
    {
        TestTerminalGrid.start();
    }
}
