package org.example;

import java.util.Scanner;

public class BankAccount {
    Scanner s;
    private final int accountNumber;
    private final String holderName;
    private double availableBalance;

    public BankAccount(int accountNumber, String holderName, double availableBalance) {
        this.s = new Scanner(System.in);
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.availableBalance = availableBalance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public double getAvailableBalance() {
        return this.availableBalance;
    }

    public void deposit(double amount) {
        if (amount > (double)0.0F) {
            this.availableBalance += amount;
        }

    }

    public void withdraw(double amount) {
        if (amount > (double)0.0F && amount <= this.availableBalance) {
            this.availableBalance -= amount;
        }

    }

    public void display() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Holder: " + this.holderName);
        System.out.println("Balance: $" + this.availableBalance);
    }
}