import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Calendar {
    int firstOfMonth;

    public Calendar() {

        LocalDate cal = LocalDate.now(); // capture today
        LocalDate x = LocalDate.of(cal.getYear(), cal.getMonth(), 1);
        firstOfMonth = getFirstOfMonth(x);


    }

    public static void main(String[] args) {

        Calendar calendar = new Calendar();

        JFrame frame = new JFrame();

        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 800;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 600, 500);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 501, 600, 800);

        int rows = 5;
        int cols = 7;


        topPanel.setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < 35; i++) {

            JButton button = new JButton(Integer.toString(i + 1));

            topPanel.add(button);
        }

        frame.add(topPanel);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
    }

    public int getFirstOfMonth(LocalDate date) {
        return weekTool(date.getDayOfWeek().toString());
    }

    public int weekTool(String day) {
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
