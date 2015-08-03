package com.room406.inventory;

import com.room406.actions.IAction;

/**
 * Created by user on 03.08.2015.
 */
public class Charger extends InventoryItem implements IUsable{
    private Phone phone;
    public Charger(Phone phone)
    {
        super("зарядка", "зарядку", "Хорошо, что ты нашел свою зарядку, без нее в институте не выжить", 0, "%s лежит там, где ты ее оставил");
        this.phone = phone;
    }
    @Override
    public IAction use()
    {
        if (phone.isPicked()) {
            phone.charge();
            System.out.println("Теперь телефон заряжен");
        }
        else
        {
            System.out.println("Как ты хочешь использовать зарядку без телефона?");
        }
        return null;
    }
}
