import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Monica Orme
 *
 */

/**
Displays the day view in the calendar.
 */
public class DayView extends JPanel implements ChangeListener {
	private CalendarModel model;
	private String viewMetric;
	private LocalDate highlightedDate;
	private ArrayList<Event> currentEventData;
	private JTextArea events;
	private JLabel currentDateToDisplay;
	private char currentDayOfTheWeek;
	private char[] daysOfWeekConversion = {' ','M','T','W','H','F','A','S'};
	
	
	
	/**
	Constructs the day view in the calendar.
	@param calModel - The calendar model of CalendarModel type
	 */
	DayView(CalendarModel calModel) {
		this.model = calModel;
		this.viewMetric = "day";
		this.highlightedDate = calModel.getHighlightedDate();
		this.currentEventData = calModel.getData();
		this.currentDayOfTheWeek = daysOfWeekConversion[highlightedDate.getDayOfWeek().getValue()];
		System.out.println(currentDayOfTheWeek);
		returnView();
		
	}
	
	/**
	Updates the view panel upon construction and whenever there is an update in the model if the user has selected day view.
	 */
	public void returnView() {
		this.removeAll();
		this.revalidate();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(700, 457));
		
		// include panel display methods here
		events = new JTextArea();
		currentDateToDisplay = new JLabel("Current date: " + highlightedDate);
		// + "\n Value of day: "+ highlightedDate.getDayOfWeek().getValue()
		
		// display events, if any
		if (!getDayEvents().isEmpty()) {
			events.setText(model.format(getDayEvents()));
		}
		else {
			events.setText("No events today");	
		}
		
		this.add(currentDateToDisplay,BorderLayout.NORTH);
		this.add(events,BorderLayout.CENTER);
		 
	}

	/**
	Checks if the highlighted date's day of the week matches with the recurring event
	
	@param ev - an Event
	@return a boolean where true indicates that the current date's (in the model) day of the week matches with the event and false otherwise
	 */
	public boolean containsDayOfWeek(Event ev) {
		char[] daysOfWeekEvent = ev.getDaysofTheWeek().toCharArray();
		for (char c: daysOfWeekEvent) {
			if(currentDayOfTheWeek==c)
				return true;
		}
		return false;
	}
	
	/**
	Obtains the list of events that occur on the current date
	@return a list of events that occur on the current date as ArrayList<Event>
	 */
	public ArrayList<Event> getDayEvents() {
		ArrayList<Event> dayEvents = new ArrayList<>();
		// add events
		for (Event e: currentEventData) {
			if(e.getIsRecurring()==false && e.getDate().equals(highlightedDate)) {
				dayEvents.add(e);
			}
			 if(e.getIsRecurring()==true && highlightedDate.isAfter(e.getStartDate())  && highlightedDate.isBefore(e.getEndDate()) && containsDayOfWeek(e)==true 
				|| e.getIsRecurring()==true && highlightedDate.equals(e.getStartDate())  && containsDayOfWeek(e)==true
				|| e.getIsRecurring()==true && highlightedDate.equals(e.getEndDate()) && containsDayOfWeek(e)==true) {
				dayEvents.add(e);
			}
		}
		return dayEvents;
		
	}
	
	/**
	@return the highlighted date in the panel, which is based on the model
	 */
	public LocalDate getHighlightedDate() {
		return highlightedDate;
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
			System.out.println("This is the day view");
		}
		else {
			// don't display anything
		}
	}

}
