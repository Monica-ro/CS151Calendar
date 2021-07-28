import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class WeekView extends JPanel implements ChangeListener {
    public WeekView(CalendarModel model) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.YELLOW);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("WEEK VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        this.add(date);
    }


    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
