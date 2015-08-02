package com.room406;

import java.util.List;

/**
 * Created by ������ on 02.08.2015.
 */
public class Answer {
    private String message;
    private List<InventoryItem> usefulThings;
    private int result;
    private String answer;

    public Answer(String message, int result, String answer) {
        this.message = message;
        this.result = result;
        this.answer = answer;
    }

    public void usefulThing(InventoryItem item) {
        usefulThings.add(item);
    }
}
