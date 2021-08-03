import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Top Panel that displays days of month as JButtons
 */
public class MonthPanel extends JPanel {
    private LocalDate date;

    /**
     * Constructor for MonthPanel
     *
     * @param cal CalendarModel
     * @param l   LocalDate
     */
    public MonthPanel(CalendarModel cal, LocalDate l) {
        date = LocalDate.of(l.getYear(), l.getMonth(), 1);
        int rows = 6;
        int cols = 7;

        this.setPreferredSize(new Dimension(700, 300));

        GridLayout grid = new GridLayout(rows, cols);
        this.setLayout(grid);

        int monthDays = l.lengthOfMonth();
        int monthDayCount = monthDays;

        for (int i = 1; i <= 42; i++) {
            if (i < getFirstOfMonth(l)) {
                JButton button = new JButton();
                this.add(button);
            }
            else if (monthDays <= 0) {
                JButton button = new JButton();
                this.add(button);
            }
            else {
                monthDays = monthDays - 1;
                JButton button = new JButton(Integer.toString(monthDayCount - monthDays));
                //add listeners and action handlers here
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {


                        System.out.println(button.getText());

                        setHighlightedDate(cal, l, button.getText());

                    }
                });
                this.add(button);
            }
        }

    }

    /**
     * reutrns the first of the month
     * @param l Local date used to get its first of the month
     * @return the day of the week that is the first of the month
     */
    public int getFirstOfMonth(LocalDate l) {
        LocalDate x = LocalDate.of(l.getYear(), l.getMonth(), 1);
        return weekTool(x.getDayOfWeek().toString());
    }

    /**
     * sets the highlighted date
     * 
     * @param cal
     * @param date
     * @param day
     */
    public void setHighlightedDate(CalendarModel cal, LocalDate date, String day) {
        LocalDate highlighted = LocalDate.of(date.getYear(), date.getMonth(), Integer.valueOf(day));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        System.out.println(" " + formatter.format(highlighted));
        cal.setHighlightedDate(highlighted);
    }

    /**
     * helper to get the day of the week
     * @param day
     * @return int dependsing on the day of the week
     */
    public static int weekTool(String day) {
        if (day.equals("SUNDAY")) {
            return 1;
        }
        else if (day.equals("MONDAY")) {
            return 2;
        }
        else if (day.equals("TUESDAY")) {
            return 3;
        }
        else if (day.equals("WEDNESDAY")) {
            return 4;
        }
        else if (day.equals("THURSDAY")) {
            return 5;
        }
        else if (day.equals("FRIDAY")) {
            return 6;
        }
        else {
            return 7;
        }
    }
}
