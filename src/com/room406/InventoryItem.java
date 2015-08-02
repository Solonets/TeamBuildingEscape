package com.room406;

/**
 * Created by user on 02.08.2015.
 */
public class InventoryItem {
    private String name;
    private int influence;

    public InventoryItem(String name, int influence) {
        this.name = name;
        this.influence = influence;
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
}
