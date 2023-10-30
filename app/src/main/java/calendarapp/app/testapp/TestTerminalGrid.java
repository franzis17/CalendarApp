package calendarapp.app;

// Import package dependencies
import calendarapp.terminalgrid.TerminalGrid;

// Import external libraries
import java.util.*;


public class TestTerminalGrid
{    
    public static void start()
    {
        // Demonstration data
        String[][] messages = {{"one two three",     "four five six",             "seven eight nine"}, 
                               {"ten eleven twelve", "thirteen fourteen fifteen", "sixteen seventeen eighteen"}};
                      
        String[] rowHeadings = {"row 1", "row 2"};
        String[] colHeadings = {"column A", "column B", "column C"};
        
        
        // Initialising
        var terminalGrid = TerminalGrid.create();
        
        
        // Without headings
        terminalGrid.print(messages);
        System.out.println();

        
        // With headings
        terminalGrid.print(messages, rowHeadings, colHeadings);
        System.out.println();
        
        
        // Using nested lists rather than arrays
        var listMessages = new ArrayList<List<String>>();
        var row1 = new ArrayList<String>();
        var row2 = new ArrayList<String>();
        row1.add("one");
        row1.add("two");
        row2.add("three");
        row2.add("four");
        listMessages.add(row1);
        listMessages.add(row2);
        terminalGrid.print(listMessages, List.of("row 1", "row 2"), List.of("col A", "col B"));
        System.out.println();
        
        
        // With limited space
        terminalGrid.setTerminalWidth(42);
        terminalGrid.print(messages, rowHeadings, colHeadings);
        System.out.println();
        
        
        // With plain ASCII characters (if the real box-drawing characters don't display properly)
        terminalGrid.setTerminalWidth(120);
        terminalGrid.setBoxChars(TerminalGrid.ASCII_BOX_CHARS);
        terminalGrid.print(messages, rowHeadings, colHeadings);
        System.out.println();

        
        // With custom box-drawing characters (if you must!)
        // terminalGrid.setBoxChars(new TerminalGrid.BoxCharSet("│ ", " ┊ ", " │", "─", "╌", "─", "╭─", "─╮",  "╰─",  "─╯", "─┬─", "─┴─", "├╌", "╌┤", "╌┼╌"));
        // terminalGrid.print(messages, rowHeadings, colHeadings); 
    }
}
