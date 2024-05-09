package org.example;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String filePath = "src/main/resources/transactions.csv";

    public static List<Transaction> readTransactionsFromFile() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                String date = data[0];
                String time = data[1];
                String description = data[2];
                String vendor = data[3];
                double amount = Double.parseDouble(data[4]);
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);
            }

        } catch (IOException ex) {
            System.out.println("File could not be read.");
        }
        return transactions;
    }

    public static void writeTransactionToFile(Transaction transaction) {
        transaction.updateDate();
        transaction.updateTime();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(transaction.getDate() + "|" + transaction.getTime() + "|" +
                    transaction.getDescription() + "|" + transaction.getVendor() + "|" +
                    df.format(transaction.getAmount()));
            System.out.println("Transaction added.");
        } catch (IOException ex) {
            System.out.println("File could not be written to.");
        }
    }
}
