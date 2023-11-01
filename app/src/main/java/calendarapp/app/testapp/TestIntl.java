package calendarapp.app;

import java.util.*;
import java.text.NumberFormat;
// import java.time.*;

/**
 * Internationalisation features to test
 */
public class TestIntl
{
    public void runTest()
    {
        //arabicNumFormat();
        testTranslation();
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
    
    public void testTranslation()
    {
        System.out.println(">>> Testing translations...");

        Locale locale = Locale.getDefault();
        System.out.println("Locale = " + locale);

        ResourceBundle bundle = ResourceBundle.getBundle("menu", locale);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("world"));
    }
}
