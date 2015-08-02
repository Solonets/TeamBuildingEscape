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
        System.out.print(">> ");
        String command = scanner.next().toLowerCase();
        if (command.equals(Message.SHOW_INVENTORY.toString().toLowerCase())) {
            System.out.println(Message.INVENTORY);
            for (InventoryItem item : inventory) {
                System.out.println(item);
            }
        } else if (command.equals(Message.ACTION_GO.toString().toLowerCase())) {
            if (getInventory().isEmpty()) {

            }
            String roomString = scanner.nextLine().toLowerCase();
            for (Room room: currentRoom.getAvailableRooms()) {
                if (room.isOpen() && roomString.contains(room.getName().toLowerCase())) {
                    return new Move(room);
                }
            }
        } else if (command.equals(Message.USE.toString().toLowerCase())) {
        } else if (command.equals(Message.WATCH_AROUND.toString().toLowerCase())) {
            System.out.println(currentRoom.getDescription());
        } else if (command.equals(Message.PICK.toString().toLowerCase())) {
            String itemString = scanner.nextLine().toLowerCase();
            for (InventoryItem item: currentRoom.getInventoryItems()) {
                if (item.toString().toLowerCase().equals(itemString)) {
                    inventory.add(item);
                    currentRoom.getItem(item);
                }
            }
        } else {
            System.out.println(Message.ERROR);
        }
        return null;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    @Override
    public void place(Room room) {
        currentRoom = room;
    }

    public Room getPlace() {
        return currentRoom;
    }
}
