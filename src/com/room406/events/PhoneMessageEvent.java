package com.room406.events;

import com.room406.events.Event;

/**
 * Created by ������ on 02.08.2015.
 */
public class PhoneMessageEvent extends Event {
    private String name;
    private int tick;

    public String getMessage() {
        return message;
    }

    private String message;
    private EventType type;

    public PhoneMessageEvent(String name, int tick, EventType type, String name1, int tick1, String message, EventType type1) {
        super(name, tick, type);
        name = name1;
        tick = tick1;
        this.message = message;
        type = type1;
    }

    public int getTick() {
        return tick;
    }

    @Override
    public String toString() {
        return String.format("Вы получили сообщение на телефон: %s", message);
    }
}

