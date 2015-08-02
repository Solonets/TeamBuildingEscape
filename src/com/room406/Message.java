package com.room406;

/**
 * Created by vic on 02.08.15.
 */
public enum Message {
    ACTION_GO("идти"),
    SHOW_INVENTORY("инвентарь "),
    INVENTORY("Инвентарь: "),
    USE("использовать"),
    PICK("взять"),
    PICKED_INVENTORY("Поднят инвентарь. "),
    WATCH_AROUND("Осмотреться"),
    ERROR("Я не понял, что ты хочешь! ");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
