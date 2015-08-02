package com.room406.Actions;

import com.room406.Actions.IAction;
import com.room406.Room;

/**
 * Created by ������ on 02.08.2015.
 */
public class Move implements IAction {
    private Room room;

    public Move(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
