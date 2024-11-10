package edu.uchicago.gerber._06design.P12_1;

public class Product {
    private final double cost;
    private int amount;
    private final String name;
    //Constructor
    public Product(String name, double cost) {
        this.cost = cost;
        this.name = name;
        this.amount = 5;
    }
    //reset
    public void reset(int new_Amount) {
        this.amount = 5;
    }
    public String getName () {
        return name;
    }

    public double getCost () {
        return cost;
    }
    public boolean isInStock() {
        return amount > 0;
    }
    //display cost and amount
    public String display() {
        return "Product : " + name +  "  $" + cost + "  " + amount;
    }
    public void buyProduct() {
        if (amount > 0) {
            amount -= 1;
        }
    }
}
