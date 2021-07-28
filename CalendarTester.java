import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * @author Nick Fong
 */

/**
 * A calendar application.
 */

public class CalendarTester {
    public int firstOfMonth;
    public static LocalDate currentDate;
    public static JLabel label;
    public static JFrame frame;
    public static MonthPanel monthPanel;
    public static JPanel bottomPanel;
    public static JPanel topPanel;


    /**
     * Captures the current date upon construction of the calendar application.
     */
    public CalendarTester() {

    }

    public static void removeMonth() {
        monthPanel.removeAll();
        frame.revalidate();
    }


    /**
     * A tester method for the calendar application.
     *
     * @param args unused
     */
    public static void main(String[] args) {

        CalendarModel calModel = new CalendarModel();
        currentDate = calModel.getToday();
        frame = new JFrame();
        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 950;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        final Container contentPane = frame.getContentPane();
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));


        label = new JLabel("                            " + currentDate.getMonth() + "  " + currentDate
                .getYear() + "                            ");
        JPanel textTitle = new JPanel();
        textTitle.add(label);

        topPanel = new JPanel();
        monthPanel = new MonthPanel(currentDate);
        topPanel.add(monthPanel);

        JPanel monthButtons = new JPanel();
        JButton backMonth = new JButton("<");
        backMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.minusMonths(1);
                topPanel.remove(monthPanel);
                textTitle.removeAll();
                label = new JLabel("                            " + currentDate.getMonth() + "  " + currentDate
                        .getYear() + "                            ");
                textTitle.add(label);
                monthPanel = new MonthPanel(currentDate);
                topPanel.add(monthPanel);

                frame.revalidate();
            }
        });
        JButton forwardMonth = new JButton(">");
        forwardMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.plusMonths(1);
                //monthPanel.removeAll();
                topPanel.remove(monthPanel);
                textTitle.removeAll();
                label = new JLabel("                            " + currentDate.getMonth() + "  " + currentDate
                        .getYear() + "                            ");
                textTitle.add(label);
                monthPanel = new MonthPanel(currentDate);
                topPanel.add(monthPanel);

                frame.revalidate();
            }
        });
        monthButtons.add(backMonth);
        monthButtons.add(forwardMonth);


        JLabel label2 = new JLabel("S                   M                   T                   W                   TH                   F                   S");
        JPanel daysOfWeek = new JPanel();
        daysOfWeek.add(label2);

        JPanel viewButtons = new JPanel();


        bottomPanel = new JPanel();
        bottomPanel.add(new DayView(currentDate));


        JButton day = new JButton("Day");
        day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calModel.setMetric("day");
                bottomPanel.removeAll();
                bottomPanel.add(new DayView(currentDate));
                bottomPanel.revalidate();
                System.out.println(calModel.getMetric());
            }
        });


