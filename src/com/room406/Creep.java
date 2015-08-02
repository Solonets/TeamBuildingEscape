package com.room406;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vic on 02.08.15.
 */
public class Creep implements IHuman {
    private String name;
    private Room currentRoom;
    private Random random = new Random();
    private List<Question> tbQuestions;
    private List<Question> notTbQuestions;
    private int influence;

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

    @Override
    public boolean onEvent(Event event) {
        return true;
    }

    @Override
    public IAction getAction() {
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
