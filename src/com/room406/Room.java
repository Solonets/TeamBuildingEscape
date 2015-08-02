package com.room406;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.08.2015.
 */
public class Room {
    private String name;
    private String history;
    private String description;
    private boolean isOpen;
    private boolean isVisited;
    private List<InventoryItem> inventoryItems;
    private List<Room> availableRooms;
    public Room(String name, String history, String description, boolean isOpen) {
        this.name = name;
        this.history = history;
        this.description = description;
        this.isOpen = isOpen;
        inventoryItems = new ArrayList<InventoryItem>();
        availableRooms = new ArrayList<Room>();
    }
    public void pushAvailableRoom(Room room)
    {
        availableRooms.add(room);
    }
    public void pushItem(InventoryItem item)
    {
        inventoryItems.add(item);
    }
    public List<Room> getAvailableRooms()
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

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited() {
        this.isVisited = true;
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
    public boolean getItem(InventoryItem a)
    {
        inventoryItems.remove(a);
        return true;
    }
}
