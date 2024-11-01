package edu.uchicago.gerber._05gui.P10_2;
import javax.swing.*;

public class BullsEyeViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BullsEye");
        frame.setSize(400,400);
        frame.setTitle("Bulls Eye");
        JComponent component = new BullsEyeComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
