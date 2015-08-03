package com.room406.events;

/**
 * Created by ������ on 02.08.2015.
 */
public class EatEvent extends Event {
    private String name;
    private EventType type;

    public EatEvent(String name, int tick, EventType type) {
        super(name, tick, type);
    }

}
