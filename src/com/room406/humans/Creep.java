package com.room406.humans;

import com.room406.actions.IAction;
import com.room406.actions.Move;
import com.room406.dialog.Question;
import com.room406.events.Event;
import com.room406.events.TeambuildingEvent;
import com.room406.rooms.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vic on 02.08.15.
 */
public class Creep implements IHuman, Serializable {
    public enum CreepSex {
        FEMALE, MALE;
    }
    private String name;
    private Room currentRoom;
    private transient Random random = new Random();
    private List<Question> tbQuestions;
    private List<Question> notTbQuestions;
    private int influence;
    private CreepSex sex;
    private boolean isTb = false;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getInfluence() {
        return influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public String getName() {
        return name;
    }


    public Creep(String name, CreepSex sex) {
        this.name = name;
        this.tbQuestions = new ArrayList<>();
        this.notTbQuestions = new ArrayList<>();
        this.sex = sex;
    }


    public CreepSex getSex() {
        return sex;
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
            List<Room> avaliableRooms = new ArrayList();
            avaliableRooms.add(currentRoom);
            avaliableRooms.addAll(currentRoom.getAvailableRooms());
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

    @Override
    public String toString() {
        return name;
    }
}
