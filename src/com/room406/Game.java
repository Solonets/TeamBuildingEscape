package com.room406;

import com.room406.Actions.IAction;
import com.room406.Actions.Move;
import com.room406.Humans.Creep;
import com.room406.Humans.Player;
import com.room406.inventory.InventoryItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02.08.2015.
 */
public class Game implements Serializable {
    private transient final String roomsIni = "Rooms.ini";
    private transient Player player = null;
    private final int MINUTES_PER_TICK = 5;
    private int tick = 0;
    private List<Creep> creeps;

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
        }
    }
    public boolean model()
    {
        System.out.println("Сейчас " + tick + " тик");
        if (!player.getPlace().isVisited())
        {
            if (player.getPlace().isGeneratable())
            {
                RandomFiller.fillRandomInventory(player.getPlace());
            }
            System.out.println(player.getPlace().getHistory());
            player.getPlace().setVisited();
        }
        else
        {
            System.out.println(player.getPlace().getDescription());
        }
        for (InventoryItem r: player.getPlace().getInventoryItems())
        {
            System.out.println(r.getLocation());
        }
        System.out.print("Доступные локации: ");
        for (Room r: player.getPlace().getAvailableRooms())
        {
            System.out.print(r.getName() + ", ");
        }
        System.out.println();
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
