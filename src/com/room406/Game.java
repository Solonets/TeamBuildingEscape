package com.room406;

import com.room406.actions.IAction;
import com.room406.actions.Move;
import com.room406.dialog.Dialog;
import com.room406.events.Event;
import com.room406.humans.Creep;
import com.room406.humans.Player;
import com.room406.inventory.InventoryItem;
import com.room406.rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.08.2015.
 */
public class Game implements Serializable {
    private transient Player player = null;
    private final int MINUTES_PER_TICK = 5;
    private int tick = 0;
    private List<Creep> creeps;
    private List<Event> events = new ArrayList<>();

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Room getInitialRoom() {
        return initialRoom;
    }

    public void setInitialRoom(Room initialRoom) {
        this.initialRoom = initialRoom;
    }

    private Room initialRoom;
    public Game() {
        creeps = new ArrayList<>();
    }
    public void addCreep(Creep creep) {
        creeps.add(creep);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Event> getEvents() {
        List<Event> result = new ArrayList();
        for (Event event : events) {
            if (event.getTick() == tick) {
                result.add(event);
            }
        }
        return result;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void control()
    {
        IAction action;
        do {
            action = player.getAction();
        } while (action == null);
        if (action instanceof Move)
        {
            Move move = (Move)action;
            player.place(move.getRoom());

            for (Creep creep : creeps) {
                if (creep.getCurrentRoom() == player.getPlace()) {
                    Dialog dialog = new Dialog(creep, player);
                    boolean dialogResult = dialog.dialog();
                    if (dialogResult) {
                        System.out.println(player + " победил");
                    } else {
                        System.out.println(player + " проиграл");
                    }
                    break;
                }
            }
        }
    }

    private String tickToTime() {
        int hours = (8 + tick / 12) % 24;
        int minutes = (tick % 12) * 5;
        return String.format("%d:%02d", hours, minutes);
    }

    public List<Creep> getCreeps() {
        return creeps;
    }

    public boolean model()
    {
        System.out.println("Сейчас " + tickToTime());
        if (!player.getPlace().isVisited())
        {
            if (player.getPlace().isGeneratable())
            {
                RandomFiller.fillRandomInventory(player.getPlace());
            }
            player.getPlace().setVisited();
            //System.out.println(player.getPlace().getHistory());
            /*for (Creep creep : creeps) {
                if (creep.getCurrentRoom() == player.getPlace()) {
                    System.out.print("\nЗдесь находится " + creep);
                }
            }*/
        }
        System.out.println(player.getPlace().getDescription());
        //System.out.println();
        for (Event event: getEvents()) {
            player.onEvent(event);
            for (Creep creep : creeps) {
                creep.onEvent(event);
            }
        }
        return true;
    }
    public void execute()
    {
        while (model())
        {
            control();
            tick++;
        }
    }
}
