package edu.uchicago.gerber._04interfaces.P9_6;

public class Monthly extends Appointment {
    public int month;
    public int year;
    public int day;
    public Monthly(int year, int month, int day, String description) {
        super(description);
        this.month = month;
        this.year = year;
        this.day = day;
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        if (this.day != day) {
            return false;
        }
        if (year > this.year || (year == this.year && month >= this.month)) {
            return true;
        }
        return false;
    }
}
