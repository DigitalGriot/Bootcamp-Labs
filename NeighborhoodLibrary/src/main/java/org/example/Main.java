package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Book> library = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        populateLibrary();
        displayHomeScreen();
    }

    public static void populateLibrary() {
        // Populate the library with 20 books
        library.add(new Book(1, "1-11", "Shadow Protocol", false, ""));
        library.add(new Book(2, "2-22", "Cipher Conspiracy", false, ""));
        library.add(new Book(3, "3-33", "Dead Drop Dossier", false, ""));
        library.add(new Book(4, "4-44", "Rogue Agent Redemption", false, ""));
        library.add(new Book(5, "5-55", "Double Cross Directive", false, ""));
        library.add(new Book(6, "6-66", "Code Black Ops", false, ""));
        library.add(new Book(7, "7-77", "Covert Vendetta", false, ""));
        library.add(new Book(8, "8-88", "Stealth Pursuit", false, ""));
        library.add(new Book(9, "9-99", "Undercover Betrayal", false, ""));
        library.add(new Book(10, "10-10", "Assassin's Gambit", false, ""));
        library.add(new Book(11, "11-11", "Operation Nightshade", false, ""));
        library.add(new Book(12, "12-12", "Ghost Protocol", false, ""));
        library.add(new Book(13, "13-13", "Blackout Protocol", true, "Jennifer"));
        library.add(new Book(14, "14-14", "The Deception Game", true, "Kate"));
        library.add(new Book(15, "15-15", "Terminal Velocity", true, "DeShawn"));
        library.add(new Book(16, "16-16", "Nightfall Protocol", true, "Tim"));
        library.add(new Book(17, "17-17", "Echo of Betrayal", true, "Shawn"));
        library.add(new Book(18, "18-18", "Zero Hour Nexus", true, "Cara"));
        library.add(new Book(19, "19-19", "Deadly Reckoning", true, "Davie"));
        library.add(new Book(20, "20-20", "Shadowstrike Directive", true, "Amanda"));
    }

    public static void displayHomeScreen() {
        System.out.println("Welcome to Jabari's Digital Lending Library!");
        System.out.println("1. Show available books");
        System.out.println("2. Check out a book");
        System.out.println("3. Check in a book");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAvailableBooks();
                break;
            case 2:
                checkOutBook();
                break;
            case 3:
                checkInBook();
                break;
            case 4:
                System.out.println("We hope you enjoy your books! Please come again soon!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayHomeScreen();
                break;
        }
    }

    public static void  showAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : library) {
            if (!book.isCheckedOut()) {
                System.out.println(book.getTitle());
            }
        }
        // After displaying available books, go back to the home screen
        displayHomeScreen();
    }

    public static void checkOutBook() {
        System.out.println("Which book would you like to borrow?:");
        scanner.nextLine(); // Consume newline character
        String title = scanner.nextLine();
        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isCheckedOut()) {
                book.checkedOut("John Doe"); // Assuming the user checking out the book is "John Doe"
                // After checking out the book, go back to the home screen
                displayHomeScreen();
                return;
            }
        }
        System.out.println("Sorry, looks like we don't have that one.");
        // If the book is not available or doesn't exist, go back to the home screen
        displayHomeScreen();
    }

    static void checkInBook() {
        System.out.println("Enter the title of the book you want to check out:");
        scanner.nextLine(); // Consume newline character
        String title = scanner.nextLine();
        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isCheckedOut()) {
                book.checkIn();
                // After checking in the book, go back to the home screen
                displayHomeScreen();
                return;
            }
        }
        System.out.println("Sorry, looks like that one is checked out or does not exist.");
        // If the book is not checked out or doesn't exist, go back to the home screen
        displayHomeScreen();
    }

    public static void homeScreen() {
    }
}

