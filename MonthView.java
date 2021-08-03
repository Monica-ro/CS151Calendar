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
    private char currentDayOfTheWeek;
    private char[] daysOfWeekConversion = {' ', 'M', 'T', 'W', 'H', 'F', 'A', 'S'};
    private char dow;


    /**
     * Constructs the Month view in the calendar.
     *
     * @param calModel - The calendar model of CalendarModel type
     */
    MonthView(CalendarModel calModel) {
        this.model = calModel;
        this.viewMetric = "month";
        this.highlightedDate = calModel.getHighlightedDate();
        this.currentDayOfTheWeek = daysOfWeekConversion[highlightedDate.getDayOfWeek().getValue()];
        this.currentEventData = calModel.getData();

        returnView();
    }

    /**
     * Updates the view panel upon construction and whenever there is an update in the model if the user has selected Month view.
     */
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

    /**
     * prints the calendar
     *
     * @return JScrollPane
     */
    public JScrollPane printCalendar() {

        JTextArea calendar = new JTextArea();
        calendar.setSelectionStart(0);
        calendar.setSelectionEnd(0);

        ArrayList events = getMonthEvents();
        for (int i = 1; i <= highlightedDate.lengthOfMonth(); i++) {
            currentDate = LocalDate.of(highlightedDate.getYear(), highlightedDate.getMonth(), Integer.valueOf(i));
            dow = daysOfWeekConversion[currentDate.getDayOfWeek().getValue()];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
            calendar.append("---" + formatter.format(currentDate) + "---" + "\n");
            calendar.append(model.format(getMonthEvents()) + "\n");
        }

        JScrollPane scroll = new JScrollPane(calendar);
        scroll.setPreferredSize(new Dimension(700, 449));

        return scroll;
    }


    /**
     * Obtains the list of events that occur on the current date
     *
     * @return a list of events that occur on the current date as ArrayList<Event>
     */
    public ArrayList<Event> getMonthEvents() {
        ArrayList<Event> monthEvents = new ArrayList<>();

        // add events
        for (Event e : model.getData()) {
            if (e.getDate().equals(currentDate) && !e.getIsRecurring()) {
                monthEvents.add(e);
            }
            if (e.getIsRecurring() == true && currentDate.isAfter(e.getStartDate()) && currentDate.isBefore(e.getEndDate()) && containsDayOfWeek(e, dow) == true
                    || e.getIsRecurring() == true && currentDate.equals(e.getStartDate()) && containsDayOfWeek(e, dow) == true
                    || e.getIsRecurring() == true && currentDate.equals(e.getEndDate()) && containsDayOfWeek(e, dow) == true) {
                monthEvents.add(e);
            }
        }
        return monthEvents;
    }

    /**
     * Checks if the highlighted date's day of the week matches with the recurring event
     *
     * @param ev - an Event
     * @return a boolean where true indicates that the current date's (in the model) day of the week matches with the event and false otherwise
     */
    public boolean containsDayOfWeek(Event ev, char dow) {
        char[] daysOfWeekEvent = ev.getDaysofTheWeek().toCharArray();
        for (char c : daysOfWeekEvent) {
            if (dow == c)
                return true;
        }
        return false;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // display contents
        // if the model's
        // view metric matches with this one
        this.highlightedDate = model.getHighlightedDate();
        this.currentEventData = model.getData();
        this.currentDayOfTheWeek = daysOfWeekConversion[model.getHighlightedDate().getDayOfWeek().getValue()];

        if (model.getMetric().equalsIgnoreCase(viewMetric)) {
            returnView();
            System.out.println("This is the month view");
        }
        else {
            // don't display anything
        }


    }

}
