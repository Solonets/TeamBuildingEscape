package com.room406.inventory;

import com.room406.inventory.InventoryItem;

/**
 * Created by vic on 02.08.15.
 */
public class Phone extends InventoryItem implements IUsable{
    private boolean isCharged = false;
    public Phone() {
        super("телефон", "телефон", "Твой телефон", 0, "%s лежит на полке");
    }

    @Override
    public void use() {
        if (isCharged)
        {

        }
        else
        {
            System.out.println("Телефон разряжен, можешь использовать его только чтобы забивать гвозди");
        }
    }
}
