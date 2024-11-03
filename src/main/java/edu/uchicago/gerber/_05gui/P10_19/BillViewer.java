package edu.uchicago.gerber._05gui.P10_19;

import edu.uchicago.gerber._05gui.P10_9.FlagComponent;

import javax.swing.*;

public class BillViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bill");
        frame.setSize(700,500);
        frame.setTitle("Bill");
        JComponent component = new BillComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
