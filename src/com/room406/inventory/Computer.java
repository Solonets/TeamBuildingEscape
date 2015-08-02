package com.room406.inventory;

import com.room406.inventory.InventoryItem;

/**
 * Created by vic on 02.08.15.
 */
public class Computer extends InventoryItem {
    public Computer() {
        super("компьютер", "компьютер", "Компьютер нужен чтобы накодить квест", 0, "%s");
    }

    public void use() {

    }
}
