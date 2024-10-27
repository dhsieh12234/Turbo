package edu.uchicago.gerber._04interfaces.P9_1;

public class WorldClock extends Clock{
    private final int offset;
    public WorldClock(int offset) {
        this.offset = offset;
    }
    public int getHour() {
        int localHour = super.getHour();
        int adjustedHour = (localHour + offset) % 24;
        if (adjustedHour < 0) {
            adjustedHour += 24;
        }
        return adjustedHour;
    }
}
