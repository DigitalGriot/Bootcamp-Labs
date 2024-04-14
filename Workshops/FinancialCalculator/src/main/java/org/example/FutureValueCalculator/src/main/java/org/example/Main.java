package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Jabari's Certificate of Deposit (CD) Calculator");
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.println("What is the initial deposit amount?");
        double initialDeposit = scanner.nextDouble();

        System.out.println("What is the annual interest rate?");
        double annualInterestRate = scanner.nextDouble() / 100;

        System.out.println("How many years will the CD be invested for?");
        int numberOfYears = scanner.nextInt();

        // Calculate future value and total interest earned
        double futureValue = calculateFutureValue(initialDeposit, annualInterestRate, numberOfYears);
        double totalInterestEarned = futureValue - initialDeposit;

        // Display results
        System.out.println("\nResults:");
        System.out.printf("Ending Balance after %d years: $%.2f\n", numberOfYears, futureValue);
        System.out.printf("Total Interest Earned: $%.2f\n", totalInterestEarned);

        // Close scanner
        scanner.close();
    }

    private static double calculateFutureValue(double initialDeposit, double annualInterestRate, int numberOfYears) {
        // Calculate future value using compound interest formula with daily compounding
        double dailyInterestRate = annualInterestRate / 365; // Daily interest rate
        int numberOfCompoundingPeriods = 365 * numberOfYears; // Total compounding periods
        return initialDeposit * Math.pow(1 + dailyInterestRate, numberOfCompoundingPeriods);
    }

}



