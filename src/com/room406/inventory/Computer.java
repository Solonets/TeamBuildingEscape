package com.room406.inventory;

import com.room406.inventory.InventoryItem;

/**
 * Created by vic on 02.08.15.
 */
public class Computer extends InventoryItem implements IUsable{
    private Phone phone;
    public Computer(Phone phone, String location) {
        super("ноутбук", "ноутбук", "Ты взял компьютер без спросу, будем надеятся, что камеры не работают", 0, location);
        this.phone = phone;
    }
    @Override
    public void use() {

    }
}
