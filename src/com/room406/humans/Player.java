package com.room406.humans;

import com.room406.actions.Eat;
import com.room406.actions.IAction;
import com.room406.actions.Move;
import com.room406.events.PhoneMessageEvent;
import com.room406.events.TeambuildingEvent;
import com.room406.inventory.InventoryItem;
import com.room406.Message;
import com.room406.inventory.Phone;
import com.room406.rooms.DinnerRoom;
import com.room406.rooms.Room;
import com.room406.events.EatEvent;
import com.room406.events.Event;

import java.util.*;

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
        int result = influence;
        for (InventoryItem item : inventory) {
            result += item.getInfluence();
        }
        return result;
    }

    public void addInfluence(int add) {
        influence += add;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public Player(String name) {
        this.inventory = new ArrayList<>();
        this.name = name;
    }

    @Override
    public boolean onEvent(Event event) {
        System.out.println(event);
        if (event instanceof EatEvent) {
            if (event.getType().equals(Event.EventType.STARTED)) {
                isHungry = true;
            } else {
                if (isHungry) {
                    return false;
                }
            }
        } else if (event instanceof PhoneMessageEvent) {
            Phone phone = getPhone();
            if (phone != null) {
                phone.pushMessage(((PhoneMessageEvent) event).getMessage());
                System.out.println(event);
            }
        } else if (event instanceof TeambuildingEvent) {
            if (event.getType().equals(Event.EventType.STARTED)) {

            } else {

            }
        }
        return true;
    }

    private Phone getPhone() {
        for (InventoryItem item : inventory) {
            if (item instanceof Phone) {
                return (Phone) item;
            }
        }
        return null;
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
            System.out.println(Message.CANT_GO_THERE);
        } else if (command.equals(Message.USE.toString().toLowerCase())) {
            String itemString = scanner.nextLine().toLowerCase().trim();
            for (InventoryItem item: inventory) {
                if (item.equals(itemString)) {
                    return item.use();
                }
            }
        } else if (command.equals(Message.WATCH_AROUND.toString().toLowerCase())) {
            System.out.println(currentRoom.getDescription());
        } else if (command.equals(Message.EAT.toString().toLowerCase())) {
            if (currentRoom instanceof DinnerRoom && isHungry) {
                isHungry = false;
                return new Eat();
            }
        } else if (command.equals(Message.PICK.toString().toLowerCase())) {
            String itemString = scanner.nextLine().toLowerCase().trim();
            boolean find = false;
            for (InventoryItem item: currentRoom.getInventoryItems()) {
                if (item.equals(itemString)) {
                    if (hasItem(item)) {
                        System.out.println(Message.HAVE_IT);
                    } else {
                        inventory.add(item);
                        currentRoom.getItem(item);
                        item.pickUp();
                        System.out.println(item.getPickMessage());
                    }
                    find = true;
                    break;
                }
            }
            if (!find) {
                System.out.println(Message.HAVE_NOT_THIS);
            }
        } else {
            scanner.nextLine();
            System.out.println(Message.ERROR);
        }
        return null;
    }

    public boolean hasItem(InventoryItem item) {
        for (InventoryItem i : inventory) {
            if (i.equals(item.getName())) {
                return true;
            }
        }
        return false;
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

    @Override
    public String toString() {
        return name;
    }
}
