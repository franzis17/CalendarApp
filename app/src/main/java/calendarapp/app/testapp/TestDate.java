package calendarapp.app.testapp;

import calendarapp.app.utils.*;

import java.time.LocalDate;

@SuppressWarnings("PMD")
public class TestDate
{
    public static void runTest()
    {
        TestDate testDate = new TestDate();
        testDate.stringToDate();
    }
    
    /**
     * Test repeating dates for a year
     */
    public void dateRepetition()
    {
        System.out.println("!!! [TEST] - Testing date repetition...");

        LocalDate date = LocalDate.now();
        int repeatDays = 15;  // repeats every 15 days
        System.out.println("Original Date = " + date);
        System.out.println("Amount of days to repeat = " + repeatDays);
        
        int nextDay = repeatDays;
        int oneYear = 365;
        LocalDate newDate = date;
        while(nextDay < oneYear)
        {
            System.out.println("Next day = "+ nextDay);
            newDate = date.plusDays(nextDay);
            System.out.println(nextDay + " days from now = " + newDate);

            nextDay = nextDay + repeatDays;
            System.out.println();
        }
    }
    
    public void addYearToDate()
    {
        System.out.println(">>> Test add year to date...");
        LocalDate givenDate = LocalDate.of(2023, 10, 10);
        LocalDate oneYearAfter = givenDate.plusYears(1);
        
        System.out.println("Original Date = " + givenDate);
        System.out.println("One year after = " + oneYearAfter);
    }
    
    public void dateIsBefore()
    {
        System.out.println(">>> Test date is before...");
        LocalDate date1 = LocalDate.of(2023, 10, 10);
        LocalDate date2 = date1.plusYears(1);
        
        if(date1.isBefore(date2))
        {
            System.out.println(date1 + " < " + date2);
        }
    }
    
    public void stringToDate()
    {
        System.out.println(">>> Testing string to date...");
        String strDate = "2023-11-11";
        System.out.println("strDate = " + strDate);

        LocalDate date = DateUtility.newStringToDate(strDate);
        System.out.println("parsed date = " + date);
    }
}
