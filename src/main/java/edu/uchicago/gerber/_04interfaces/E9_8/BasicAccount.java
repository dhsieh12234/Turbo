package edu.uchicago.gerber._04interfaces.E9_8;

public class BasicAccount extends BankAccount {
    public BasicAccount(double initialBalance) {
        super(initialBalance);
    }
    public BasicAccount() {
        super();
    }
    public void withdraw(double amount) {
        if (amount <= getBalance()) {
            super.withdraw(amount);
        } else {
            System.out.println("Withdrawal denied: Insufficient funds.");
        }
    }
}
