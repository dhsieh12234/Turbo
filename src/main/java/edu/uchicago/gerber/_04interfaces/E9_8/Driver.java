package edu.uchicago.gerber._04interfaces.E9_8;

public class Driver {
    public static void main(String[] args) {
        BasicAccount account = new BasicAccount(100); // Initial balance of 100

        // Test 1: Successful withdrawal
        account.withdraw(50);
        System.out.println("Balance after withdrawal: " + account.getBalance());

        // Test 2: Unsuccessful withdrawal (exceeds balance)
        account.withdraw(60);
        System.out.println("Balance after failed withdrawal: " + account.getBalance());

        // Test 3: Deposit and withdrawal
        account.deposit(30);
        System.out.println("Balance after deposit: " + account.getBalance());
        account.withdraw(30);
        System.out.println("Balance after another withdrawal: " + account.getBalance());
    }
}
