package com.room406.inventory;

import com.room406.actions.IAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 02.08.15.
 */
public class Phone extends InventoryItem implements IUsable {
    private boolean isCharged = false;
    private List<String> messages = new ArrayList<>();
    public Phone() {
        super("телефон", "телефон", "Твой телефон", 0, 0, "%s лежит на полке");
    }

    @Override
    public IAction use() {
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
        return null;
    }
    public void pushMessage(String msg)
    {
        messages.add(msg);
    }
    public void charge()
    {
        isCharged = true;
        String s = "[Telegram] TA: Задание в приклепленном файле";
        System.out.println(s);
        pushMessage(s);
    }
    public boolean isCharged()
    {
        return isCharged;
    }
}
