package org.example;

import java.util.Scanner;

public class FutureValueCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Thank you for choosing Jabari's Future Value Calculator!");
        // Get user input
        System.out.print("What is your initial deposit amount: $");
        double deposit = scanner.nextDouble();

        System.out.print("What is the annual interest rate (in percentage)?: ");
        double interestRate = scanner.nextDouble();

        System.out.print("For how long?: ");
        int years = scanner.nextInt();

        System.out.println("Thank you and we hope you chose us again!");

        // Calculate future value and total interest
        double[] results = calculateFutureValue(deposit, interestRate, years);
        double futureValue = results[0];
        double totalInterest = results[1];

        // Display results
        System.out.printf("\nFuture value after %d years: $%.2f\n", years, futureValue);
        System.out.printf("Total interest earned: $%.2f\n", totalInterest);

        scanner.close();
    }

    public static double[] calculateFutureValue(double deposit, double interestRate, int years) {
        // Convert interest rate from percentage to decimal
        interestRate /= 100.0;

        // Calculate future value
        double futureValue = deposit * Math.pow(1 + interestRate, years);

        // Calculate total interest earned
        double totalInterest = futureValue - deposit;

        return new double[] {futureValue, totalInterest};
    }
}
