package com.room406;

/**
 * Created by user on 02.08.2015.
 */
public class InventoryItem {
    private String name;
    private String accusative;
    private int influence;
    private String location;

    public InventoryItem(String name, String accusative, int influence, String location) {
        this.name = name;
        this.accusative = accusative;
        this.influence = influence;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getInfluence() {
        return influence;
    }

    @Override
    public String toString() {
        return name;
    }
    public boolean equals(String name)
    {
        return this.name == name || this.accusative == name;
    }
    public String getLocation()
    {
        return String.format(this.location, this.name);
    }
}
