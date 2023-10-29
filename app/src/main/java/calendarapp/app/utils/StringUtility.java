package calendarapp.app.utils;

import java.util.*;

public class StringUtility {
    
    /**
     * Removes first and last character from a String. Exclude 0th and str.length() char.
     */
    public static String removeFirstAndLastChar(String str) {
        return str.substring(1, str.length()-1);
    }
}
