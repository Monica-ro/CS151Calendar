import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreateView extends JPanel implements ChangeListener, ActionListener {

    private LocalDate startDate;
    private LocalDate endDate;
    private String viewMetric;
    private CalendarModel model;
    private LocalDate highlightedDate;
    private ArrayList<Event> currentEventData;
    JTextArea events;
    JTextField eventName, weekDay, startTime, endTime, eventDate;


    /**
     * Ctor that initiates the Create View Panel
     * @param calModel calendar model being ran
     */
    CreateView(CalendarModel calModel) {
        this.model = calModel;
        this.viewMetric = "create";
        this.highlightedDate = calModel.getHighlightedDate();
        returnView();

    }

    /**
     * Ctor that passes LocalDate parameters for views instance variables
     * @param startDate
     * @param endDate
     */
    public CreateView(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.viewMetric = "create";
    }


    /**
     * Method used to create the panel for Create view. Creates all JtextAreas, JLabels and Buttons
     */
    public void returnView() {
        this.removeAll();

        //Primary Panel
        this.setLayout(new GridLayout(10, 1));
        this.setPreferredSize(new Dimension(700, 443));
        this.setBackground(Color.PINK);

        //Title of View and Highlighted Date Section
        JPanel dateholderPanel = new JPanel();
        dateholderPanel.setBackground(Color.PINK);
        this.add(dateholderPanel);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
        JLabel date = new JLabel("CREATE VIEW  " + formatter.format(model.getHighlightedDate()).toString());
        highlightedDate = LocalDate.parse(formatter.format(model.getHighlightedDate()).toString(), formatter);
        this.add(date);

        //Name of Event Section
        JPanel nameOfEventPanel = new JPanel();
        nameOfEventPanel.setBackground(Color.PINK);
        this.add(nameOfEventPanel);
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Name of Event : ");
        nameOfEventPanel.add(nameLabel);
        JTextField nameOfEventTextField = new JTextField();
        nameOfEventTextField.setPreferredSize(new Dimension(250, 40));
        eventName = nameOfEventTextField;
        nameOfEventPanel.add(nameOfEventTextField);
        
        //Date of Event Section
        JPanel dateOfEventPanel = new JPanel();
        dateOfEventPanel.setBackground(Color.PINK);
        this.add(dateOfEventPanel);
        JLabel dateLabel = new JLabel();
        dateLabel.setText("Date of Event (MM/dd/yyyy) : ");
        dateOfEventPanel.add(dateLabel);
        JTextField dateOfEventTextField = new JTextField();
        dateOfEventTextField.setPreferredSize(new Dimension(250, 40));
        eventDate = dateOfEventTextField;
        dateOfEventPanel.add(dateOfEventTextField);

        //StartTime of Event Section
        JPanel startTimePanel = new JPanel();
        startTimePanel.setBackground(Color.PINK);
        this.add(startTimePanel);
        JLabel startTimeLabel = new JLabel();
        startTimeLabel.setText("StartTime of Event (00:00) : ");
        startTimePanel.add(startTimeLabel);
        JTextField startTimeTextField = new JTextField();
        startTimeTextField.setPreferredSize(new Dimension(250, 40));
        startTime = startTimeTextField;
        startTimePanel.add(startTimeTextField);

        //EndTime of Event Section
        JPanel endTimePanel = new JPanel();
        endTimePanel.setBackground(Color.PINK);
        this.add(endTimePanel);
        JLabel endTimeLabel = new JLabel();
        endTimeLabel.setText("EndTime of Event (00:00) : ");
        endTimePanel.add(endTimeLabel);
        JTextField endTimeTextField = new JTextField();
        endTimeTextField.setPreferredSize(new Dimension(250, 40));
        endTime = endTimeTextField;
        endTimePanel.add(endTimeTextField);

        //Submit Button Section
        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(Color.PINK);
        this.add(submitPanel);
        JButton submitBtn = new JButton("Create Event");
        submitBtn.setBounds(10, 80, 80, 25);
        submitBtn.addActionListener(this);
        submitPanel.add(submitBtn);

    }


    /**
     * Method that adds events from calendar model to an arraylist that have startdate is before enddate 
     * @param model Calendar Model of the program
     * @return
     */
    public ArrayList<Event> createEvents(CalendarModel model) {
        // loop through events in the model
        // and determine which events fit the criteria
        // then return those events

        ArrayList<Event> createEvents = new ArrayList<>();
        for (Event e : model.getData()) {
            if (e.getDate().isAfter(startDate) && e.getDate().isBefore(endDate) || e.getDate().equals(startDate) || e.getDate().equals(endDate)) {
                createEvents.add(e);
            }
        }
        return createEvents;
    }


    /**
     * Method that checks if metric is the same. It prints that its in create view
     * @Override
     */
    public void stateChanged(ChangeEvent e) {

        // display contents
        // if the model's
        // view metric matches with this one
    	
    	this.highlightedDate = model.getHighlightedDate();
        this.currentEventData = model.getData();
    	
        if (model.getMetric().equalsIgnoreCase(viewMetric)) {
            returnView();
            System.out.println("This is the create view");
        }
        else {
            // don't display anything
        }

    }


    /**
     * Method used when the Create Event button is clicked
     * This method reads all JTextAreas and creates an Event and adds it to models arraylist
     * @Override
     */
    public void actionPerformed(ActionEvent e) {

        //eventName startTime, endTime, eventDate
        String name = eventName.getText();
        String start = startTime.getText();
        String end = endTime.getText();
        String date = eventDate.getText();
        
        //parse date and convert to int
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));
        
        if(month<=0 || month>12 || day<=0 || day>31)
        {
        	System.out.println("Invalid Date. Please input correct date");
        	return;
        }
        
        LocalDate dt = LocalDate.of(year, month, day);

        //parse hr and min to check time
        String starthr = start.substring(0, 2);
        String startmin = start.substring(3, 5);
        String endhr = end.substring(0, 2);
        String endmin = end.substring(3, 5);

        //convert to int
        int sHr = Integer.parseInt(starthr);
        int sMin = Integer.parseInt(startmin);
        int eHr = Integer.parseInt(endhr);
        int eMin = Integer.parseInt(endmin);

        if (sHr > 23 || sMin > 59 || eHr > 23 || eMin > 59) {
            System.out.println("Invalid time. Please input correct time");
            return;
        }

        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime starttime = LocalTime.parse(start, timeformat);
        LocalTime endtime = LocalTime.parse(end, timeformat);
        //TimeInterval ti = new TimeInterval(starttime, endtime);
        DayOfWeek dow = DayOfWeek.from(dt);

        Event event = new Event(name, dow.name(), starttime, endtime, dt, false);
        model.addEvent(event);
        System.out.println("Event was Created");
        System.out.println(model.format(model.getData())); //prints the added event

    }

    /**
     * Method getter that returns viewMetric
     * @return String viewMetric
     */
    public String getViewMetric() {
        return viewMetric;
    }

}

