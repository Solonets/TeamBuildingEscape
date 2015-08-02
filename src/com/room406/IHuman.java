package com.room406;

/**
 * Created by vic on 02.08.15.
 */
public interface IHuman {
    void onEvent(Event event);
    Action move();
    void place(Room room);
}
