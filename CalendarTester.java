import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ben Gonzalez, Monica Orme, Nick Fong
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
    static JPanel viewButtons;
    static DayView dayViewPanel;
    static AgendaView agendaViewPanel;
    static WeekView weekViewPanel;
    static MonthView monthViewPanel;
    static CreateView createViewPanel;
    static JPanel monthButtons;
    static JPanel currentViewButtons;


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

        // initialize view panels
        dayViewPanel = new DayView(calModel);
        agendaViewPanel = new AgendaView(calModel);
        weekViewPanel = new WeekView(calModel);
        monthViewPanel = new MonthView(calModel);
        createViewPanel = new CreateView(calModel);

        // attach all view panels to the model
        calModel.attach(dayViewPanel);
        calModel.attach(agendaViewPanel);
        calModel.attach(weekViewPanel);
        calModel.attach(monthViewPanel);
        calModel.attach(createViewPanel);


        label = new JLabel("                            " + currentDate.getMonth() + "  " + currentDate
                .getYear() + "                            ");
        JPanel textTitle = new JPanel();
        textTitle.add(label);

        topPanel = new JPanel();
        monthPanel = new MonthPanel(calModel, currentDate);
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
                monthPanel = new MonthPanel(calModel, currentDate);
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
                monthPanel = new MonthPanel(calModel, currentDate);
                topPanel.add(monthPanel);

                frame.revalidate();
            }
        });

        JButton darkMode = new JButton("Dark Mode");
        darkMode.addActionListener(event -> {
        	monthButtons.setBackground(new DarkMode().updateBackground());
        	topPanel.setBackground(new DarkMode().updateBackground());
        	bottomPanel.setBackground(new DarkMode().updateBackground());
        	viewButtons.setBackground(new DarkMode().updateBackground());
        	currentViewButtons.setBackground(new DarkMode().updateBackground());
            frame.revalidate();

        });

        JButton lightMode = new JButton("Light Mode");
        lightMode.addActionListener(event -> {
            monthButtons.setBackground(new LightMode().updateBackground());
            topPanel.setBackground(new LightMode().updateBackground());
        	bottomPanel.setBackground(new LightMode().updateBackground());
        	viewButtons.setBackground(new LightMode().updateBackground());
        	currentViewButtons.setBackground(new LightMode().updateBackground());
            frame.revalidate();
        });
        
        JButton resetMode = new JButton("Reset Mode");
        resetMode.addActionListener(event -> {
            monthButtons.setBackground(monthPanel.getBackground());
            topPanel.setBackground(monthPanel.getBackground());
        	bottomPanel.setBackground(monthPanel.getBackground());
        	viewButtons.setBackground(monthPanel.getBackground());
        	currentViewButtons.setBackground(monthPanel.getBackground());
            frame.revalidate();
        });

        monthButtons.add(darkMode);
        monthButtons.add(lightMode);
        monthButtons.add(resetMode);
        monthButtons.add(backMonth);
        monthButtons.add(forwardMonth);


        JLabel label2 = new JLabel("S                   M                   T                   W                   TH                   F                   S");
        JPanel daysOfWeek = new JPanel();
        daysOfWeek.add(label2);

        JPanel viewButtons = new JPanel();


        bottomPanel = new JPanel();
        bottomPanel.add(dayViewPanel, BorderLayout.CENTER);


        JButton day = new JButton("Day");
        day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bottomPanel.removeAll();
                calModel.setMetric("day");
                bottomPanel.add(dayViewPanel, BorderLayout.CENTER);
                bottomPanel.revalidate();
                bottomPanel.repaint();

                System.out.println(calModel.getMetric());
            }
        });

        JButton week = new JButton("Week");
        week.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                calModel.setMetric("week");
                bottomPanel.add(weekViewPanel, BorderLayout.CENTER);
                bottomPanel.revalidate();
                bottomPanel.repaint();

                System.out.println(calModel.getMetric());
            }
        });

        JButton month = new JButton("Month");
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calModel.setMetric("month");
                bottomPanel.removeAll();
                bottomPanel.add(monthViewPanel, BorderLayout.CENTER);
                bottomPanel.revalidate();
                bottomPanel.repaint();

                System.out.println(calModel.getMetric());
            }
        });

        JButton agenda = new JButton("Agenda");
        agenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.removeAll();
                calModel.setMetric("agenda");
                bottomPanel.add(agendaViewPanel, BorderLayout.CENTER);
                bottomPanel.revalidate();
                bottomPanel.repaint();

                System.out.println(calModel.getMetric());
            }
        });
        JButton create = new JButton("Create");
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bottomPanel.removeAll();
                calModel.setMetric("create");
                bottomPanel.add(createViewPanel, BorderLayout.CENTER);
                bottomPanel.revalidate();
                bottomPanel.repaint();

                System.out.println(calModel.getMetric());

            }
        });
        JButton fromFile = new JButton("From File");
        fromFile.addActionListener(event -> {
            // specify file path
            JFileChooser fileChoice = new JFileChooser();
            boolean eventsRead = false;
            int returnValue = fileChoice.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChoice.getSelectedFile().getAbsolutePath();
                // pass the path
                File f = new File(filePath);
                // parse the events from the file
                try {
                    parsingEventsToCalendar(f, calModel);
                    eventsRead = true;
                } catch (IOException e) {
                    System.out.println("An IO Exception occurred. ");
                } catch (UnsupportedOperationException e) {
                    e.getMessage();
                }
            }
            // make button invisible after clicking on it
            if (fromFile == (JButton) event.getSource() && eventsRead == true) {
                fromFile.setEnabled(false);

            }
        });


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

        // add functionality to the buttons
        back.addActionListener(event -> {
            // check the current metric
            String metric = calModel.getMetric();
            // adjust the current date pointer accordingly
            if (metric.equalsIgnoreCase("day")) {
                calModel.setHighlightedDate(calModel.getHighlightedDate().minusDays(1));
                //dayViewPanel.setHighlightedDate(calModel.getHighlightedDate());
                System.out.println("Day view panel's higlighted date: " + dayViewPanel.getHighlightedDate());
                bottomPanel.add(dayViewPanel, BorderLayout.CENTER);
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());
            }
            else if (metric.equalsIgnoreCase("month")) {
                calModel.setHighlightedDate(calModel.getHighlightedDate().minusMonths(1));
                bottomPanel.add(monthViewPanel, BorderLayout.CENTER);
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());
            }
            else if (metric.equalsIgnoreCase("week")) {
                calModel.setHighlightedDate(calModel.getHighlightedDate().minusWeeks(1));
                bottomPanel.add(weekViewPanel, BorderLayout.CENTER);
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());
            }
            else {
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());
            }
        });

        next.addActionListener(event -> {
            // check the current metric
            String metric = calModel.getMetric();
            // adjust the current date pointer accordingly
            if (metric.equalsIgnoreCase("day")) {
                calModel.setHighlightedDate(calModel.getHighlightedDate().plusDays(1));
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());
            }
            else if (metric.equalsIgnoreCase("month")) {
                calModel.setHighlightedDate(calModel.getHighlightedDate().plusMonths(1));
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());
            }
            else if (metric.equalsIgnoreCase("week")) {
                calModel.setHighlightedDate(calModel.getHighlightedDate().plusWeeks(1));
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());
            }
            else {
                System.out.println("The current date is: " + calModel.getHighlightedDate().toString());

            }
        });
        today.addActionListener(event -> {
            // change the highlighted date to the today's date
            calModel.setHighlightedDate(calModel.getToday());
            System.out.println("The current date is: " + calModel.getHighlightedDate());
        });

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
     * Reads a text file that contains recurring events and adds these events to the calendar model.
     * Precondition - the file passed must be a text file
     *
     * @param f - a text file
     * @throws IOException
     * @throws UnsupportedOperationException - thrown if any line in the text file doesn't have 7 semicolons
     */
    public static void parsingEventsToCalendar(File f, CalendarModel model) throws IOException {
        // check that the file type is a txt file
        // if not, throw an error


        // create FileReader and Buffered Reader
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("H");
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        // initialize date time formatters
        //DateTimeFormatter recurringformatter = DateTimeFormatter.ofPattern("E H:m M/d/yy");
        //DateTimeFormatter recurringFormatter = DateTimeFormatter.ofPattern("H");
        //DateTimeFormatter oneDateFormatter = DateTimeFormatter.ofPattern("M/d/yy");

        boolean doneReading = false;

        while (!doneReading) {
            String line = br.readLine();

            if (line == null) {
                doneReading = true; // break out of the loop
            }

            else {
                // split event info at space
                String eventInfo[] = line.split(";");
                if (eventInfo.length != 7) {
                    throw new UnsupportedOperationException("One of the lines in the file didn't contain 7 semicolons. \n Please reformat your text file. ");
                }

                // parse event attributes
                else {
                    String eventName = eventInfo[0];
                    String eventYear = eventInfo[1];
                    String eventStartMonth = eventInfo[2];
                    String eventEndMonth = eventInfo[3];
                    String eventDaysOfTheWeek = eventInfo[4];
                    LocalTime eventStartTime = LocalTime.parse(eventInfo[5], timeformatter);
                    LocalTime eventEndTime = LocalTime.parse(eventInfo[6], timeformatter);

                    String eventStartDate = eventStartMonth + "/" + "1" + "/" + eventYear;
                    String eventEndDate = eventEndMonth + "/" + "1" + "/" + eventYear;

                    LocalDate startDate = LocalDate.parse(eventStartDate, dateformatter);
                    LocalDate endDate = LocalDate.parse(eventEndDate, dateformatter);

                    // construct an event
                    //TimeInterval ti = new TimeInterval(eventStartTime, eventEndTime);
                    Event e = new Event(eventName, startDate, endDate, eventDaysOfTheWeek, eventStartTime, eventEndTime, true);
                    model.addEvent(e);
                }

            }
        }
        br.close();
        fr.close();
        System.out.println(model.getData().toString());
        System.out.println("Number of events: " + model.getData().size());

        System.out.println("Loading is done!");
    }
} // close tester


