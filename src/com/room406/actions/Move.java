package com.room406.actions;

import com.room406.rooms.Room;

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
