package org.example;

import java.util.Scanner;

public class Book {

    public static Object Screens;
    public int bookID;

    public String isbn;

    public String title;

    public boolean isCheckedOut;

    public String checkedOutTo;


    public Book(int bookID, String isbn, String title, boolean isCheckedOut, String checkedOutTo) {
        this.bookID = bookID;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = isCheckedOut;


    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }

    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }

    public void checkedOut(String name) {
        if (!isCheckedOut) {
            isCheckedOut = true;
            checkedOutTo = name;
            System.out.println("That's a good choice! We hope you enjoy it!," + name + "!");

        } else {
            System.out.println("This one's already checked out, try again later!");
        }

    }

    public void checkIn() {
        if (isCheckedOut) {
            isCheckedOut = !true;
            checkedOutTo = "";
            System.out.println("This book has been returned to the library");
        } else {
            System.out.println("Sorry, this book has been checked in.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        if (input.equals("1")) {



        }
    }

    public Object getISBN() {
        return null;
    }
}
