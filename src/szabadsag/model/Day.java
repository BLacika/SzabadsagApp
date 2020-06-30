package szabadsag.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

public class Day implements Comparable<Day>{

    private IntegerProperty year;
    private IntegerProperty month;
    private IntegerProperty day;
    private StringProperty status;

    public Day(int year, int month, int day, String status) {
        this.year = new SimpleIntegerProperty(year);
        this.month = new SimpleIntegerProperty(month);
        this.day = new SimpleIntegerProperty(day);
        this.status = new SimpleStringProperty(status);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public int getMonth() {
        return month.get();
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getDay() {
        return day.get();
    }

    public IntegerProperty dayProperty() {
        return day;
    }

    public void setDay(int day) {
        this.day.set(day);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    @Override
    public int compareTo(Day otherDay) {
        if (this == null && otherDay == null) return 0;
        if (this == null && otherDay != null) return -1;
        if (this != null && otherDay == null) return 1;

        // compare by year
        int result = 0;
        if (this.yearProperty() == null && otherDay.yearProperty() == null) result = 0;
        else if (this.yearProperty() == null && otherDay.yearProperty() != null) result = -1;
        else if (this.yearProperty() != null && otherDay.yearProperty() == null) result = 1;
        else result = Integer.valueOf(this.getYear()).compareTo(Integer.valueOf(otherDay.getYear()));

        // compare by month
        if (result == 0) {
            if (this.getMonth() < otherDay.getMonth()) result = -1;
            else if (this.getMonth() > otherDay.getMonth()) result = 1;
            else result = 0;
        }

        // compare by day
        if (result == 0) {
            if (this.getDay() < otherDay.getDay()) result = -1;
            else if (this.getDay() > otherDay.getDay()) result = 1;
            else result = 0;
        }

        if (result == 0) {
            result = this.getStatus().compareTo(otherDay.getStatus());
        }

        return result;
    }
}
