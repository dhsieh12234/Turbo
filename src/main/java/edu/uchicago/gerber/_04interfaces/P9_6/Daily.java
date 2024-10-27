package edu.uchicago.gerber._04interfaces.P9_6;

public class Daily extends Appointment{
    int year;
    int month;
    int day;
    public Daily(int year, int month, int day, String description) {
        super(description);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        if (year > this.year) {
            return true;
        } else if (year == this.year && month > this.month) {
            return true;
        } else if (year == this.year && month == this.month && day >= this.day) {
            return true;
        }
        return false;
    }
}
