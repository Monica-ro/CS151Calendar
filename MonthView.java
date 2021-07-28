import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class MonthView extends JPanel implements ChangeListener {
    public MonthView(CalendarModel model) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.RED);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("MONTH VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        this.add(date);
    }


    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
