package com.room406.inventory;

import com.room406.actions.Code;
import com.room406.actions.IAction;

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
    public IAction use() {
        System.out.println("Ты сел за задание.");
        return new Code();
    }
}
