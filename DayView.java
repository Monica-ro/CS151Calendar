import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Monica Orme
 *
 */
public class DayView extends JPanel implements ChangeListener {
	private CalendarModel model;
	private String viewMetric;
	private LocalDate highlightedDate;
	private ArrayList<Event> currentEventData;
	private JTextArea events;
	private JLabel currentDateToDisplay;
	
	
	DayView(CalendarModel calModel) {
		this.model = calModel;
		this.viewMetric = "day";
		this.highlightedDate = calModel.getHighlightedDate();
		this.currentEventData = calModel.getData();
		returnView();
		
	}
	
	public void returnView() {
		this.removeAll();
		this.revalidate();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(700, 457));
		
		// include panel display methods here
		events = new JTextArea();
		currentDateToDisplay = new JLabel("Current date: " + highlightedDate);
		if (!getDayEvents().isEmpty()) {
			events.setText(model.format(getDayEvents()));
		}
		else {
			events.setText("No events today");	
		}
		this.add(currentDateToDisplay,BorderLayout.NORTH);
		this.add(events,BorderLayout.CENTER);
		 
	}
	
	public ArrayList<Event> getDayEvents() {
		ArrayList<Event> dayEvents = new ArrayList<>();
		// add events
		for (Event e: currentEventData) {
			if(e.getDate().equals(highlightedDate)) {
				dayEvents.add(e);
			}	
		}
		return dayEvents;
		
	}
	
	
	
	
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
		
		if (model.getMetric().equalsIgnoreCase(viewMetric)) {
			returnView();
			System.out.println("This is the day view");
		}
		else {
			// don't display anything
		}
		
		

	}

}
