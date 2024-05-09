package org.example;

import java.time.LocalDate;
import java.util.*;

public class TransactionSort {
    //filter transaction methods

    //all transactions sorted newest to oldest
    public static List<Transaction> getAllTransactions(List<Transaction> transactions) {
        List<Transaction> allTransactions = new ArrayList<>();

        //sort transactions by newest first
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                int dateComparison = t2.getDate().compareTo(t1.getDate());
                if (dateComparison != 0) {
                    return dateComparison;
                }

                //if the dates are equal, compare the time values
                return t2.getTime().compareTo(t1.getTime());
            }
        });

        //print sorted transactions
        for (Transaction transaction : transactions) {
            System.out.printf("%s %s %s %s %s\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            allTransactions.add(transaction);
        }

        return allTransactions;
    }

    //only display deposits sorted newest to oldest
    public static List<Transaction> getAllDeposits(List<Transaction> transactions) {
        List<Transaction> deposits = new ArrayList<>();
        //sort transactions by newest first
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                int dateComparison = t2.getDate().compareTo(t1.getDate());
                if (dateComparison != 0) {
                    return dateComparison;
                }

                //if the dates are equal, compare the time values
                return t2.getTime().compareTo(t1.getTime());
            }
        });
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        return deposits;
    }

    //only display payments sorted newest to oldest
    public static List<Transaction> getAllPayments(List<Transaction> transactions) {
        List<Transaction> withdraw = new ArrayList<>();
        //sort transactions by newest first
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                int dateComparison = t2.getDate().compareTo(t1.getDate());
                if (dateComparison != 0) {
                    return dateComparison;
                }

                //if the dates are equal, compare the time values
                return t2.getTime().compareTo(t1.getTime());
            }
        });
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                withdraw.add(transaction);
            }
        }
        return withdraw;
    }


    public static List<Transaction> getTransactionsByPeriod(List<Transaction> transactions, String period) {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
        LocalDate firstDayOfPreviousMonth = firstDayOfMonth.minusMonths(1);
        LocalDate lastDayOfPreviousMonth = firstDayOfMonth.minusDays(1);
        LocalDate firstDayOfYear = currentDate.withDayOfYear(1);
        LocalDate firstDayOfPreviousYear = firstDayOfYear.minusYears(1);
        LocalDate lastDayOfPreviousYear = firstDayOfYear.minusDays(1);

        switch (period) {
            case "MonthToDate":
                return filterTransactions(transactions, firstDayOfMonth, currentDate);
            case "PreviousMonth":
                return filterTransactions(transactions, firstDayOfPreviousMonth, lastDayOfPreviousMonth);
            case "YearToDate":
                return filterTransactions(transactions, firstDayOfYear, currentDate);
            case "PreviousYear":
                return filterTransactions(transactions, firstDayOfPreviousYear, lastDayOfPreviousYear);
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }
    }

    // Method to filter transactions within a specific period
    private static List<Transaction> filterTransactions(List<Transaction> transactions, LocalDate startDate, LocalDate endDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate());
            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    //method to search by vendor
    public static List<Transaction> searchByVendor(List<Transaction> transactions) {
        List<Transaction> vendorSort = new ArrayList<>();
        int transactioncount = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the vendor name: ");
        String vendorName = scanner.nextLine();
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                vendorSort.add(transaction);
                transactioncount++;
            }
        }
        if (transactioncount == 0) {
            System.out.println("Vendor not found");
        }
        return vendorSort;
    }
}
