package com.room406;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by vic on 02.08.15.
 */
public class Player implements IHuman {
    private String name;
    private Room currentRoom;
    private Scanner scanner = new Scanner(System.in);
    private Set<Room> visitedRooms;

    public Player(String name) {
        this.name = name;
        visitedRooms = new HashSet<Room>();
    }

    @Override
    public void onEvent(Event event) {

    }

    @Override
    public Action move() {
        if (visitedRooms.contains(currentRoom)) {

        }
        String command = scanner.next();
        switch (command) {
            case "инвентарь":
                break;
        }
        return null;
    }

    @Override
    public void place(Room room) {
        currentRoom = room;
    }
}
