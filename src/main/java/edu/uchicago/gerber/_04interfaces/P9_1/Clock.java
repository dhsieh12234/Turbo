package edu.uchicago.gerber._04interfaces.P9_1;

public class Clock {
    public int getHour() {
        String time =  java.time.LocalTime.now().toString();
        return Integer.parseInt(time.split(":")[0]);

    }
    public int getMinuite() {
        String time =  java.time.LocalTime.now().toString();
        return Integer.parseInt(time.split(":")[1]);
    }
    public String getTime() {
        return "Hour: " + getHour() + " Min: " + getMinuite();
    }
}
