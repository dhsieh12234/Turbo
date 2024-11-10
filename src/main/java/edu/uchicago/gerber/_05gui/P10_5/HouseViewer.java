package edu.uchicago.gerber._05gui.P10_5;


import javax.swing.*;

public class HouseViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(700,700);
        frame.setTitle("House");
        JComponent component = new HouseComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}