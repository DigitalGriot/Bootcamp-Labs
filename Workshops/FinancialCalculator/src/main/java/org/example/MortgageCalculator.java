package org.example;
import java.util.Scanner;
public class MortgageCalculator {
    private double principle;
    private final double yearlyInterest;
    private double loanLengthYears;

    public MortgageCalculator(double principle, double yearlyInterest, double loanLengthYears) {
        this.principle = principle;
        this.yearlyInterest = yearlyInterest;
        this.loanLengthYears = loanLengthYears;
    }

    public double calculateMonthlyPayment() {
        double monthlyInterest = yearlyInterest / 12 / 100;
        double loanLengthInMonths = loanLengthYears * 12;
        double monthlyPayment = principle * (monthlyInterest * Math.pow((1 + monthlyInterest), loanLengthInMonths)) /
                (Math.pow(1 + monthlyInterest, loanLengthInMonths) - 1);
        return monthlyPayment;
    }

    public double calculateTotalInterest() {
        double monthlyPayment = calculateMonthlyPayment();
        double totalInterest = (monthlyPayment * (loanLengthYears * 12)) - principle;
        return totalInterest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your principle (loan amount)? ");
        double principle = scanner.nextDouble();
        System.out.print("What is your yearly interest rate? ");
        double yearlyInterest = scanner.nextDouble();
        System.out.print("What is your loan length (in years)? ");
        double loanLengthYears = scanner.nextDouble();

        MortgageCalculator calculator = new MortgageCalculator(principle, yearlyInterest, loanLengthYears);
        double monthlyPayment = calculator.calculateMonthlyPayment();
        double totalInterest = calculator.calculateTotalInterest();

        System.out.println("\nSummary:");
        System.out.printf("Principle: $%.2f\n", principle);
        System.out.printf("Yearly interest: %.3f\n", yearlyInterest);
        System.out.printf("Loan length: %.0f years\n", loanLengthYears);
        System.out.println("--------------------------");
        System.out.printf("Total monthly payment: $%.2f\n", monthlyPayment);
        System.out.printf("Total interest paid: $%.2f\n", totalInterest);

        System.out.println("\nThank you for choosing Jabari's mortgage calculator!");

        scanner.close();
    }
}
