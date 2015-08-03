package com.room406;

import com.room406.humans.Creep;
import com.room406.humans.Player;
import com.room406.dialog.Answer;
import com.room406.dialog.Question;
import com.room406.inventory.Computer;
import com.room406.inventory.InventoryItem;
import com.room406.inventory.Phone;
import com.room406.inventory.Schedule;
import com.room406.rooms.Room;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        //Game game = new Game();
        //game.execute();
        createGame();
        Game game = loadGame();
        game.execute();
    }

    public static Game loadGame() {
        Game game = null;

        try {
            FileInputStream fis = new FileInputStream("game");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Player player = new Player("Булат");
        player.place(game.getInitialRoom());

        game.setPlayer(player);

        return game;
    }

    public static void createGame() {
        Game game = new Game();
        Room room = new Room("Номер 406", "Ты находишься в комнате 406. Наступили выходные и у тебя есть всего два дня, чтобы накодить квест." +
                " Тебе еще не выдали задание, но ты можешь получить его у своего TA.", "Это твоя комната", true, true);
        room.pushItem(new Schedule());
        room.pushItem(new Phone());
        Room holeCampus = new Room("Кампус", "По кампусу не ходят организаторы, здесь ты в безопасности", "Длинный коридор с дверьми в комнаты", true, false);
        Room holeUniversityFloor1 = new Room("1 этаж университета", "На первом этаже находится столовая", "Просторное помещение с двумя основными кабинетами для лекций", true, false);
        Room holeUniversityFloor2 = new Room("2 этаж университета", "На втором этаже находится много кабинетов для занятий", "На втором этаже можно подшлянуть в лекторные первого этажа", true, false);
        Room holeUniversityFloor3 = new Room("3 этаж университета", "На третьем этаже находятся кабинеты для занятий программированием", "Много прозрачных смотровых и читальный зал", true, false);
        Room university317 = new Room("Кабинет 317", "TA нет в университете. Зато он есть в Telegram.", "Обычный кабинет для занятий по программированию", true, false);
        Room holeUniversityFloor4 = new Room("4 этаж университета", "На четвертом этаже находятся кабинеты английского", "много кабинетов и небольшая лужица рядом с лестницей", true, false);
        Room It = new Room("IT отдел", "IT отдел, в нем никого нет", "It Отдел заставлен компьютерами", true, false);
        It.pushItem(new Computer("%s лежит на столе"));
        It.pushItem(new Computer("под столом лежит %s"));
        It.pushItem(new Computer("на подоконике лежит еще один %s"));
        It.pushAvailableRoom(holeUniversityFloor4);
        holeUniversityFloor4.pushAvailableRoom(It);
        room.pushAvailableRoom(holeCampus);

        Creep lena = new Creep("Лена");

        InventoryItem documents = new InventoryItem("Документы", "Документы", "Это твои документы для перевода", 10000, "%s");
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

        game.addCreep(lena);
        game.addCreep(sasha);
        game.addCreep(timur);

        holeCampus.pushAvailableRoom(room);
        for (int i = 400; i <= 410; i++)
        {
            if (i != 406)
            {
                Room r = new Room("Номер " + i, "Это чья-то комната, она почему-то была открыта", "Это чья-то комната", true, true);
                r.pushAvailableRoom(holeCampus);
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
        for (int i = 300; i <= 310; i++)
        {
            if (i != 307)
            {
                Room r = new Room("Кабинет " + i, "Кабинет программирования", "Кабинет программирования", true, true);
                r.pushAvailableRoom(holeUniversityFloor3);
                holeUniversityFloor3.pushAvailableRoom(r);
            }
        }
        for (int i = 400; i <= 410; i++)
        {
                Room r = new Room("Кабинет " + i, "Кабинет английского языка", "Кабинет английского языка", true, true);
                r.pushAvailableRoom(holeUniversityFloor4);
                holeUniversityFloor4.pushAvailableRoom(r);
        }

        for (int i = 200; i <= 210; i++)
        {
            Room r = new Room("Кабинет " + i, "Кабинет для занятий", "Кабинет для занятий", true, true);
            r.pushAvailableRoom(holeUniversityFloor2);
            holeUniversityFloor2.pushAvailableRoom(r);
        }


        university317.pushAvailableRoom(holeUniversityFloor3);

        holeUniversityFloor4.pushAvailableRoom(holeUniversityFloor1);
        holeUniversityFloor4.pushAvailableRoom(holeUniversityFloor2);
        holeUniversityFloor4.pushAvailableRoom(holeUniversityFloor3);

        lena.place(holeUniversityFloor1);
        timur.place(holeUniversityFloor2);
        sasha.place(holeUniversityFloor3);
        game.setInitialRoom(room);

        try {
            FileOutputStream fos = new FileOutputStream("game");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
