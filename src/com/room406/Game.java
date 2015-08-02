package com.room406;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by user on 02.08.2015.
 */
public class Game {
    private final String roomsIni = "Rooms.ini";
    private Room currentRoom = null;
    private Player player = null;
    public Game() {
        Room room = new Room("Комната", "Ты находишься у себя в комнате. Наступили выходные и у тебя есть всего два дня, чтобы накодить квест." +
                "Тебе еще не выдали задание ты можешь получить его у своего TA.", "Описание", true);
        Room holeCampus = new Room("Кампус", "История", "Описание", true);
        Room holeUniversityFloor1 = new Room("1 этаж общежития", "История", "Описание", true);
        Room holeUniversityFloor2 = new Room("2 этаж общежития", "История", "Описание", true);
        Room holeUniversityFloor3 = new Room("3 этаж общежития", "История", "Описание", true);
        Room university317 = new Room("3 этаж общежития", "TA нет в университете. Зато он есть в Телеграме.", "Описание", true);
        Room holeUniversityFloor4 = new Room("4 этаж общежития", "История", "Описание", true);
        room.pushAvailableRoom(holeCampus);

        holeCampus.pushAvailableRoom(room);
        holeCampus.pushAvailableRoom(holeUniversityFloor1);

        holeUniversityFloor1.pushAvailableRoom(holeCampus);
        holeUniversityFloor1.pushAvailableRoom(holeUniversityFloor2);
        holeUniversityFloor1.pushAvailableRoom(holeUniversityFloor3);
        holeUniversityFloor1.pushAvailableRoom(holeUniversityFloor4);

        holeUniversityFloor2.pushAvailableRoom(holeUniversityFloor1);
        holeUniversityFloor2.pushAvailableRoom(holeUniversityFloor3);
        holeUniversityFloor2.pushAvailableRoom(holeUniversityFloor4);

        holeUniversityFloor3.pushAvailableRoom(holeUniversityFloor1);
        holeUniversityFloor3.pushAvailableRoom(holeUniversityFloor2);
        holeUniversityFloor3.pushAvailableRoom(holeUniversityFloor4);
        holeUniversityFloor3.pushAvailableRoom(university317);

        university317.pushAvailableRoom(holeUniversityFloor3);

        holeUniversityFloor4.pushAvailableRoom(holeUniversityFloor1);
        holeUniversityFloor4.pushAvailableRoom(holeUniversityFloor2);
        holeUniversityFloor4.pushAvailableRoom(holeUniversityFloor3);

        currentRoom = room;
        player = new Player("Булат");
        player.place(room);
    }
    public void Control()
    {
        IAction action = player.getAction();
        if (action instanceof Move)
        {
            Move move = (Move)action;
            player.place(move.getRoom());
        }
    }
    public boolean Model()
    {
        if (!currentRoom.isVisited())
        {
            System.out.println(currentRoom.getHistory());
            currentRoom.setVisited();
        }
        System.out.println(currentRoom.getDescription());
        return true;
    }
    public void Execute()
    {
        while(Model())
        {
            Control();
        }
    }
}
