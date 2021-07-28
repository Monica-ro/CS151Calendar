import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class CreateView extends JPanel {
    public CreateView(CalendarModel model) {
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.PINK);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("CREATE VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        this.add(date);
    }


}
