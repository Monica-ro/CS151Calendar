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
	public Event(String name, int year, int startMonth, int endMonth, String daysofTheWeek, TimeInterval ti, boolean isRecurring) {
		this.name = name;
		this.year = year;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.daysofTheWeek = daysofTheWeek;
		this.ti = ti;
		//this.date = date;
		this.isRecurring = true;
		
		
	}

	
	
//	Constructor for one-time events
	/**
	Constructs a one-time event
	*/
	public Event(String name, String daysofTheWeek, TimeInterval ti, LocalDate date, boolean isRecurring) {
		//TODO - need to figure out way to convert int day of week to String, may need an array or method
		// same for start month and end month
		this.name = name;
		this.daysofTheWeek = daysofTheWeek;
		this.ti = ti;
		this.date = date;
		this.isRecurring = false;
		this.startMonth = date.getMonthValue();
		this.endMonth = date.getMonthValue();
		this.year = date.getYear();
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
	 *
	 Returns the date of a one-time event.
	@return date - the event's date
	*/
	public LocalDate getDate() {
		if (isRecurring==false) {
			return date;
		}
	}


	/**
	 Returns the start month interval of an event.
	@return startMonth - the start month 
	*/
	public int getStartMonth() {
		return startMonth;
	}


	/**
	 * TODO
	Returns the end month interval of a recurring event.
	@return endMonth - the end month 
	*/
	public int getEndMonth() {
		return endMonth;
	}

	/**
	 TODO
	 Returns the year of an event.
	@return year - the time interval of an event, which contains the start time and end time.
	*/
	public int getYear() {
		return year;
	}

//	compareTo method - TODO
	@Override
	public int compareTo(Event that) {
		// TODO Auto-generated method stub
		int dateComp = this.date.compareTo(that.date);
		int nameComp = this.name.compareTo(that.name);
		int tiComp = this.ti.compareTo(that.ti);
		
		if(dateComp != 0) {
			 return dateComp;
		}
		
		 else if (tiComp != 0) {
				return tiComp;
			}
		 
		else {
			return nameComp;
		}
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
		return name.hashCode() + daysofTheWeek.hashCode() + ti.hashCode() + date.hashCode() + isRecurring + startMonth + endMonth + year;
	}


	@Override
	public String toString() {
		// TODO
		return name + " " + daysofTheWeek + " " + ti;
		/*if(isRecurring==true) {
			return name + " " + daysofTheWeek + " " + ti;
		}
		else {
			return "Event [name=" + name + ", daysofTheWeek=" + daysofTheWeek + ", date=" + date + ", isRecurring="
					+ isRecurring + ", startMonth=" + startMonth + ", endMonth=" + endMonth + ", year=" + year + "]";
		}
		*/
	}
}
