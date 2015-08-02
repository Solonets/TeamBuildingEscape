package com.room406;

/**
 * Created by пїЅпїЅпїЅпїЅпїЅпїЅ on 02.08.2015.
 */
public abstract class Event {

    public enum EventType {
        STARTED("начался"), FINISHED("закончился");
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
        return String.format("Сейчас %d, %s %s", tick, name, type);
    }

    public EventType getType() {
        return type;
    }
}