//        week.addActionListener(updateViewMetric("week", model));
//        agenda.addActionListener(updateViewMetric("agenda", model));
//        month.addActionListener(updateViewMetric("month", model));


        JButton week = new JButton("Week");
        week.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calModel.setMetric("week");
                bottomPanel.removeAll();
                bottomPanel.add(new WeekView(currentDate));
                bottomPanel.revalidate();
                System.out.println(calModel.getMetric());
            }
        });

        JButton month = new JButton("Month");
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calModel.setMetric("month");
                bottomPanel.removeAll();
                bottomPanel.add(new MonthView(currentDate));
                bottomPanel.revalidate();
                System.out.println(calModel.getMetric());
            }
        });

        JButton agenda = new JButton("Agenda");
        agenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calModel.setMetric("agenda");
                bottomPanel.removeAll();
                bottomPanel.add(new AgendaView(currentDate));
                bottomPanel.revalidate();
                System.out.println(calModel.getMetric());
            }
        });
        JButton create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calModel.setMetric("agenda");
                bottomPanel.removeAll();
                bottomPanel.add(new CreateView(currentDate));
                bottomPanel.revalidate();
                System.out.println(calModel.getMetric());
            }
        });
        JButton fromFile = new JButton("From File");
        viewButtons.add(day);
        viewButtons.add(week);
        viewButtons.add(month);
        viewButtons.add(agenda);
        viewButtons.add(create);
        viewButtons.add(fromFile);

        JPanel currentViewButtons = new JPanel();
        JButton today = new JButton("Today");
        JButton back = new JButton("<");
        JButton next = new JButton(">");
        currentViewButtons.add(today);
        currentViewButtons.add(back);
        currentViewButtons.add(next);


        frame.add(textTitle);
        frame.add(monthButtons);
        frame.add(daysOfWeek);
        frame.add(topPanel);
        frame.add(viewButtons);
        frame.add(currentViewButtons);
        frame.add(bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     Reads a text file that contains recurring events and adds these events to the calendar model.
     Precondition - the file passed must be a text file
     @param f - a text file
     @throws IOException
     @throws UnsupportedOperationException - thrown if any line in the text file doesn't have 7 semicolons
     */
//    public static void parsingEventsToCalendar(File f, CalendarModel model) throws IOException {
//        // check that the file type is a txt file
//        // if not, throw an error
//
//
//        // create FileReader and Buffered Reader
//        FileReader fr = new FileReader(f);
//        BufferedReader br = new BufferedReader(fr);
//        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("H");
//
//        // initialize date time formatters
//        //DateTimeFormatter recurringformatter = DateTimeFormatter.ofPattern("E H:m M/d/yy");
//        //DateTimeFormatter recurringFormatter = DateTimeFormatter.ofPattern("H");
//        //DateTimeFormatter oneDateFormatter = DateTimeFormatter.ofPattern("M/d/yy");
//
//        boolean doneReading = false;
//
//        while (!doneReading) {
//            String name = br.readLine();
//
//            if (name == null) {
//                doneReading = true; // break out of the loop
//            }
//
//            else {
//                String eventText = br.readLine();
//                // split event info at space
//                String eventInfo[] = eventText.split(";");
//                if (eventInfo.length!=7) {
//                    throw new UnsupportedOperationException("One of the lines in the file didn't contain 7 semicolons. \n Please reformat your text file. ");
//                }
//
//                // parse event attributes
//                else {
//                    String eventName = eventInfo[0];
//                    int eventYear = Integer.parseInt(eventInfo[1]);
//                    int eventStartMonth = Integer.parseInt(eventInfo[2]);
//                    int eventEndMonth = Integer.parseInt(eventInfo[3]);
//                    String eventDaysOfTheWeek = eventInfo[4];
//                    LocalTime eventStartTime = LocalTime.parse(eventInfo[5],dateformatter);
//                    LocalTime eventEndTime = LocalTime.parse(eventInfo[6],dateformatter);
//
//                    // construct an event
//                    // and store it in the calendar model
//                    TimeInterval ti = new TimeInterval(eventStartTime,eventEndTime);
//                    Event event = new Event(eventName, eventYear, eventStartMonth, eventEndMonth, eventDaysOfTheWeek, ti, true);
//                    model.addEvent(event);
//                }
//
//            }
//        }
//        br.close();
//        fr.close();
//        System.out.println(model.getData().toString());
//        System.out.println(model.getData().size());
//
//        System.out.println("Loading is done!");
//    }

    /**
     * Updates the view metric in the model
     *
     * @param metric - the view metric
     * @param model  - the calendar model
     */
    public static ActionListener updateViewMetric(String metric, CalendarModel model) {
        if (metric.equals("day")) {
            return event -> {
                model.setMetric("day");
            };
        }
        else if (metric.equals("agenda")) {
            return event -> {
                model.setMetric("agenda");
            };
        }
        else if (metric.equals("month")) {
            return event -> {
                model.setMetric("month");
            };
        }
        else {
            return event -> {
                model.setMetric("week");
            };
        }
    }

//    /**
//     * Returns the view that has been specified by the user, which has been captured by the current view metric in the model.
//     *
//     * @param metric - the view metric that is given by the model
//     * @return ChangeListener - gives the user-specified view
//     */
//    public static ChangeListener returnNewView(String metric, LocalDate highlightedDate) {
//        if (metric.equals("day")) {
//
//            bottomPanel.removeAll();
//            bottomPanel.add(new DayView(highlightedDate));
//            bottomPanel.revalidate();
//            return new DayView(highlightedDate);
//        }
//        else if(metric.equals("agenda")) {
//            return new AgendaView(highlightedDate);
//        }
//        else if(metric.equals("week")) {
//            return new WeekView(highlightedDate);
//        }
//        else if(metric.equals("view")) {
//            return new MonthView(highlightedDate);
//        }
//
//        return null;
//    }
    
    public static JPanel returnNewViewPanel (String metric, CalendarModel model) {
    	if(metric.equals("day")) {
    		return dayViewPanel;
    	}
    	else if(metric.equals("agenda")) {
    		return agendaViewPanel;
    	}
    	else if(metric.equals("week")) {
    		return weekViewPanel;
    	}
    	else {
    		return monthViewPanel;
    	}
    	
    	}
    } // close tester

}

