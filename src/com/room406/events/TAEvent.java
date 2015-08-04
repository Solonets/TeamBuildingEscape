package com.room406.events;

/**
 * Created by user on 04.08.2015.
 */
public class TAEvent extends Event{
    private String name;
    private Event.EventType type;

    public TAEvent(String name, int tick, Event.EventType type) {
        super(name, tick, type);
    }

}
