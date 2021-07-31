import java.time.*;

/**
 * 
 */

/**
 * @author Monica Orme, Nicholas Fong
 *
 */
/**
 * The TimeInterval class represents an interval of time and suitable for events
 */

public class TimeInterval implements Comparable<TimeInterval>{
    private LocalTime startTime;
    private LocalTime endTime;
    private String startTimeString;
    private String endTimeString;

    /**
     * Constructor for TimeInterval
     *
     * @param start  start time int
     * @param end    end time int
     * @param startT start time string
     * @param endT   end time int
     */
    public TimeInterval(LocalTime start, LocalTime end) {
        startTime = start;
        endTime = end;
        startTimeString = startTime.toString();
        endTimeString = endTime.toString();
    }

    /**
     * Returns the start time as a local time object
     *
     * @return start time 
     */
    public LocalTime getStart() {
        return startTime;
    }

    /**
     * Returns the end time int
     *
     * @return end time int
     */
    public LocalTime getEnd() {
        return endTime;
    }

    /**
     * Returns the start time as string
     *
     * @return start time string
     */
    public String startString() {
        return startTimeString;
    }

    /**
     * Returns the end time as string
     *
     * @return end time string
     */
    public String endString() {
        return endTimeString;
    }
    
    

    /**
     * Checks if the time interval is valid
     * @return boolean if time interval is invalid
     */
    public boolean overlap() {
       return startTime.isAfter(endTime);
    }
    
    @Override
	public int compareTo(TimeInterval that) {
		int stComp = this.startTime.compareTo(that.startTime);
		int etComp = this.endTime.compareTo(that.endTime);
		
		if (stComp != 0) {
			return stComp;
		}
		
		else {
			return etComp;
		}	
	}

	@Override
	public int hashCode() {
		return startTime.hashCode() + endTime.hashCode();
		
	}


	@Override
	public boolean equals(Object obj) {
		TimeInterval that = (TimeInterval)obj;
		return this.compareTo(that) == 0;
	}


	@Override
	public String toString() {
		return  startTime + " - " + endTime;
	}
	
}

