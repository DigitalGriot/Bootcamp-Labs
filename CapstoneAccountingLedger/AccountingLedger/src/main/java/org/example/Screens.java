package org.example;

import java.util.List;
import java.util.Scanner;

public class Screens {
    // Home Screen
    public static void homeScreen() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    What Would You Like to Do?
                    1) Deposit Funds
                    2) Payment (Debit)
                    3) View Ledger
                    4) Exit""");
            switch (scanner.nextLine()) {
                case "1":
                    addDeposit();
                    break;
                case "2":
                    makePayment();
                    break;
                case "3":
                    ledgerScreen();
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Please choose one of the options above.");
            }
        }
    }

    // Add Deposit
    private static void addDeposit() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("---Add Deposit---");
                Transaction deposit = new Transaction();
                System.out.println("Please enter the following information.");
                System.out.print("Deposit Description: ");
                String description = scanner.nextLine();
                System.out.print("Vendor: ");
                String vendor = scanner.nextLine();
                System.out.print("Amount: ");
                double amount = Double.parseDouble(scanner.nextLine());
                deposit.setDescription(description);
                deposit.setVendor(vendor);
                deposit.setAmount(amount);
                FileManagement.writeTransactionToFile(deposit);
                System.out.println("Deposit added successfully.");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter the deposit amount in number format.");
            }
            addMoreDeposits();
        }
    }

    // Make Payment
    private static void makePayment() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("---Make Payment---");
                Transaction payment = new Transaction();
                System.out.println("Please enter the following information.");
                System.out.print("Payment Description: ");
                String description = scanner.nextLine();
                System.out.print("Vendor: ");
                String vendor = scanner.nextLine();
                System.out.print("Amount: ");
                double amount = Math.abs(Double.parseDouble(scanner.nextLine())) * -1;
                payment.setDescription(description);
                payment.setVendor(vendor);
                payment.setAmount(amount);
                FileManagement.writeTransactionToFile(payment);
                System.out.println("Payment made successfully.");
            } catch (NumberFormatException ex) {
                System.out.println("Please enter the payment amount in number format.");
            }
            addMorePayments();
        }
    }

    // Ledger Screen
    private static void ledgerScreen() {
        while (true) {
            System.out.println("""
                    ---Ledger---
                    1) Display all Transactions
                    2) Display all Deposits
                    3) Display all Payments
                    4) Return to Home screen""");
            switch (optionPicker()) {
                case "1":
                    List<Transaction> allTransactions = FileManagement.readTransactionsFromFile();
                    printTransactions(allTransactions);
                    break;
                case "2":
                    List<Transaction> deposits = TransactionSort.getAllDeposits(FileManagement.readTransactionsFromFile());
                    printTransactions(deposits);
                    break;
                case "3":
                    List<Transaction> payments = TransactionSort.getAllPayments(FileManagement.readTransactionsFromFile());
                    printTransactions(payments);
                    break;
                case "4":
                    homeScreen();
                    break;
                default:
                    System.out.println("Please choose from one of the options above.");
                    break;
            }
        }
    }

    // Helper method for getting user input
    private static String optionPicker() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Helper method to allow the user to stay on the deposit screen
    private static void addMoreDeposits() {
        while (true) {
            System.out.println("1) Add another deposit 2) Home screen");
            switch (optionPicker()) {
                case "1":
                    addDeposit();
                    break;
                case "2":
                    homeScreen();
                    break;
                default:
                    System.out.println("Please choose from one of the above options.");
            }
        }
    }

    // Helper method to allow the user to stay on the payments screen
    private static void addMorePayments() {
        while (true) {
            System.out.println("1) Add another payment 2) Home screen");
            switch (optionPicker()) {
                case "1":
                    makePayment();
                    break;
                case "2":
                    homeScreen();
                    break;
                default:
                    System.out.println("Please choose from one of the above options.");
            }
        }
    }

    // Helper method to print transactions
    private static void printTransactions(List<Transaction> transactions) {
        int transactionCount = 0;
        for (Transaction transaction : transactions) {
            System.out.printf("%s %s %s %s %s\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            transactionCount++;
        }
        if (transactionCount == 0) {
            System.out.println("No transactions found.");
        } else {
            System.out.printf("All %d transactions displayed\n", transactionCount);
        }
    }
}
