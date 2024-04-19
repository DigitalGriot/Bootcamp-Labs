package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double interestRate;
        double loanLength;
        double principle;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Thank you for choosing the Magnificent Mortgage Calculator");
        System.out.println("We will calculate the following: your monthly loan payment + the interest paid over the lifetime of the loan");

        System.out.println("What is the principle amount: ");
        principle = scanner.nextDouble();

        System.out.println("What is the annual interest rate: ");
        interestRate = scanner.nextDouble();

        System.out.println("How long will you be paying this loan? (in years): ");
        loanLength = scanner.nextDouble();

        double monthlyInterestRate = monthlyInterestRate(interestRate);
        double loanLengthToMonths = loanLengthToMonths(loanLength);
        double monthlyPayment = monthlyPayment(principle, monthlyInterestRate, loanLengthToMonths);
        double totalInterest = totalInterest(monthlyPayment, loanLengthToMonths, principle);

        System.out.printf("Your monthly payment is: $%.2f\n", monthlyPayment);
        System.out.printf("Your total interest paid is: $%.2f\n", totalInterest);
        System.out.printf("Your loan length in months is: %.0f\n", loanLengthToMonths);
    }

    public static double monthlyInterestRate(double annualInterestRate) {
        return annualInterestRate / 12 / 100;
    }

    public static double loanLengthToMonths(double loanLengthYears) {
        return loanLengthYears * 12;
    }

    public static double monthlyPayment(double principle, double monthlyInterestRate, double loanLengthMonths) {
        return principle * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanLengthMonths)) / (Math.pow(1 + monthlyInterestRate, loanLengthMonths) - 1);
    }

    public static double totalInterest(double monthlyPayment, double loanLengthMonths, double principle) {
        return (monthlyPayment * loanLengthMonths) - principle;
    }
}













