package com.room406;

/**
 * Created by vic on 02.08.15.
 */
public enum Message {
    ACTION_GO("идти"),
    SHOW_INVENTORY("инвентарь"),
    USE("использовать"),
    PICK("взять");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
