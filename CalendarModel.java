import java.time.LocalDate;
/**
 * @author Nick Fong, Monica Orme, Ben Gonzalez
 *
 */
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
An MVC based model for the Calendar application
*/


public class CalendarModel {
    private LocalDate currentDate;
    //private TreeMap<LocalDate, ArrayList<Event>> events;
//    private TreeSet<Event> events;
    private ArrayList<ChangeListener> listeners;

    public CalendarModel() {
 //       events = new TreeSet<>();
        listeners = new ArrayList<>();
    }
    
    /**
    Constructs the calendar model
    @param cd - current date
    */
    public CalendarModel(LocalDate cd) {
        currentDate = cd;
    }

    /**
    [METHOD DESCRIPTION
    
    @return  [FILL THIS IN]
    */
    public int getDaysInMonth() {
        return currentDate.lengthOfMonth();
    }

    /**
    [METHOD DESCRIPTION
    
    @return  [FILL THIS IN]
    */
    public String getMonth() {
        return currentDate.getMonth().toString();
    }

    /**
    [METHOD DESCRIPTION
    
    @return  [FILL THIS IN]
    */
    public int getYear() {
        return currentDate.getYear();
    }


    /**
    [METHOD DESCRIPTION
    
    @return  [FILL THIS IN]
    */
    public static int getFirstOfMonth(LocalDate date) {
        return weekTool(date.getDayOfWeek().toString());
    }

    /**
    [METHOD DESCRIPTION
    
    @return  [FILL THIS IN]
    */
    public static int weekTool(String day) {
        if (day.equals("SUNDAY")) {
            return 1;
        }
        else if (day.equals("MONDAY")) {
            return 2;
        }
        else if (day.equals("TUESDAY")) {
            return 3;
        }
        else if (day.equals("WEDNESDAY")) {
            return 4;
        }
        else if (day.equals("THURSDAY")) {
            return 5;
        }
        else if (day.equals("FRIDAY")) {
            return 6;
        }
        else {
            return 7;
        }
    }
    
    /**
    Constructs a DataModel object
    @return the data in an ArrayList
	 */
    /*
	 public ArrayList<Event> getData() {
	    return (ArrayList<Event>) (event.clone());
	 }
	 */
	
	 /**
	    Attach a listener to the Model
	    @param c - the listener
	 */
	 public void attach(ChangeListener c) {
	    listeners.add(c);
	 }
	
	 /**
	    Change the data in the model at a particular location
	    @param location the index of the field to change
	    @param value the new value
	 */
	 
	 /*
	 public void update(int location, double value) {
	    data.set(location, new Double(value));
	    for (ChangeListener l : listeners) {
	       l.stateChanged(new ChangeEvent(this));
	    }
	 }
	 */
}
