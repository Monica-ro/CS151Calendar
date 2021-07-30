import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Monica Orme
 */

/**
 * A view for the agenda option within the calendar model.
 */
public class AgendaView extends JPanel implements ChangeListener {


    //Ideas:
    /* its parameters would include a specific set
     * of local dates
     * that are passed to the constructor
     * so a start date and end date is needed


     */
    private LocalDate startDate;
    private LocalDate endDate;
    private String viewMetric;


    /**
     * Constructs an agenda view without dates.
     */
    public AgendaView(CalendarModel model) {
        this.setPreferredSize(new Dimension(700, 457));
        this.setBackground(Color.GRAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("AGENDA VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        this.add(date);
    }

    /**
     * Constructs an agenda view
     *
     * @param startDate - the beginning date within the agenda
     * @param endDate   - the beginning date within the agenda
     */
    public AgendaView(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.viewMetric = "agenda";
    }

    /**
     * returns events within the agenda view
     *
     * @param model - the calendar model
     * @return agendaEvents - the events from the model that will be in the agenda view
     */
    public ArrayList<Event> getAgendaEvents(CalendarModel model) {
        // loop through events in the model
        // and determine which events fit the criteria
        // then return those events

        ArrayList<Event> agendaEvents = new ArrayList<>();
        for (Event e : model.getData()) {
            if (e.getDate().isAfter(startDate) && e.getDate().isBefore(endDate) || e.getDate().equals(startDate) || e.getDate().equals(endDate)) {
                agendaEvents.add(e);
            }
        }
        return agendaEvents;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * @return viewMetric - the day view as a String
     */
    public String getViewMetric() {
        return viewMetric;
    }

}
