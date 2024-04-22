package org.example;

import java.util.Scanner;

import static org.example.Main.checkInBook;

public class Screens {
    private static Book[] books; // Declare book array at the class level

    public static void main(String[] args) {
        // Call the method to set up the book inventory
        setupBookInventory();

        // Call the showAvailableBooks() method
        showAvailableBooks();

        // Handle user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do? :");
        System.out.println("(1) I would like to borrow a book.");
        System.out.println("(2) Home Screen");
        var input = scanner.nextLine();

        // Process user input
        switch (input) {
            case "1":
                borrowBook();
                break;
            case "2":
                // Add logic for navigating to the home screen if needed
                break;
            default:
                System.out.println("Invalid input. Please try again.");
        }
    }

    private static void setupBookInventory() {
        // Initialize the book array
        books = new Book[20];

        // Populate the book array with Book objects
        // Omitted for brevity
    }

    public static void showAvailableBooks() {
        System.out.println("Here's all the books we have currently:");
        // Omitted for brevity
    }

    public static void borrowBook() {
        // Logic for borrowing a book
        System.out.println("What would you like to do?");
        System.out.println("(1) I want to borrow a book");
        System.out.println("(2) Home Screen");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1")) {
            checkInBook();
            Main.homeScreen();
        } else if (input.equals("2")) {
            // Add logic for navigating to the home screen
        } else {
            System.out.println("Invalid input");
        }
    }

    public static void checkinBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book ID to check out:");
        int bookID = scanner.nextInt();

        // Add logic to check in the book with the given ID
    }
}





        
    


        



