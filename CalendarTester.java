import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * @author Nick Fong
 *
 */

/**
 A calendar application.
 */

public class CalendarTester {
    public int firstOfMonth;

    /**
    Captures the current date upon construction of the calendar application.
    */
    public CalendarTester() {

        LocalDate cal = LocalDate.now(); // capture today
        LocalDate x = LocalDate.of(cal.getYear(), cal.getMonth(), 1);
        //firstOfMonth = getFirstOfMonth(x);


    }

    /**
    A tester method for the calendar application.
    @param args unused
    */
    public static void main(String[] args) {
        LocalDate cal = LocalDate.now(); // capture today
        LocalDate x = LocalDate.of(cal.getYear(), cal.getMonth(), 1);

        CalendarModel calendar = new CalendarModel(cal);

        JFrame frame = new JFrame();

        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 950;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        JLabel label = new JLabel("                            " + calendar.getMonth() + "  " + calendar
                .getYear() + "                            ");
        JPanel textTitle = new JPanel();
        textTitle.setBounds(0, 0, 700, 20);
        textTitle.add(label);


        JPanel monthButtons = new JPanel();
        monthButtons.setBounds(0, 30, 700, 30);
        JButton backMonth = new JButton("<");
        JButton forwardMonth = new JButton(">");
        monthButtons.add(backMonth);
        monthButtons.add(forwardMonth);


        JLabel label2 = new JLabel("S                   M                   T                   W                   TH                   F                   S");
        JPanel textTitle2 = new JPanel();
        textTitle2.setBounds(0, 65, 700, 20);
        textTitle2.add(label2);


        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 95, 700, 300);


        JPanel middlePanel = new JPanel();
        middlePanel.setBounds(0, 400, 700, 30);
        JButton day = new JButton("Day");
        JButton week = new JButton("Week");
        JButton month = new JButton("Month");
        JButton agenda = new JButton("Agenda");
        JButton create = new JButton("Create");
        JButton fromFile = new JButton("From File");
        middlePanel.add(day);
        middlePanel.add(week);
        middlePanel.add(month);
        middlePanel.add(agenda);
        middlePanel.add(create);
        middlePanel.add(fromFile);

        JPanel currentViewButtons = new JPanel();
        currentViewButtons.setBounds(0, 430, 700, 30);
        JButton today = new JButton("Today");
        JButton back = new JButton("<");
        JButton next = new JButton(">");
        currentViewButtons.add(today);
        currentViewButtons.add(back);
        currentViewButtons.add(next);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 450, 700, 950);

        int rows = 5;
        int cols = 7;

        topPanel.setLayout(new GridLayout(rows, cols));

        int monthDays = calendar.getDaysInMonth();
        int monthDayCount = monthDays;
        for (int i = 1; i <= 35; i++) {
            if (i < calendar.getFirstOfMonth(x)) {
                JButton button = new JButton();
                //add listeners and action handlers here
                topPanel.add(button);
            }
            else if (monthDays <= 0) {
                JButton button = new JButton();
                //add listeners and action handlers here
                topPanel.add(button);
            }
            else {
                monthDays = monthDays - 1;
                JButton button = new JButton(Integer.toString(monthDayCount - monthDays));
                //add listeners and action handlers here
                topPanel.add(button);
            }
        }

        frame.add(textTitle);
        frame.add(monthButtons);
        frame.add(textTitle2);
        frame.add(topPanel);
        frame.add(middlePanel);
        frame.add(currentViewButtons);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
