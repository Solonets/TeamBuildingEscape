package com.room406.humans;

import com.room406.actions.IAction;
import com.room406.actions.Move;
import com.room406.dialog.Question;
import com.room406.rooms.Room;
import com.room406.events.Event;
import com.room406.events.TeambuildingEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vic on 02.08.15.
 */
public class Creep implements IHuman, Serializable {
    private String name;
    private Room currentRoom;
    private transient Random random = new Random();
    private List<Question> tbQuestions;
    private List<Question> notTbQuestions;
    private int influence;
    private boolean isTb = false;


    public int getInfluence() {
        return influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public Creep(String name) {
        this.name = name;
        this.tbQuestions = new ArrayList<>();
        this.notTbQuestions = new ArrayList<>();
    }

    public List<Question> getTbQuestions() {
        return tbQuestions;
    }

    public void addTbQuestion(Question question) {
        tbQuestions.add(question);
    }

    public List<Question> getNotTbQuestions() {
        return notTbQuestions;
    }

    public void addNotTbQuestion(Question question) {
        notTbQuestions.add(question);
    }

    public List<Question> getQuestions() {
        if (isTb) {
            return tbQuestions;
        }
        return notTbQuestions;
    }

    @Override
    public boolean onEvent(Event event) {
        if (event instanceof TeambuildingEvent) {
            if (event.getType().equals(Event.EventType.STARTED)) {
                isTb = true;
            } else {
                isTb = false;
            }
        }
        return true;
    }

    @Override
    public IAction getAction() {
        if (random == null) {
            random = new Random();
        }
        while (true) {
            List<Room> avaliableRooms = currentRoom.getAvailableRooms();
            avaliableRooms.add(currentRoom);
            avaliableRooms.add(currentRoom);
            int nextRoomNumber = random.nextInt(avaliableRooms.size());
            Room nextRoom = avaliableRooms.get(nextRoomNumber);
            if (nextRoom.isOpen()) {
                return new Move(nextRoom);
            }
        }
    }

    @Override
    public void place(Room room) {
        currentRoom = room;
    }
}
