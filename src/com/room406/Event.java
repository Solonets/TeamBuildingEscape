package com.room406;

/**
 * Created by Наська on 02.08.2015.
 */
public abstract class Event {

    public enum EventType {
        STARTED("starts"), FINISHED("ended");
        String name;

        EventType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    private String name;
    private int tick;
    private EventType type;

    public Event(String name, int tick, EventType type) {
        this.name = name;
        this.tick = tick;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("It's %d, %s %s", tick, name, type);
    }
}
