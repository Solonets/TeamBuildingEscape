package com.room406;

import javax.swing.*;
import javax.swing.plaf.synth.SynthButtonUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by user on 02.08.2015.
 */
public class Game {
    private final String roomsIni = "Rooms.ini";
    private Player player = null;
    public Game() {
        Room room = new Room("Номер 406", "Ты находишься в комнате 406. Наступили выходные и у тебя есть всего два дня, чтобы накодить квест." +
                " Тебе еще не выдали задание, но ты можешь получить его у своего TA.", "Это твоя комната", true);
        Room holeCampus = new Room("Кампус", "История", "Описание", true);
        Room holeUniversityFloor1 = new Room("1 этаж университета", "История", "Описание", true);
        Room holeUniversityFloor2 = new Room("2 этаж университета", "История", "Описание", true);
        Room holeUniversityFloor3 = new Room("3 этаж университета", "История", "Описание", true);
        Room university317 = new Room("Кабинет 317", "TA нет в университете. Зато он есть в Telegram.", "Описание", true);
        Room holeUniversityFloor4 = new Room("4 этаж университета", "История", "Описание", true);
        room.pushAvailableRoom(holeCampus);

        Creep lena = new Creep("Лена");

        InventoryItem documents = new InventoryItem("Документы", 10000);
        Question q1 = new Question("Ты почену не на тимбилдинге?");
        Answer a1 = new Answer("Ну... так вышло", -10, "Пойдем-ка, я тебя провожу");
        q1.addAnswer(a1);
        Answer a2 = new Answer("Не заметил, что он начался...", 0, "Пойдем-ка, я тебя проведу");
        q1.addAnswer(a2);
        Answer a3 = new Answer("Несу сдавать документы", 10, "Давай я сама отнесу");
        a3.setDependency(documents);
        q1.addAnswer(a3);
        Answer a4 = new Answer("<Притвориться ниндзя и сделать кувырок>", -20, "... Брысь на тимбилдинг");
        lena.addTbQuestion(q1);

        Question q2 = new Question("Привет");
        a1 = new Answer("Привет", 10, "");
        q2.addAnswer(a1);
        lena.addNotTbQuestion(q2);

        Creep sasha = new Creep("Саша");

        q1 = new Question("Идешь на тимбилдинг сегодня?");
        a1 = new Answer("Ну ... я кодить собирался...", -20, "Идешь значит!");
        q1.addAnswer(a1);
        a2 = new Answer("Я документы несу, не знаю когда освобожусь", -10, "Ну ты постарайся прийти, а то мы собирались ...");
        q1.addAnswer(a2);
        q2 = new Question("Привет");
        a1 = new Answer("Привет", 10, "");
        q2.addAnswer(a1);
        sasha.addNotTbQuestion(q1);
        sasha.addNotTbQuestion(q2);

        Creep timur = new Creep("Тимур");
        q1 = new Question("Ты почену не на тимбилдинге?");
        a1 = new Answer("У нас в общаге крыша протекает", 10000, "Мы работаем над этим");
        q1.addAnswer(a1);
        a2 = new Answer("Я хотел чего-нибудь поделать...", -10, "Иди на тимбилдинг");
        q1.addAnswer(a2);
        a3 = new Answer("Я документы несу", 20, "Хорошо");
        q1.addAnswer(a3);
        q2 = new Question("Привет");
        a1 = new Answer("Привет", 10, "");
        q2.addAnswer(a1);


        holeCampus.pushAvailableRoom(room);
        for (int i = 400; i <= 410; i++)
        {
            if (i != 406)
            {
                Room r = new Room("Номер " + i, "", "", true);
                r.pushAvailableRoom(holeCampus);
                RandomFiller.fillRandomInventory(r);
                holeCampus.pushAvailableRoom(r);
            }
        }
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

        player = new Player("Булат");
        player.place(room);
    }
    public void control()
    {
        IAction action = player.getAction();
        if (action instanceof Move)
        {
            Move move = (Move)action;
            player.place(move.getRoom());
        }
    }
    public boolean model()
    {
        if (!player.getPlace().isVisited())
        {
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
        }
    }
}
