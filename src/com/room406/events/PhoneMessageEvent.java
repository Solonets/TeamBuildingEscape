package com.room406.events;

import com.room406.events.Event;

/**
 * Created by ������ on 02.08.2015.
 */
public class PhoneMessageEvent extends Event {
    private String name;

    public String getMessage() {
        return message;
    }

    private String message;

    public PhoneMessageEvent(String name, int tick, EventType type, String message) {
        super(name, tick, type);
        this.message = message;
    }


    @Override
    public String toString() {
        return String.format("Вы получили сообщение на телефон: %s", message);
    }
}

