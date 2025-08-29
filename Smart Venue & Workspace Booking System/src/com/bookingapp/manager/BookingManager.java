package com.bookingapp.manager;

import com.bookingapp.exceptions.*;
import com.bookingapp.model.*;

import java.time.LocalDate;

public class BookingManager {
    private static BookingManager INSTANCE;
    private Venue[] venues = new Venue[0];
    private Booking[] bookings = new Booking[0];

    private BookingManager() {}

    public static synchronized BookingManager getInstance() {
        if (INSTANCE == null) INSTANCE = new BookingManager();
        return INSTANCE;
    }

    public void addVenue(Venue v) {
        Venue[] tmp = new Venue[venues.length + 1];
        System.arraycopy(venues, 0, tmp, 0, venues.length);
        tmp[venues.length] = v;
        venues = tmp;
    }

    public void listVenues() {
        if (venues.length == 0) System.out.println("No venues available.");
        for (Venue v : venues) System.out.println(v);
    }

    public void searchVenues(String location) {
        for (Venue v : venues) {
            if (v.getLocation().equalsIgnoreCase(location)) {
                System.out.println(v);
            }
        }
    }

    public void bookVenue(int venueId, String customer, LocalDate date)
            throws AlreadyBookedException, VenueNotFoundException {
        Venue v = findVenueById(venueId);
        if (v == null) throw new VenueNotFoundException("Venue id not found: " + venueId);

        for (Booking b : bookings) {
            if (b.getVenueId() == venueId && b.getDate().equals(date)) {
                throw new AlreadyBookedException("Venue already booked for: " + date);
            }
        }

        Booking nb = new Booking(venueId, customer, date);
        Booking[] tmp = new Booking[bookings.length + 1];
        System.arraycopy(bookings, 0, tmp, 0, bookings.length);
        tmp[bookings.length] = nb;
        bookings = tmp;

        System.out.println("Booked successfully! " + nb);
        String receipt = nb.generateReceipt(v.getPrice(), v.getName(), v.getLocation());
        System.out.println(receipt);

        //Save receipt to .txt file
        nb.saveReceiptToFile(v.getPrice(), v.getName(), v.getLocation());
    }


    public void cancelBooking(int bookingId) {
        int idx = -1;
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].getBookingId() == bookingId) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("Booking id not found.");
            return;
        }
        Booking[] tmp = new Booking[bookings.length - 1];
        System.arraycopy(bookings, 0, tmp, 0, idx);
        System.arraycopy(bookings, idx + 1, tmp, idx, bookings.length - idx - 1);
        bookings = tmp;
        System.out.println("Booking cancelled: " + bookingId);
    }

    public void listBookings() {
        if (bookings.length == 0) System.out.println("No bookings yet.");
        for (Booking b : bookings) System.out.println(b);
    }

    private Venue findVenueById(int id) {
        for (Venue v : venues) if (v.getId() == id) return v;
        return null;
    }
}