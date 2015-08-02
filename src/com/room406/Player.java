package com.room406;

import java.util.HashSet;
import java.util.List;
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
    private List<InventoryItem> inventory;

    public Player(String name) {
        this.name = name;
        visitedRooms = new HashSet<Room>();
    }

    @Override
    public void onEvent(Event event) {

    }

    @Override
    public Action move() {
        if (!visitedRooms.contains(currentRoom)) {
            visitedRooms.add(currentRoom);
            System.out.println(currentRoom.getDescription());
        }
        String command = scanner.next();
        if (command.equals(Message.SHOW_INVENTORY)) {
        } else if (command.equals(Message.ACTION_GO)) {
        } else if (command.equals(Message.USE)) {
        } else if (command.equals(Message.PICK)) {
        } else {
            System.out.println("Я не понял, чего ты хочешь!");

        }
        return null;
    }

    @Override
    public void place(Room room) {
        currentRoom = room;
    }
}
