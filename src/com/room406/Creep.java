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
    private List<Question> questions;

    public Creep(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
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
                currentRoom = nextRoom;
                return null;
            }
        }
    }

    @Override
    public void place(Room room) {
        currentRoom = room;
    }
}
