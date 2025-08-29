package com.bookingapp.main;

import com.bookingapp.factory.VenueFactory;
import com.bookingapp.manager.BookingManager;
import com.bookingapp.model.*;
import com.bookingapp.exceptions.*;
import com.bookingapp.interfaces.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        BookingManager mgr = BookingManager.getInstance();

        // Seed sample data
        mgr.addVenue(VenueFactory.create("FunctionHall", 1, "Grand entry", "Hyderabad", 50000, 500));
        mgr.addVenue(VenueFactory.create("FunctionHall", 2, "JBM", "Hyderabad", 150000, 300));
        mgr.addVenue(VenueFactory.create("FunctionHall", 3, "SWAGATH GRAND", "Hyderabad", 90000, 300));
        mgr.addVenue(VenueFactory.create("Workspace", 4, "CoWork Hub", "Bangalore", 500, 20));
        mgr.addVenue(VenueFactory.create("Workspace", 5, "IT Hub", "Bangalore", 5000, 100));
        mgr.addVenue(VenueFactory.create("Workspace", 6, "ECO Hub", "Bangalore", 2500, 70));
        mgr.addVenue(VenueFactory.create("ToLet", 7, "TechPark Office", "Chennai", 100000, 50));

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== Venue Booking System ===");
            System.out.println("1. List all venues");
            System.out.println("2. Search venues");
            System.out.println("3. Book a venue");
            System.out.println("4. Cancel booking");
            System.out.println("5. List all bookings");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> mgr.listVenues();
                case 2 -> {
                    System.out.print("Enter location: ");
                    String loc = sc.nextLine();
                    mgr.searchVenues(loc);
                }
                case 3 -> {
                    System.out.print("Enter venue id: ");
                    int vid = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter your name: ");
                    String cust = sc.nextLine();
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    try {
                        mgr.bookVenue(vid, cust, date);
                    } catch (AlreadyBookedException | VenueNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("Enter booking id: ");
                    int bid = Integer.parseInt(sc.nextLine());
                    mgr.cancelBooking(bid);
                }
                case 5 -> mgr.listBookings();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}