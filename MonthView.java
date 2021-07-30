import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MonthView extends JPanel implements ChangeListener {
    private CalendarModel model;
    private String viewMetric;
    private LocalDate highlightedDate;
    private LocalDate currentDate;
    private LocalDate dateIterator;
    private ArrayList<Event> currentEventData;
    private JTextArea events;
    private JLabel currentDateToDisplay;


    MonthView(CalendarModel calModel) {
        this.model = calModel;
        this.viewMetric = "month";
        this.highlightedDate = calModel.getHighlightedDate();
        this.currentEventData = calModel.getData();

        returnView();
    }

    public void returnView() {
        this.removeAll();
        this.revalidate();

        JScrollPane calendar;
        calendar = printCalendar();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                calendar.getVerticalScrollBar().setValue(0);
            }
        });

        this.add(calendar);

        calendar.revalidate();
        calendar.repaint();

    }

    public JScrollPane printCalendar() {


        JTextArea calendar = new JTextArea();
        calendar.setSelectionStart(0);
        calendar.setSelectionEnd(0);

        ArrayList events = getMonthEvents();
        for (int i = 1; i <= highlightedDate.lengthOfMonth(); i++) {
            currentDate = LocalDate.of(highlightedDate.getYear(), highlightedDate.getMonth(), Integer.valueOf(i));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
            calendar.append("---" + formatter.format(currentDate) + "---" + "\n");
            calendar.append(model.format(getMonthEvents()) + "\n");
        }

        JScrollPane scroll = new JScrollPane(calendar);
        scroll.setPreferredSize(new Dimension(700, 449));

        return scroll;
    }


    public ArrayList<Event> getMonthEvents() {
        ArrayList<Event> monthEvents = new ArrayList<>();

        // add events
        for (Event e : model.getData()) {
            if (e.getDate().equals(currentDate)) {
                monthEvents.add(e);
            }
        }

//        monthEvents.add(new Event("test1", "3", LocalTime.of(12, 0), LocalTime.of(13, 0), LocalDate.of(2021, 7, 29), false));
//        monthEvents.add(new Event("test2", "3", LocalTime.of(12, 0), LocalTime.of(13, 0), LocalDate.of(2021, 7, 30), false));
//        monthEvents.add(new Event("test3", "3", LocalTime.of(15, 0), LocalTime.of(16, 0), LocalDate.of(2021, 7, 30), false));

        return monthEvents;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // display contents
        // if the model's
        // view metric matches with this one
        this.highlightedDate = model.getHighlightedDate();
        this.currentEventData = model.getData();

        if (model.getMetric().equalsIgnoreCase(viewMetric)) {
            returnView();
            System.out.println("This is the month view");
        }
        else {
            // don't display anything
        }


    }

}
