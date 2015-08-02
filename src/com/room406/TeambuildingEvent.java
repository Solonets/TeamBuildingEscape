package com.room406;

/**
 * Created by Наська on 02.08.2015.
 */
public class TeambuildingEvent extends Event{
    private String name;
    private int tick;
    private EventType type;

    public TeambuildingEvent(String name, int tick, EventType type) {
        super(name, tick, type);
    }

    public int getTick() {
        return tick;
    }


}
