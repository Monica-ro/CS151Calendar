import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AgendaView extends JPanel implements ChangeListener {
    private CalendarModel model;
    private String viewMetric;
    private LocalDate dateIterator;
    private ArrayList<Event> currentEventData;
    private JTextArea events;
    private JLabel currentDateToDisplay;
    private LocalDate localStart;
    private LocalDate localEnd;
    private JScrollPane calendar;


    AgendaView(CalendarModel calModel) {
        this.model = calModel;
        this.viewMetric = "agenda";
        this.currentEventData = calModel.getData();
        this.setPreferredSize(new Dimension(700, 455));

        returnView();

    }

    public void returnView() {

        this.removeAll();
        this.revalidate();

        JLabel start = new JLabel("Start Date (MM/DD/YYYY)");
        this.add(start);
        JTextField startDate = new JTextField();
        startDate.setPreferredSize(new Dimension(100, 35));
        this.add(startDate);
        JLabel end = new JLabel("End Date (MM/DD/YYYY)");
        this.add(end);
        JTextField endDate = new JTextField();
        endDate.setPreferredSize(new Dimension(100, 35));
        this.add(endDate);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(event -> {
            try {
                this.remove(calendar);
                this.revalidate();
                String startInput = startDate.getText();
                localStart = LocalDate.of(Integer.parseInt(startInput.substring(6, 10)), Integer.parseInt(startInput.substring(0, 2)), Integer.parseInt(startInput.substring(3, 5)));
                String endInput = endDate.getText();
                localEnd = LocalDate.of(Integer.parseInt(endInput.substring(6, 10)), Integer.parseInt(endInput.substring(0, 2)), Integer.parseInt(endInput.substring(3, 5)));

                if (localStart.isAfter(localEnd) || localStart.equals(localEnd)) {
                    throw new Exception("Invalid Input");
                }

                this.add(printAgenda(localStart, localEnd));
                this.revalidate();
                this.repaint();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.add(searchButton);

        calendar = printAgenda(dateIterator, dateIterator);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                calendar.getVerticalScrollBar().setValue(0);
            }
        });

        this.add(calendar);

        calendar.revalidate();
        calendar.repaint();

    }


    public JScrollPane printAgenda(LocalDate s, LocalDate e) {
        JTextArea agenda = new JTextArea();
        JScrollPane scroll = new JScrollPane(agenda);
        agenda.setSelectionStart(0);
        agenda.setSelectionEnd(0);

        dateIterator = localStart;
        if (localStart == null || localEnd == null) {
            return scroll;
        }
        while (dateIterator.isBefore(localEnd) || dateIterator.equals(localEnd)) {
            if (!getMonthEvents().isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
                agenda.append("---" + formatter.format(dateIterator) + "---" + "\n");
                agenda.append(model.format(getMonthEvents()) + "\n");
            }
            dateIterator = dateIterator.plusDays(1);

        }


        agenda.append(model.format(getMonthEvents()) + "\n");
        scroll.setPreferredSize(new Dimension(700, 400));
        return scroll;

    }


    public ArrayList<Event> getMonthEvents() {
        ArrayList<Event> monthEvents = new ArrayList<>();

        // add events
        for (Event e : model.getData()) {
            if (e.getDate().equals(dateIterator)) {
                monthEvents.add(e);
            }
        }

        return monthEvents;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // display contents
        // if the model's
        // view metric matches with this one
        this.currentEventData = model.getData();

        if (model.getMetric().equalsIgnoreCase(viewMetric)) {
            returnView();
            System.out.println("This is the agenda view");
        }
        else {
            // don't display anything
        }

    }
}
