package com.room406.inventory;

import com.room406.inventory.InventoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 02.08.15.
 */
public class Phone extends InventoryItem implements IUsable {
    private boolean isCharged = false;
    private List<String> messages = new ArrayList<>();
    public Phone() {
        super("телефон", "телефон", "Твой телефон", 0, "%s лежит на полке");
    }

    @Override
    public void use() {
        if (isCharged)
        {
            if (messages.isEmpty())
            {
                System.out.println("Тебе еще никто не писал, попробуй завести друзей ");
            }
            else
            {
                System.out.println("Твоя история сообщений:");
                for (String msg: messages)
                {
                    System.out.println(msg);
                }
            }
        }
        else
        {
            System.out.println("Телефон разряжен, можешь использовать его только чтобы забивать гвозди");
        }
    }
    public void pushMessage(String msg)
    {
        messages.add(msg);
    }
    public void charge()
    {
        isCharged = true;
        String s = "[Телефон] TA: Задание в приклепленном файле";
        System.out.println(s);
        pushMessage(s);
    }
}
