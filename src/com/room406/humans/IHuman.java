package com.room406.humans;

import com.room406.actions.IAction;
import com.room406.rooms.Room;
import com.room406.events.Event;

/**
 * Created by vic on 02.08.15.
 */
public interface IHuman {
    boolean onEvent(Event event);
    IAction getAction();
    void place(Room room);
}
