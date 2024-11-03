package edu.uchicago.gerber._05gui.P10_19;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillComponent extends JComponent {
    private final JTextArea billArea;
    private double totalBill = 0.0;
    private static final double TAX_RATE = 0.1;
    private static final double TIP_RATE = 0.15;

    public BillComponent() {
        setLayout(new BorderLayout());

        billArea = new JTextArea();
        billArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 5));
        add(buttonPanel, BorderLayout.NORTH);

        String[] items = { "Burger", "Pizza", "Pasta", "Salad", "Soda", "Coffee", "Tea", "Juice", "Steak", "Ice Cream" };
        double[] prices = { 8.99, 12.50, 10.00, 6.50, 2.00, 3.00, 2.50, 4.00, 15.00, 5.50 };

        for (int i = 0; i < items.length; i++) {
            DrawButton(buttonPanel, items[i], prices[i]);
        }

        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField itemField = new JTextField(10);
        JTextField priceField = new JTextField(5);
        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(e -> addCustomItem(itemField.getText(), priceField.getText()));
        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.SOUTH);
    }
    public void DrawButton(JPanel panel, String itemName, double itemPrice) {
        JButton button = new JButton(itemName + " ($" + itemPrice + ")");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToBill(itemName, itemPrice);
            }
        });
        panel.add(button);
    }
    private void addCustomItem(String itemName, String priceText) {
        try {
            double itemPrice = Double.parseDouble(priceText);
            addItemToBill(itemName, itemPrice);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.");
        }
    }

    private void addItemToBill(String itemName, double itemPrice) {
        totalBill += itemPrice;
        billArea.append(itemName + " - $" + itemPrice + "\n");
        updateBill();
    }

    private void updateBill() {
        double tax = totalBill * TAX_RATE;
        double tip = totalBill * TIP_RATE;
        double finalTotal = totalBill + tax + tip;

        billArea.append("\nSubtotal: $" + String.format("%.2f", totalBill));
        billArea.append("\nTax: $" + String.format("%.2f", tax));
        billArea.append("\nSuggested Tip: $" + String.format("%.2f", tip));
        billArea.append("\nTotal: $" + String.format("%.2f", finalTotal) + "\n\n");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
