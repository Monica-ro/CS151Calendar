import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        this.setBackground(Color.YELLOW);

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
    	
    	ArrayList<Event> events = getWeekEvents();
    
    	int daysFromSunday = MonthPanel.weekTool(highlightedDate.getDayOfWeek().name()) - 1;
    	LocalDate firstDayOfWeek = highlightedDate.minusDays(daysFromSunday);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
    	
    	ArrayList<Event> sunday = new ArrayList<Event>();
    	ArrayList<Event> monday = new ArrayList<Event>();
    	ArrayList<Event> tuesday = new ArrayList<Event>();
    	ArrayList<Event> wednesday = new ArrayList<Event>();
    	ArrayList<Event> thursday = new ArrayList<Event>();
    	ArrayList<Event> friday = new ArrayList<Event>();
    	ArrayList<Event> saturday = new ArrayList<Event>();
    	
    	for(Event e : events) {
    		if(e.getDaysofTheWeek().equals(DayOfWeek.SUNDAY.name())) { sunday.add(e); }
    		if(e.getDaysofTheWeek().equals(DayOfWeek.MONDAY.name())) { monday.add(e); }
    		if(e.getDaysofTheWeek().equals(DayOfWeek.TUESDAY.name())) { tuesday.add(e); }
    		if(e.getDaysofTheWeek().equals(DayOfWeek.WEDNESDAY.name())) { wednesday.add(e); }
    		if(e.getDaysofTheWeek().equals(DayOfWeek.THURSDAY.name())) { thursday.add(e); }
    		if(e.getDaysofTheWeek().equals(DayOfWeek.FRIDAY.name())) { friday.add(e); }
    		if(e.getDaysofTheWeek().equals(DayOfWeek.SATURDAY.name())) { saturday.add(e); }
    	}
    	
    	calendar.append("--------"+ formatter.format(firstDayOfWeek) + "---------" + "\n");
    	calendar.append(model.format(sunday) + "\n");
    	calendar.append("--------"+ formatter.format(firstDayOfWeek.plusDays(1)) + "---------" + "\n");
    	calendar.append(model.format(monday) + "\n");
    	calendar.append("--------"+ formatter.format(firstDayOfWeek.plusDays(2)) + "---------" + "\n");
    	calendar.append(model.format(tuesday) + "\n");
    	calendar.append("--------"+ formatter.format(firstDayOfWeek.plusDays(3)) + "---------" + "\n");
    	calendar.append(model.format(wednesday) + "\n");
    	calendar.append("--------"+ formatter.format(firstDayOfWeek.plusDays(4)) + "---------" + "\n");
    	calendar.append(model.format(thursday) + "\n");
    	calendar.append("--------"+ formatter.format(firstDayOfWeek.plusDays(5)) + "---------" + "\n");
    	calendar.append(model.format(friday) + "\n");
    	calendar.append("--------"+ formatter.format(firstDayOfWeek.plusDays(6)) + "---------" + "\n");
    	calendar.append(model.format(saturday) + "\n");
    	
    	JScrollPane scroll = new JScrollPane(calendar);
    	scroll.setPreferredSize(new Dimension(700, 449));
    	
    	return scroll;
    	
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



