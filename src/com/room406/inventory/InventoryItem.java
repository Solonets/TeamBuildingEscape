package com.room406.inventory;

import com.room406.actions.IAction;

import java.io.Serializable;

/**
 * Created by user on 02.08.2015.
 */
public class InventoryItem implements Serializable, IUsable {
    private String name;
    private String accusative;
    private int influence;
    private String pickMessage;
    private String location;
    private int codingSkills;
    private boolean isPicked = false;

    public InventoryItem(String name, String accusative, String pickMessage, int influence, int codingSkills, String location) {
        this.name = name;
        this.pickMessage = pickMessage;
        this.accusative = accusative;
        this.influence = influence;
        this.location = location;
        this.codingSkills = codingSkills;
    }
    public int getCodingSkills()
    {
        return codingSkills;
    }
    public void pickUp()
    {
        isPicked = true;
    }
    public boolean isPicked()
    {
        return isPicked;
    }
    public IAction use()
    {
        System.out.println("Зачем тебе с этим что-то делать? просто носи и гордись");
        return null;
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
        String result = this.pickMessage;
        if (this.getInfluence() != 0) {
            result += " (+" + this.getInfluence() + " к влиянию)";
        }
        if (this.getCodingSkills() != 0) {
            result += " (+" + this.getCodingSkills() + " к кодингу)";
        }
        return result;
    }
    public String getLocation()
    {
        return String.format(this.location, this.name);
    }
}
