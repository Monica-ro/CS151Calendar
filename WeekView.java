import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WeekView extends JPanel implements ChangeListener {
	
	private CalendarModel model;
	private String viewMetric;
	private LocalDate highlightedDate;
	JTextArea events;
	
    WeekView(CalendarModel calModel) {
		this.model = calModel;
		this.viewMetric = "week";
		this.highlightedDate = calModel.getHighlightedDate();
		returnView();
	}


    public void returnView() {
		this.removeAll();
				
		this.setPreferredSize(new Dimension(700, 470));
		this.setBackground(Color.YELLOW);
		
		events = new JTextArea();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("WEEK VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        this.add(date);
        
	}
    
    public ArrayList<Event> getDayEvents() {
		ArrayList<Event> weekEvents = new ArrayList<>();
		// add events
		for (Event e: model.getData()) {
			if(e.getDate().equals(highlightedDate)) {
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
    			if (model.getMetric().equalsIgnoreCase(viewMetric)) {
    				returnView();
    				System.out.println("This is the week view");
    			}
    			else {
    				// don't display anything
    			}
    			
    			
    }
}



