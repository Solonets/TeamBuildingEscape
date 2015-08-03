package com.room406.events;

import com.room406.events.Event;

/**
 * Created by ������ on 02.08.2015.
 */
public class TeambuildingEvent extends Event {
    private String name;
    private EventType type;

    public TeambuildingEvent(String name, int tick, EventType type) {
        super(name, tick, type);
    }

}
