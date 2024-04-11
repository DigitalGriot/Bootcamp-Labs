package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         String pickupDate;
         int dayRented;
         boolean tollTag;
         boolean GPS;
         boolean roadsideAssistance;
         int age;
         Scanner input = new Scanner(System.in);

        System.out.println("Please enter the pickup date:  ");
        pickupDate = input.nextLine();
        System.out.println("Please enter the number of days you will rent for:  ");
        dayRented = input.nextInt();
        System.out.println("Would you like a tolltag for ($3.95/day)\n\t(Y)es\n\t(N)o");
        tollTag = input.next().equalsIgnoreCase("y");
        System.out.println("Would you like GPS for ($2.95/day)\n\t(Y)es\n\t(N)o");
        GPS = input.next().equalsIgnoreCase("y");
        System.out.println("Would you like roadside assistance? ($3.95/day)\n\t(N)o");
        roadsideAssistance = input.next().equalsIgnoreCase("y");
        System.out.println("What is your age?");
        age = input.nextInt();

        double basicRentalCost = 29.99 * dayRented;




    }
}