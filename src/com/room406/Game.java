package com.room406;

import com.room406.actions.Code;
import com.room406.actions.Eat;
import com.room406.actions.IAction;
import com.room406.actions.Move;
import com.room406.dialog.Dialog;
import com.room406.events.Event;
import com.room406.humans.Creep;
import com.room406.humans.Player;
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
    private final int TICKS_PER_TIMBUILDING = 36;
    private int tick = 0;
    private List<Creep> creeps;
    private List<Event> events = new ArrayList<>();

    private int codeProgress = 0;
    private final int wholeCodeProgress = 10;
    private boolean isPlayerCoding = false;


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
            if (event.getTick() <= tick) {
                result.add(event);
            }
        }
        events.removeAll(result);
        return result;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void control()
    {
        IAction action;
        if (isPlayerCoding) {
            if (player.coding()) {
                codeProgress += player.getCodingSkills();
                return;
            }
            isPlayerCoding = false;
        }
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
                    if (!dialogResult) {
                        if(creep.getSex() == Creep.CreepSex.FEMALE) {
                            System.out.println(creep.getName() + " сравняла тебя взглядом с землей и отправила на тимбилдинг");
                        }
                        else {
                            System.out.println(creep.getName() + " сравнял тебя взглядом с землей и отправил на тимбилдинг");
                        }
                        System.out.println("Тебя отвели на тимбилдинг. Так как ты опоздал, тимбилдинг начался с начала.");
                        tick += TICKS_PER_TIMBUILDING;
                    } else {
                        if(creep.getSex() == Creep.CreepSex.FEMALE) {
                            System.out.println(creep.getName() + " посчитала твои доводы достаточно весомыми, да ты влятельный человек");
                        }
                        else {
                            System.out.println(creep.getName() + " посчитал твои доводы достаточно весомыми, да ты влятельный человек");
                        }
                    }
                    break;
                }
            }
        }
        else if (action instanceof Eat)
        {
            System.out.println("ням-ням-ням");
            tick += 2;
        } else if (action instanceof Code) {
            isPlayerCoding = true;
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
        System.out.println(String.format("Прогресс задания %d процентов", (codeProgress * 100) / wholeCodeProgress));
        if (!player.getPlace().isVisited())
        {
            if (player.getPlace().isGeneratable())
            {
                RandomFiller.fillRandomInventory(player.getPlace());
            }
            System.out.println(player.getPlace().getHistory());
            player.getPlace().setVisited();

        }
        System.out.println(player.getPlace().getDescription());
        for (Event event: getEvents()) {
            boolean result = player.onEvent(event);
            if (!result) {
                end();
            }
            for (Creep creep : creeps) {
                creep.onEvent(event);
            }
        }
        return true;
    }

    private void end() {
        System.out.println("К сожалению, ты не смог закончиить квест. Тебя выгнали из Иннополиса. \nМожешь попробовать в следующем году");
        System.exit(0);
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
