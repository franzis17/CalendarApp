package calendarapp.app;

import java.util.*;
import java.text.NumberFormat;
import java.time.*;

public class TestLocale
{
    public void runTest()
    {
        arabicNumFormat();
    }
    
    public void arabicNumFormat()
    {
        System.out.println(">>> Testing arabic num format...");
        Locale arabicLocale = Locale.forLanguageTag("ar");
        
        int ogNum = 12345;
        NumberFormat numFormat = NumberFormat.getInstance(arabicLocale);
        String fmtNum = numFormat.format(ogNum);
        
        System.out.println("OG num = " + ogNum);
        System.out.println("Formatted num = " + fmtNum);
    }
}
