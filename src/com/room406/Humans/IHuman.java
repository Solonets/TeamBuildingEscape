package com.room406.Humans;

import com.room406.Actions.IAction;
import com.room406.Room;
import com.room406.events.Event;

/**
 * Created by vic on 02.08.15.
 */
public interface IHuman {
    boolean onEvent(Event event);
    IAction getAction();
    void place(Room room);
}
