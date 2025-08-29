package com.bookingapp.model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private static int SEQ = 100;
    private final int bookingId;
    private final int venueId;
    private final String customer;
    private final LocalDate date;

    public Booking(int venueId, String customer, LocalDate date) {
        this.bookingId = ++SEQ;
        this.venueId = venueId;
        this.customer = customer;
        this.date = date;
    }

    public int getBookingId() { return bookingId; }
    public int getVenueId() { return venueId; }
    public String getCustomer() { return customer; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return "(B#" + bookingId + ") Venue:" + venueId + " Customer:" + customer + " Date:" + date;
    }

    public String generateReceipt(double pricePerDay, String venueName, String location) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========= BOOKING RECEIPT =========\n");
        sb.append("Booking ID : ").append(bookingId).append("\n");
        sb.append("Customer   : ").append(customer).append("\n");
        sb.append("Venue ID   : ").append(venueId).append("\n");
        sb.append("Venue Name : ").append(venueName).append("\n");
        sb.append("Location   : ").append(location).append("\n");
        sb.append("Booking On : ").append(date).append("\n");
        sb.append("-----------------------------------\n");
        sb.append("TOTAL AMOUNT : ₹").append(pricePerDay).append("\n");
        sb.append("===================================\n");
        return sb.toString();
    }

    // Save receipt to a .txt file
    public void saveReceiptToFile(double pricePerDay, String venueName, String location) {
        String receipt = generateReceipt(pricePerDay, venueName, location);
        String fileName = "receipt_" + bookingId + ".txt";  // unique file for each booking
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(receipt);
            System.out.println("Receipt saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking b)) return false;
        return bookingId == b.bookingId;
    }

    @Override
    public int hashCode() { return Objects.hash(bookingId); }
}
