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
	JTextArea events;
	
	
	DayView(CalendarModel calModel) {
		this.model = calModel;
		this.viewMetric = "day";
		this.highlightedDate = calModel.getHighlightedDate();
		returnView();
		
	}
	
	public void returnView() {
		this.removeAll();
		this.revalidate();
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(700, 470));
		// include panel display methods here
		events = new JTextArea();
		
		if (!getDayEvents().isEmpty()) {
			events.setText(model.format(getDayEvents()));
		}
		else {
			events.setText("No events today. ");
		}
		this.add(events,BorderLayout.CENTER);
        
        /**
        this.setPreferredSize(new Dimension(700, 470));
        this.setBackground(Color.BLUE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("DAY VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        this.add(date);
        */
		 
	}
	
	public ArrayList<Event> getDayEvents() {
		ArrayList<Event> dayEvents = new ArrayList<>();
		// add events
		for (Event e: model.getData()) {
			if(e.getDate().equals(highlightedDate)) {
				dayEvents.add(e);
			}	
		}
		return dayEvents;
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// display contents
		// if the model's
		// view metric matches with this one
		if (model.getMetric().equalsIgnoreCase(viewMetric)) {
			returnView();
			System.out.println("This is the day view");
		}
		else {
			// don't display anything
		}
		
		

	}

}
