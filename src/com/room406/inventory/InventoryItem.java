package com.room406.inventory;

import java.io.Serializable;

/**
 * Created by user on 02.08.2015.
 */
public class InventoryItem implements Serializable {
    private String name;
    private String accusative;
    private int influence;
    private String pickMessage;
    private String location;
    private boolean isPicked = false;

    public InventoryItem(String name, String accusative, String pickMessage, int influence, String location) {
        this.name = name;
        this.pickMessage = pickMessage;
        this.accusative = accusative;
        this.influence = influence;
        this.location = location;
    }
    public void pickUp()
    {
        isPicked = true;
    }
    public boolean isPicked()
    {
        return isPicked;
    }
    public void use()
    {
        System.out.println("Зачем тебе с этим что-то делать? просто носи и гордись");
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
        return this.name.toLowerCase().equals(name.toLowerCase())
                || this.accusative.toLowerCase().equals(name.toLowerCase());
    }
    public String getPickMessage()
    {
        if (this.getInfluence() == 0)
        {
            return this.pickMessage;
        }
        return this.pickMessage + " (+" + this.getInfluence() + " к влиянию)";
    }
    public String getLocation()
    {
        return String.format(this.location, this.name);
    }
}
