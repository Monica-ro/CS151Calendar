package project;

/**
 * @author  Monica Orme
 *
 */

import java.util.*;
import javax.swing.event.*;

/**
An MVC based model for the Calendar application
*/


public class CalendarModel {
   // private TreeMap<LocalDate, ArrayList<Event>> events;
    private TreeSet<Event> events;
    private ArrayList<ChangeListener> listeners;

    public CalendarModel() {
        events = new TreeSet<>();
        listeners = new ArrayList<>();
    }
    
    
    // accessor
    /**
    Constructs a DataModel object
    @return the event data in an ArrayList
	 */
    
	 public ArrayList<Event> getData() {
		return new ArrayList<>(events);
	    //return (ArrayList<Event>) (events.clone());
	 }
	 
	
    // mutator
	 /**
    Adds the event to the calendar model and notifies all change listeners
    in the model about this update. 
    @param e - an event
 */
 
	 public void addEvent(Event e) {
	    //events.put(e.getDate(), add(e));
		 events.add(e);
	    for (ChangeListener l : listeners) {
	       l.stateChanged(new ChangeEvent(this));
	    }
	 }
    
	 /**
	    Attach a listener to the Model
	    @param c - the listener
	 */
	 public void attach(ChangeListener c) {
	    listeners.add(c);
	 }

}

