package com.room406;

/**
 * Created by vic on 02.08.15.
 */
public enum Message {
    ACTION_GO("идти"),
    SHOW_INVENTORY("инвентарь"),
    INVENTORY("Инвентарь: "),
    USE("использовать"),
    PICK("взять"),
    PICKED_INVENTORY("Поднят инвентарь. "),
    WATCH_AROUND("Осмотреться"),
    PICED("Взял"),
    HAVE_IT("У тебя достаточно таких предметов"),
    ERROR("Я тебя не понял ");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
