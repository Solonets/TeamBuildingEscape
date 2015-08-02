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
    private boolean isHungry;
    private int influence;

    public int getInfluence() {
        return influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public Player(String name) {
        this.name = name;
        visitedRooms = new HashSet<Room>();
    }

    @Override
    public boolean onEvent(Event event) {
        if (event instanceof EatEvent) {
            if (event.getType().equals(Event.EventType.STARTED)) {
                isHungry = true;
            } else {
                if (isHungry) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public IAction getAction() {
        if (!visitedRooms.contains(currentRoom)) {
            visitedRooms.add(currentRoom);
            System.out.println(currentRoom.getHistory());
        }
        String command = scanner.next().toLowerCase();
        if (command.equals(Message.SHOW_INVENTORY)) {
            System.out.println(Message.INVENTORY);
            for (InventoryItem item : inventory) {
                System.out.println(item);
            }
        } else if (command.equals(Message.ACTION_GO)) {
            String roomString = scanner.nextLine().toLowerCase();
            for (Room room: currentRoom.getAvailableRooms()) {
                if (room.isOpen() && roomString.contains(room.getName())) {
                    return new Move(room);
                }
            }
        } else if (command.equals(Message.USE)) {
        } else if (command.equals(Message.WATCH_AROUND)) {
            System.out.println(currentRoom.getDescription());
        } else if (command.equals(Message.PICK)) {
            System.out.println(Message.PICKED_INVENTORY);
            inventory.addAll(currentRoom.getInventoryItems());
        } else {
            System.out.println(Message.ERROR);
        }
        return null;
    }

    @Override
    public void place(Room room) {
        currentRoom = room;
    }
}
