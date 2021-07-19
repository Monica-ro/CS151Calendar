import java.time.LocalDate;

public class CalendarModel {
    private LocalDate currentDate;

    public CalendarModel(LocalDate cd) {
        currentDate = cd;
    }

    public int getDaysInMonth() {
        return currentDate.lengthOfMonth();
    }

    public String getMonth() {
        return currentDate.getMonth().toString();
    }

    public int getYear() {
        return currentDate.getYear();
    }


    public static int getFirstOfMonth(LocalDate date) {
        return weekTool(date.getDayOfWeek().toString());
    }

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
}
