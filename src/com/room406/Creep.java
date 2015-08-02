package com.room406;

import java.util.List;
import java.util.Random;

/**
 * Created by vic on 02.08.15.
 */
public class Creep implements IHuman {
    private String name;
    private Room currentRoom;
    private Random random = new Random();

    public Creep(String name) {
        this.name = name;
    }


    @Override
    public void onEvent(Event event) {

    }

    @Override
    public Action move() {
        while (true) {
            List<Room> avaliableRooms = currentRoom.getAvailableRooms();
            avaliableRooms.add(currentRoom);
            avaliableRooms.add(currentRoom);
            int nextRoomNumber = random.nextInt(avaliableRooms.size());
            Room nextRoom = avaliableRooms.get(nextRoomNumber);
            if (nextRoom.isOpen()) {
                currentRoom = nextRoom;
                return null;
            }
        }
    }

    @Override
    public void place(Room room) {
        currentRoom = room;
    }
}
