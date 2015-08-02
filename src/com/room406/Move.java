package com.room406;

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
