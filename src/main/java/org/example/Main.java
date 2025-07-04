package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        boolean option = true;

        while (option) {
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1 - Create Account");
            System.out.println("2 - View All Accounts");
            System.out.println("3 - Check Balance");
            System.out.println("4 - Deposit");
            System.out.println("5 - Withdraw");
            System.out.println("6 - Exit");
            System.out.print("Enter choice (1-6): ");

            int choice = s.nextInt();
            s.nextLine(); // consume newline

            switch (choice) {
                case 1: {
                    System.out.print("Enter Account Number (6 digits): ");
                    int accountNumber = s.nextInt();
                    int length = String.valueOf(accountNumber).length();
                    s.nextLine();

                    if (length != 6){
                        System.out.println("Account number must be 6 digits");
                        break;
                    }

                    BankAccount existing = findAccountByNumber(accounts, accountNumber);
                    if (existing != null) {
                        System.out.println("This account number already exists. Please enter a different one.");
                        break;
                    }

                    System.out.print("Enter Holder Name: ");
                    String holderName = s.nextLine();

                    if (holderName.isEmpty()){
                        System.out.println("Can't proceed without holder name");
                        break;
                    }

                    System.out.print("Initial Deposit? (yes or no): ");
                    String depositDecision = s.nextLine();

                    double initialDeposit = 0;

                    if (depositDecision.equalsIgnoreCase("yes")) {
                        System.out.print("Enter initial deposit amount: ");
                        initialDeposit = s.nextDouble();
                        s.nextLine(); // consume newline
                    }

                    BankAccount account = new BankAccount(accountNumber, holderName, initialDeposit);
                    accounts.add(account);

                    System.out.println("Account created successfully!");
                    break;
                }

                case 2: {
                    System.out.println("\n--- All Accounts ---");
                    for (BankAccount acc : accounts) {
                        acc.display();
                        System.out.println("---------------------");
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter account number to check balance: ");
                    int accNum = s.nextInt();
                    BankAccount found = findAccountByNumber(accounts, accNum);

                    if (found != null) {
                        System.out.println("Balance: $" + found.getAvailableBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter account number to deposit to: ");
                    int accNum = s.nextInt();
                    BankAccount found = findAccountByNumber(accounts, accNum);

                    if (found != null) {
                        System.out.print("Enter deposit amount: ");
                        double amount = s.nextDouble();
                        s.nextLine();

                        found.deposit(amount);
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }

                case 5: {
                    System.out.print("Enter account number to withdraw from: ");
                    int accNum = s.nextInt();
                    BankAccount found = findAccountByNumber(accounts, accNum);

                    if (found != null) {
                        System.out.print("Enter withdraw amount: ");
                        double amount = s.nextDouble();
                        s.nextLine();

                        if (amount <= found.getAvailableBalance()) {
                            found.withdraw(amount);
                            System.out.println("Withdraw successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                }

                case 6: {
                    System.out.println("Goodbye!");
                    option = false;
                    break;
                }

                default:
                    System.out.println("Invalid choice.");
            }
        }

        s.close();
    }



    private static BankAccount findAccountByNumber(ArrayList<BankAccount> accounts, int accNum) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber()== accNum) {
                return acc;
            }
        }
        return null;
    }
}