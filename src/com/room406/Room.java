package com.room406;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.08.2015.
 */
public class Room {
    String name;
    String history;
    String description;
    boolean isOpen;
    List<InventoryItem> inventoryItems;
    List<Room> availableRooms;
    public Room(String name, String history, String description, boolean isOpen) {
        this.name = name;
        this.history = history;
        this.description = description;
        this.isOpen = isOpen;
        inventoryItems = new ArrayList<InventoryItem>();
        availableRooms = new ArrayList<Room>();
    }

    List<Room> getAvailableRooms()
    {
        return availableRooms;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getHistory() {

        return history;
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
}
