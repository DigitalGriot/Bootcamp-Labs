package org.example;


import java.util.ArrayList;
import java.util.Scanner;



public class DisplayCart {
    private static final Scanner scanner = new Scanner(System.in);
    public static ArrayList<Products> shoppingCart = new ArrayList<>();

    public static void addingItems(){
        System.out.println("Enter the SKU to add");
        String customerProduct = scanner.nextLine();
        ArrayList<Products>products = FileManager.getProducts();
    }
}
