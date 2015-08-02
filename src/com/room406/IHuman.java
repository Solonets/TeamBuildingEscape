package com.room406;

/**
 * Created by vic on 02.08.15.
 */
public interface IHuman {
    boolean onEvent(Event event);
    IAction getAction();
    void place(Room room);
}
