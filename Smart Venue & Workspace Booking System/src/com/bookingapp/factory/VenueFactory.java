package com.bookingapp.factory;

import com.bookingapp.model.*;

public class VenueFactory {
    public static Venue create(String type, int id, String name, String location, double price, int capacity) 
    {
        return switch (type) 
        		{
            case "FunctionHall" -> new FunctionHall(id, name, location, price, capacity);
            case "Workspace" -> new Workspace(id, name, location, price, capacity);
            case "ToLet" -> new ToLet(id, name, location, price, capacity);
            default -> throw new IllegalArgumentException("Unknown venue type: " + type);
        };
    }
}