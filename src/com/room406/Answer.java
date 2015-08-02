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

    public List<InventoryItem> getUsefulThings() {
        return usefulThings;
    }

    public void usefulThing(InventoryItem item) {
        usefulThings.add(item);
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return String.format("- %s\n" + "- %s", message, answer);
    }
}
