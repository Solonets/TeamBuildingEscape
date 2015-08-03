package com.room406.inventory;

import com.room406.actions.Code;
import com.room406.actions.IAction;

/**
 * Created by vic on 02.08.15.
 */
public class Computer extends InventoryItem implements IUsable{
    public Computer(String location) {
        super("ноутбук", "ноутбук", "Ты взял компьютер без спросу, будем надееться что камеры не работают", 0, location);
    }
    @Override
    public IAction use() {
        System.out.println("Ты сел за задание.");
        return new Code();
    }
}
