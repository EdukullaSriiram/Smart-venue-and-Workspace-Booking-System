package com.bookingapp.model;

import com.bookingapp.interfaces.Bookable;

import java.util.Objects;

public abstract class Venue implements Bookable {
    private final int id;
    private final String name;
    private final String location;
    private final double price;
    private final int capacity;

    protected Venue(int id, String name, String location, double price, int capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.capacity = capacity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | ₹%.2f | Capacity:%d", id, name, location, price, capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue v)) return false;
        return id == v.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}