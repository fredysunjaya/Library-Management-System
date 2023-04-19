package librarymanagementsystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;

import com.toedter.calendar.JCalendar;

public class test extends JFrame {
    
    private JTextField textField;
    private JCalendar calendar;
    private JPopupMenu popupMenu;
    
    public test() {
        setTitle("JCalendar Dropdown Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        
        // Create the text field and calendar
        textField = new JTextField();
        calendar = new JCalendar();
        
        // Create the popup menu and add the calendar to it
        popupMenu = new JPopupMenu();
        popupMenu.add(calendar);
        
        // Add a mouse listener to the text field that displays the popup menu
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(textField, 0, popupMenu.getHeight());
            }
        });
        
        // Add the components to the frame
        add(textField);
        
        // Display the frame
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new test();
    }

}
