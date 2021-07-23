import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * @author Nick Fong, Monica Orme
 *
 */

/**
 A calendar application.
 */

public class CalendarTester {
    public int firstOfMonth;

    /**
    Captures the current date upon construction of the calendar application.
    */
    public CalendarTester() {

        LocalDate cal = LocalDate.now(); // capture today
        LocalDate x = LocalDate.of(cal.getYear(), cal.getMonth(), 1);
        //firstOfMonth = getFirstOfMonth(x);


    }

    /**
    A tester method for the calendar application.
    @param args unused
    */
    public static void main(String[] args) {
        LocalDate cal = LocalDate.now(); // capture today
        LocalDate x = LocalDate.of(cal.getYear(), cal.getMonth(), 1);

        CalendarModel calendar = new CalendarModel(cal);
	NewCalendarModel model = new NewCalendarModel();

        JFrame frame = new JFrame();

        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 950;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        JLabel label = new JLabel("                            " + calendar.getMonth() + "  " + calendar
                .getYear() + "                            ");
        JPanel textTitle = new JPanel();
        textTitle.setBounds(0, 0, 700, 20);
        textTitle.add(label);


        JPanel monthButtons = new JPanel();
        monthButtons.setBounds(0, 30, 700, 30);
        JButton backMonth = new JButton("<");
        JButton forwardMonth = new JButton(">");
        monthButtons.add(backMonth);
        monthButtons.add(forwardMonth);


        JLabel label2 = new JLabel("S                   M                   T                   W                   TH                   F                   S");
        JPanel textTitle2 = new JPanel();
        textTitle2.setBounds(0, 65, 700, 20);
        textTitle2.add(label2);


        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 95, 700, 300);


        JPanel middlePanel = new JPanel();
        middlePanel.setBounds(0, 400, 700, 30);
        JButton day = new JButton("Day");
        JButton week = new JButton("Week");
        JButton month = new JButton("Month");
        JButton agenda = new JButton("Agenda");
        JButton create = new JButton("Create");
        JButton fromFile = new JButton("From File");
        middlePanel.add(day);
        middlePanel.add(week);
        middlePanel.add(month);
        middlePanel.add(agenda);
        middlePanel.add(create);
        middlePanel.add(fromFile);

        JPanel currentViewButtons = new JPanel();
        currentViewButtons.setBounds(0, 430, 700, 30);
        JButton today = new JButton("Today");
        JButton back = new JButton("<");
        JButton next = new JButton(">");
        currentViewButtons.add(today);
        currentViewButtons.add(back);
        currentViewButtons.add(next);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 450, 700, 950);

        int rows = 5;
        int cols = 7;

        topPanel.setLayout(new GridLayout(rows, cols));

        int monthDays = calendar.getDaysInMonth();
        int monthDayCount = monthDays;
        for (int i = 1; i <= 35; i++) {
            if (i < calendar.getFirstOfMonth(x)) {
                JButton button = new JButton();
                //add listeners and action handlers here
                topPanel.add(button);
            }
            else if (monthDays <= 0) {
                JButton button = new JButton();
                //add listeners and action handlers here
                topPanel.add(button);
            }
            else {
                monthDays = monthDays - 1;
                JButton button = new JButton(Integer.toString(monthDayCount - monthDays));
                //add listeners and action handlers here
                topPanel.add(button);
            }
        }

        frame.add(textTitle);
        frame.add(monthButtons);
        frame.add(textTitle2);
        frame.add(topPanel);
        frame.add(middlePanel);
        frame.add(currentViewButtons);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}

/**
    Reads a text file that contains recurring events and adds these events to the calendar model.
    Precondition - the file passed must be a text file
    @param f - a text file
    @throws IOException
    @throws UnsupportedOperationException - thrown if any line in the text file doesn't have 7 semicolons
    */
    public static void parsingEventsToCalendar(File f, NewCalendarModel model) throws  IOException {
    	// check that the file type is a txt file
    	// if not, throw an error

    	
    	// create FileReader and Buffered Reader
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		// initialize date time formatters
		//DateTimeFormatter recurringformatter = DateTimeFormatter.ofPattern("E H:m M/d/yy");
    	//DateTimeFormatter recurringFormatter = DateTimeFormatter.ofPattern("H");
    	//DateTimeFormatter oneDateFormatter = DateTimeFormatter.ofPattern("M/d/yy");
		
    	boolean doneReading = false;
	   
   	        while (!doneReading) {
   	        	String name = br.readLine();
   	        	
   	        	if (name == null) {
   	        		doneReading = true; // break out of the loop
   				}
   	        	
   	        	else {
   	        		String eventText = br.readLine();
   	        		// split event info at space
   	        		String eventInfo[] = eventText.split(";");
	   	        	if (eventInfo.length!=7) {
	   	        		throw new UnsupportedOperationException("One of the lines in the file didn't contain 7 semicolons. \n Please reformat your text file. ");
	   	        	}
   	        	
	   	        	// parse event attributes 
   	        		String eventName = eventInfo[0];
   	        		int eventYear = Integer.parseInt(eventInfo[1]);
   	        		int eventStartMonth = Integer.parseInt(eventInfo[2]);
   	        		int eventEndMonth = Integer.parseInt(eventInfo[3]);
   	        		String eventDaysOfTheWeek = eventInfo[4];
   	        		LocalTime eventStartTime = LocalTime.parse(eventInfo[5]);
   	        		LocalTime eventEndTime = LocalTime.parse(eventInfo[6]);
	   	        	
	   	        	// construct an event
	   	        	// and store it in the calendar model
	   	        	TimeInterval ti = new TimeInterval(eventStartTime,eventEndTime);
	   	        	Event event = new Event(eventName, eventYear, eventStartMonth, eventEndMonth, eventDaysOfTheWeek, ti, true);
	   	        	model.addEvent(event);
   	        }
   	        }
   	        br.close();
   	        fr.close();
    	

	        System.out.println("Loading is done!");
	}

