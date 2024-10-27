package edu.uchicago.gerber._04interfaces.P9_6;

import java.util.ArrayList;

public abstract class Appointment {
    static public ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    String description;
    public Appointment(String description) {
        this.description = description;
        appointments.add(this);
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean occursOn(int year, int month, int day);
}
