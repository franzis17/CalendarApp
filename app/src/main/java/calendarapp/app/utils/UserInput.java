package calendarapp.app.utils;

import java.util.*;

public class UserInput
{
    public static String getStrInput()
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //scanner.close();
        return input;
    }
    
    public static int getIntInput() throws InputMismatchException
    {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        //scanner.close();
        return input;
    }
}