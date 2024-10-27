package edu.uchicago.gerber._04interfaces.E9_8;

public class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }
    public BankAccount() {
        balance = 0;
    }
    public void deposit(double amount)
    {
        balance = balance + amount;
    }
    public void withdraw(double amount)
    {
        balance = balance - amount;
    }
    public void monthEnd() {
        System.out.println("Month-end processing for BankAccount.");
    }
    public double getBalance()
    {
        return balance;
    }
}
