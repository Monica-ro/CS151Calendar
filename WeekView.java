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
    private LocalDate currentDate;
    private JTextArea events;
    private JLabel currentDateToDisplay;
    private char currentDayOfTheWeek;
	private char[] daysOfWeekConversion = {' ','M','T','W','H','F','A','S'};
	private char dow;

	
    /**
     * Ctor that initiates the Week View Panel
     * @param calModel calendar model being ran
     */
    WeekView(CalendarModel calModel) {
        this.model = calModel;
        this.viewMetric = "week";
        this.highlightedDate = calModel.getHighlightedDate();
        this.currentEventData = calModel.getData();
        this.currentDayOfTheWeek = daysOfWeekConversion[highlightedDate.getDayOfWeek().getValue()]; 
        returnView();
    }

    
    /**
     * Method used to create the panel for Week view
     */
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
    
    
    /**
     * Method use to evaluate if event contains day of week char
     * @param ev			Event with day of week
     * @param dow		day of week
     * @return boolean	
     */
    public boolean containsDayOfWeek(Event ev, char dow) {
        char[] daysOfWeekEvent = ev.getDaysofTheWeek().toCharArray();
        for (char c : daysOfWeekEvent) {
            if (dow == c)
                return true;
        }
        return false;
    }
    
    
    /**
     * Method that prints out the week of the date highlighted, starting at Sunday-Saturday
     * prints out in Week View Panel.
     * @return JScrollPane scroll with string of week view
     */
    public JScrollPane printCalendar() {
    	
    	JTextArea calendar = new JTextArea();
    	calendar.setSelectionStart(0);
    	calendar.setSelectionEnd(0);
    	
    	int daysFromSunday = MonthPanel.weekTool(highlightedDate.getDayOfWeek().name()) - 1;

        LocalDate sunday = highlightedDate.minusDays(daysFromSunday);
       
    	for (int i = 0; i < 7; i++) 
    	{
    		currentDate = sunday.plusDays(i);
            dow = daysOfWeekConversion[currentDate.getDayOfWeek().getValue()];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
            calendar.append("~~~" + formatter.format(currentDate) + "~~~" + "\n");
            calendar.append(model.format(getWeekEvents()) + "\n");
        }
    	
    	JScrollPane scroll = new JScrollPane(calendar);
    	scroll.setPreferredSize(new Dimension(700, 449));
    	
    	return scroll;
    	
    }

    
    /**
     * Method that checks arraylist of events from model for recurring/non-recurring dates, if it contains DayofWeek, or if time conflicts
     * @return ArrayList<Event> list of events 
     */
    public ArrayList<Event> getWeekEvents() {

        ArrayList<Event> weekEvents = new ArrayList<>();

        // add events
        for (Event e : model.getData()) 
        {
            if (e.getDate().equals(currentDate) && !e.getIsRecurring()) {
                weekEvents.add(e);
            }
            
            if (e.getIsRecurring() == true && currentDate.isAfter(e.getStartDate()) && currentDate.isBefore(e.getEndDate()) && containsDayOfWeek(e, dow) == true
                    || e.getIsRecurring() == true && currentDate.equals(e.getStartDate()) && containsDayOfWeek(e, dow) == true
                    || e.getIsRecurring() == true && currentDate.equals(e.getEndDate()) && containsDayOfWeek(e, dow) == true) {
                weekEvents.add(e);
            }
        }
        
        return weekEvents;

    }


    /**
     * Method that checks if metric is the same. It prints that its in week view
     * @Override
     */
    public void stateChanged(ChangeEvent e) {
        // display contents
        // if the model's
        // view metric matches with this one

        this.highlightedDate = model.getHighlightedDate();
        this.currentEventData = model.getData();
        
        this.currentDayOfTheWeek = daysOfWeekConversion[model.getHighlightedDate().getDayOfWeek().getValue()]; //ADDED

        if (model.getMetric().equalsIgnoreCase(viewMetric)) {
            returnView();
            System.out.println("This is the week view");
        }
        else {
            // don't display anything
        }
    }
    
    
    /*
     * Method getter that returns highlighted date
     */
    public LocalDate getHighlightedDate() {
		return highlightedDate;
	}
    
}



