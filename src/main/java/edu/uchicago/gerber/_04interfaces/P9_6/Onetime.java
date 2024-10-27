package edu.uchicago.gerber._04interfaces.P9_6;

public class Onetime extends Appointment{
    int year;
    int month;
    int day;
    public Onetime(int year, int month, int day, String description) {
        super(description);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return this.year == year && this.month == month && this.day == day;
    }
}
