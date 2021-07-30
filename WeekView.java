import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class WeekView extends JPanel implements ChangeListener {

    private CalendarModel model;
    private String viewMetric;
    private LocalDate highlightedDate;
    private ArrayList<Event> currentEventData;
    private JTextArea events;
    private JLabel currentDateToDisplay;

    WeekView(CalendarModel calModel) {
        this.model = calModel;
        this.viewMetric = "week";
        this.highlightedDate = calModel.getHighlightedDate();
        this.currentEventData = calModel.getData();
        returnView();
    }

    public void returnView() {
        this.removeAll();

        this.revalidate();
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(700, 457));
        this.setBackground(Color.YELLOW);

        // panel display methods
        events = new JTextArea();
        currentDateToDisplay = new JLabel("Current date: " + highlightedDate);
        if (!getWeekEvents().isEmpty()) {
            events.setText(model.format(getWeekEvents()));
        }
        else {
            events.setText("No events today");
        }
        this.add(currentDateToDisplay, BorderLayout.NORTH);
        this.add(events, BorderLayout.CENTER);


        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        //JLabel date = new JLabel("WEEK VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        //this.add(date);

    }

    public ArrayList<Event> getWeekEvents() {

        ArrayList<Event> weekEvents = new ArrayList<>();

        int daysFromSunday = MonthPanel.weekTool(highlightedDate.getDayOfWeek().name()) - 1;

        LocalDate sunday = highlightedDate.minusDays(daysFromSunday);
        LocalDate monday = sunday.plusDays(1);
        LocalDate tuesday = monday.plusDays(1);
        LocalDate wednesday = tuesday.plusDays(1);
        LocalDate thursday = wednesday.plusDays(1);
        LocalDate friday = thursday.plusDays(1);
        LocalDate saturday = friday.plusDays(1);

        // add events
        for (Event e : model.getData()) {
            if (e.getDate().equals(sunday) || e.getDate().equals(monday) || e.getDate().equals(tuesday)
                    || e.getDate().equals(wednesday) || e.getDate().equals(thursday)
                    || e.getDate().equals(friday) || e.getDate().equals(saturday)) {
                System.out.println("inside if of get week method");
                weekEvents.add(e);
            }
        }

        return weekEvents;

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
            System.out.println("This is the week view");
        }
        else {
            // don't display anything
        }
    }
}



