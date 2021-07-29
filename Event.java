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
	private LocalTime startTime, endTime;
	private LocalDate date, startDate, endDate;
	private boolean isRecurring;
	//private int startMonth, endMonth, year;
	
	
	
	
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
	public Event(String name, LocalDate startDate, LocalDate endDate, String daysofTheWeek, LocalTime startTime, LocalTime endTime, boolean isRecurring) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.daysofTheWeek = daysofTheWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = startDate;
		this.isRecurring = true;
		
		
	}

	
	
//	Constructor for one-time events
	/**
	Constructs a one-time event
	*/
	public Event(String name, String daysofTheWeek, LocalTime startTime, LocalTime endTime, LocalDate date, boolean isRecurring) {
		//TODO - need to figure out way to convert int day of week to String, may need an array or method
		// same for start month and end month
		this.name = name;
		this.daysofTheWeek = daysofTheWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.isRecurring = false;
		this.startDate = date;
		this.endDate = date;
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
	 Returns a boolean that indicates whether an event is recurring or one-time
	@return isRecurring - a boolean indicating whether an event is recurring or one-time
	*/
	public boolean getIsRecurring() {
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
	 *
	 Returns the date of a one-time event.
	@return date - the event's date
	*/
	public LocalDate getDate() {
		return date;
	}

	/**
	 *
	 Returns the start date of an event.
	@return startDate - the event's start date
	*/
	public LocalDate getStartDate() {
		return startDate;
	}


	/**
	 *
	 Returns the start date of an event.
	@return endDate - the event's end date
	*/
	public LocalDate getEndDate() {
		return endDate;
	}
	

//	compareTo method - TODO
	@Override
	public int compareTo(Event that) {
		int nameComp = this.name.compareTo(that.name);
		int startTimeComp = this.startTime.compareTo(that.startTime);
		int endTimeComp = this.endTime.compareTo(that.endTime);
		
		if (startTimeComp != 0) {
			return startTimeComp;
		} 
		if (endTimeComp != 0) {
			return endTimeComp;
		}
		else {
			return nameComp;
		}
	}

	@Override
	public boolean equals(Object obj) {
		Event that = (Event)obj;
		return this.compareTo(that)==0;
	}
	
	
	@Override
	public int hashCode() {
		return name.hashCode() + daysofTheWeek.hashCode() + startTime.hashCode() + endTime.hashCode() + date.hashCode() + startDate.hashCode() + endDate.hashCode();
	}


	@Override
	public String toString() {
		if(isRecurring==true) {
			return name + " " + daysofTheWeek + " " + startDate + "-"  + endDate + "   "+ startTime + "-" + endTime;
		}
		else {
			return name + " " + daysofTheWeek + " " + startTime + endTime;
		}
		
	}


	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}




	
}
