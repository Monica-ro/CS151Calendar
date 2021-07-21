package project;

import java.time.*;

/**
* 
* @author Monica Orme
* @version 1.0 7/21/21
*/


/**
	An event containing attributes including a name, a date, and whether or not it is recurring. 
	This defines the type of data that will be collected by the Calendar Model.
*/
public class Event implements Comparable<Event> {
	private String name, daysofTheWeek;
	private TimeInterval ti;
	private LocalDate date;
	private boolean isRecurring;
	private int startMonth, endMonth, year;
	
	
	
	
//	Empty constructor
	/**
	Constructs an Empty event
	*/
	public Event() {
		
	}


//	Constructor for recurring
	/**
	Constructs a recurring event
	*/
	public Event(String name, String daysofTheWeek, TimeInterval ti, LocalDate date, boolean isRecurring,
			int startMonth, int endMonth, int year) {
		this.name = name;
		this.daysofTheWeek = daysofTheWeek;
		this.ti = ti;
		this.date = date;
		this.isRecurring = true;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.year = year;
	}

	
	
//	Constructor for one-time events
	/**
	Constructs a one-time event
	*/
	public Event(String name, String daysofTheWeek, TimeInterval ti, LocalDate date, boolean isRecurring,
			int startMonth, int endMonth, int year) {
		//TODO - need to figure out way to convert int day of week to String, may need an array or method
		// same for start month and end month
		this.name = name;
		this.daysofTheWeek = daysofTheWeek;
		this.ti = ti;
		this.date = date;
		this.isRecurring = false;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.year = year;
	}

	
//	Getters and setters for every attribute
	/**
	Returns the name of the event.
	@return name - the name of the event
	*/
	public String getName() {
		return name;
	}
	
	/**
	 Returns the time interval of an event.
	@return ti - the time interval of an event, which contains the start time and end time.
	*/
	public TimeInterval getTi() {
		return ti;
	}
	
	/**
	 Returns a boolean that indicates whether an event is recurring or one-time
	@return isRecurring - a boolean indicating whether an event is recurring or one-time
	*/
	public boolean isRecurring() {
		return isRecurring;
	}
	
	/**
	 * TODO
	 Returns the days of the week of an event.
	@return daysofTheWeek - the time interval of an event, which contains the start time and end time.
	*/
	public String getDaysofTheWeek() {
		return daysofTheWeek;
	}


	/**
	 * TODO
	 Returns the time interval of an event.
	@return ti - the time interval of an event, which contains the start time and end time.
	*/
	public LocalDate getDate() {
		return date;
	}


	/**
	 * TODO
	 Returns the time interval of an event.
	@return ti - the time interval of an event, which contains the start time and end time.
	*/
	public int getStartMonth() {
		return startMonth;
	}


	/**
	 * TODO
	 Returns the time interval of an event.
	@return ti - the time interval of an event, which contains the start time and end time.
	*/
	public int getEndMonth() {
		return endMonth;
	}

	/**
	 TODO
	 Returns the time interval of an event.
	@return ti - the time interval of an event, which contains the start time and end time.
	*/
	public int getYear() {
		return year;
	}

//	compareTo method - TODO
	/**
	Returns an int
	@param that
	@return int - 
	*/
	@Override
	public int compareTo(Event that) {
		// TODO Auto-generated method stub
		return 0;
	}

//	Equals TODO
	@Override
	public boolean equals(Object obj) {
		Event that = (Event)obj;
		return this.compareTo(that)==0;
	}
	
	
//	Hashcode TODO
	@Override
	public int hashCode() {
		
	}


	@Override
	public String toString() {
		// TODO
		if(isRecurring==true) {
			return "Event [name=" + name + ", daysofTheWeek=" + daysofTheWeek + ", date=" + date + ", isRecurring="
					+ isRecurring + ", startMonth=" + startMonth + ", endMonth=" + endMonth + ", year=" + year + "]";
		}
		else {
			return "Event [name=" + name + ", daysofTheWeek=" + daysofTheWeek + ", date=" + date + ", isRecurring="
					+ isRecurring + ", startMonth=" + startMonth + ", endMonth=" + endMonth + ", year=" + year + "]";
		}
	}



	
	
//	toString
	

	

}
