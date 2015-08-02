package com.room406;

/**
 * Created by ������ on 02.08.2015.
 */
public class EatEvent extends Event{
    private String name;
    private int tick;
    private EventType type;

    public EatEvent(String name, int tick, EventType type) {
        super(name, tick, type);
    }

    public int getTick() {
        return tick;
    }
}