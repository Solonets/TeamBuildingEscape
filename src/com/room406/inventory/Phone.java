package com.room406.inventory;

import com.room406.inventory.InventoryItem;

/**
 * Created by vic on 02.08.15.
 */
public class Phone extends InventoryItem {
    public Phone() {
        super("телефон", "телефон", "Твой телефон, он разряжен", 0, "%s");
    }

    public void use() {

    }
}
