package com.room406;

import com.room406.events.EatEvent;
import com.room406.events.Event;
import com.room406.events.PhoneMessageEvent;
import com.room406.events.TeambuildingEvent;
import com.room406.humans.Creep;
import com.room406.humans.Player;
import com.room406.dialog.Answer;
import com.room406.dialog.Question;
import com.room406.inventory.*;
import com.room406.rooms.DinnerRoom;
import com.room406.rooms.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Phone phone = new Phone();
        room.pushItem(phone);
        Room holeCampus = new Room("Кампус", "По кампусу не ходят организаторы, здесь ты в безопасности", "Длинный коридор с дверьми в комнаты", true, false);
        Room holeUniversityFloor1 = new Room("1 этаж университета", "На первом этаже находится столовая", "Просторное помещение с двумя основными кабинетами для лекций", true, false);
        DinnerRoom dr = new DinnerRoom();
        holeUniversityFloor1.pushAvailableRoom(dr);
        dr.pushAvailableRoom(holeUniversityFloor1);
        Room holeUniversityFloor2 = new Room("2 этаж университета", "На втором этаже находится много кабинетов для занятий", "На втором этаже можно подшлянуть в лекторные первого этажа", true, false);
        Room holeUniversityFloor3 = new Room("3 этаж университета", "На третьем этаже находятся кабинеты для занятий программированием", "Много прозрачных смотровых и читальный зал", true, false);
        Room university317 = new Room("Кабинет 317", "TA нет в университете. Зато он есть в Telegram.", "Обычный кабинет для занятий по программированию", true, false);
        Room holeUniversityFloor4 = new Room("4 этаж университета", "На четвертом этаже находятся кабинеты английского", "много кабинетов и небольшая лужица рядом с лестницей", true, false);
        Room It = new Room("IT отдел", "IT отдел, в нем никого нет", "It Отдел заставлен компьютерами", true, false);
        It.pushItem(new Computer(phone, "%s лежит на столе"));
        It.pushItem(new Computer(phone, "под столом лежит %s"));
        It.pushItem(new Computer(phone, "на подоконике лежит еще один %s"));
        It.pushItem(new Computer(phone, "Еще 20 %sов лежат на полке"));
        It.pushAvailableRoom(holeUniversityFloor4);
        holeUniversityFloor4.pushAvailableRoom(It);
        room.pushAvailableRoom(holeCampus);

        Creep lena = new Creep("Лена", Creep.CreepSex.FEMALE);
        lena.setInfluence(50);
        InventoryItem documents = new InventoryItem("Документы", "Документы", "Это твои документы для перевода", 0, 0, "%s");
        Question q1 = new Question("Ты почену не на тимбилдинге?");
        Answer a1 = new Answer("Ну... так вышло", -5, "_");
        q1.addAnswer(a1);
        Answer a2 = new Answer("Не заметил, что он начался...", 0, "_");
        q1.addAnswer(a2);
        Answer a3 = new Answer("Несу сдавать документы", 5, "Давай я сама отнесу");
        a3.setDependency(documents);
        q1.addAnswer(a3);
        Answer a5 = new Answer("Не увидел в чате сообщение из-за флуда", 5, "Я же просила не ФЛУДИТЬ!");
        q1.addAnswer(a5);
        Answer a4 = new Answer("<Притвориться ниндзя и сделать кувырок>", -10, "_");
        q1.addAnswer(a4);
        Answer a6 = new Answer("Там куча web-программистов. Я их боюсь...", -1, "_");
        q1.addAnswer(a6);
        lena.addTbQuestion(q1);

        Question q2 = new Question("Привет");
        a1 = new Answer("Привет", 1, "");
        q2.addAnswer(a1);
        lena.addNotTbQuestion(q2);

        Creep sasha = new Creep("Саша", Creep.CreepSex.FEMALE);
        sasha.setInfluence(100);

        q1 = new Question("Идешь на тимбилдинг сегодня?");
        a1 = new Answer("Ну ... я кодить собирался...", -5, "Идешь значит!");
        q1.addAnswer(a1);
        a2 = new Answer("Я документы несу, не знаю когда освобожусь", 3, "Ну ты постарайся прийти, а то мы собирались ...");
        q1.addAnswer(a2);
        q2 = new Question("Привет");
        a1 = new Answer("Привет", 1, "");
        q2.addAnswer(a1);
        Question q3 = new Question("Ты пришел на наш тимбилдинг)");
        a1 = new Answer("...", -10, "" +
                "                                                                                                                                                      \n" +
                "                                                                                                `..`                                                  \n" +
                "                                                                                      ``````..-:::::::--:::::--.`                                     \n" +
                "                                                                                  `.-:::::-------:::::-------------`                                  \n" +
                "                                                                               `-::-:--.````.-:://:-...`..----::::///-`                               \n" +
                "                                                                             .-:::--.`` ```.-::::-.`````..--:////oooss+/-.                            \n" +
                "                                                                           .-:::::-.`   ``.--:::-``   `.-://++++++++oooooo+:`                         \n" +
                "                                                                         `---::::-.``````.---::-``  ``.-://+ooooo+++//++o+oo+/:-`                     \n" +
                "                                                                       `.----::::.`````.--::::--````.-://+oosssoo+//::::////+++oo+:`                  \n" +
                "                                                                      `----:::::-.....--::///:--...-:/++ooosssooo++/:::--:::////+oss/.                \n" +
                "                                    `...`                            `----:::::::----:-:::///:::::/++ooosssssooo++/::-:::////++///+oso/`              \n" +
                "            `dmmms-mmmmmmmmmhsmm/`odNNNNNd+`                        .---:::::::::-:----:////:://+ooooossssssooo++//::://+++////++//+oos+`             \n" +
                "            `mMMMh-mmNMMMMmmysMM:oMMMmomMMM/                      `.---::::/::/::------////:://oosssssssooooo+++//::/+++ooo+++osoo+++oos+`            \n" +
                "            `mMMMh ``sMMMM-``+h/ yMMMd`sddd/                     `.----:::/::/:-----..://+/:///+oooooooo++++++++//:/+oooossosssssssoooosy:            \n" +
                "            `mMMMh   oMMMM.  ``  /NMMMmy/.`                      .-:---::/::/:----//:/++++/+/++/+ooooo+++ooooo+++/++ooosssssyyyyyyyyysosyo`           \n" +
                "            `mMMMh   oMMMM.       :ymMMMMmo`                    .-:----:/:-/:-----::++ooo++++++++ooosoooooosssoo++oosyyyyhhhhhhhhhdhhsssys`           \n" +
                "            `mMMMh   oMMMM.      .-:/oyNMMMy                   `--:---:::-/:-::-::/+oooooo+++++++oossssssssosyyssyyyyhhdddddddmdddmmdhysyy`           \n" +
                "            `mMMMh   oMMMM.      oNNNh sMMMm`                  --:::--:/://:::/:/+ooooooo+//+o+++ossssssssssosysyyhdddddddmmmmmmmmmmmddhys`           \n" +
                "            `mMMMh   oMMMM.      /MMMN/dMMMh                  `--:::-:///+/::://+oooooso/::/+++++oosssssssssoosyyhhdddddmmmmmmmmmmmmmddhys.           \n" +
                "            `dmmmy   ommmm.       +dNNNMNmy-                  .-:::--://////++++oooosso+::////++++oosssssssssoooyyhhhddmmmmmmmmmmmmmmmmhss.           \n" +
                "             `````   `````         `.---.`                  .:--:::--:/+///++oooooosso+/::/++++++++oosssssssosssysyyhdmmmmmmmmmmmmNNNmmhso`           \n" +
                "                                                          ./o/--:::::/++//++ooooossso+/:/+oooo+++ooooosssssssso++ooo+oyhddmmmmmmmNNNNmdys/            \n" +
                "                                                         -/+s/--:::::/+///+ooooosssoo+:/+ssooooooooooosssyyso/::///////+osyhddmmNNNNNmhys.            \n" +
                "                        `------.                       `:///o:--:/::/++///+oooosssso++++ossooossoooosssyhhs/+//::::/+++oosssyhdmNNNNNdyy/             \n" +
                "                        /NNNNNNy                       -ossoo/:-:/::/+++++ooooossso++//ossooossoossssshhyyo++++//++///+oooossyhmmNNNmhyo`             \n" +
                "                        yMMMMMMm`                      -+++syo:-:/::/++//+ooooosssoo++osssoooosssssyyhyyysooosssoossyyyyyyyyyyyydmNmmhs-              \n" +
                "                       `mMMNhMMM:                      -o+yNMd+::/:/+/++++oooooossoooosyssssssysyyhyssyhysosyssyyhdmmNNNNdddddhyymmmdh:               \n" +
                "                       -MMMd+MMMs                      -o+dNNNo:////+/+++++ooooosssyssssssssssyyhhssssyhyssyshddmNMMMMMMMmhhyhhdhdmmds`               \n" +
                "                       +MMMy-NMMd`                     `ooshyms///////++++++oooossysssso+++++++osssssyhdysyydmdhdMNdmMMMMmhyosydddmmh.                \n" +
                "                       hMMMhoNMMM-                      /sso++s///////o++++ooooosooosyo/////++++oosyyhddhyyhmddssmmyyMMMmys+/+ymhddd/                 \n" +
                "                      `NMMMMNMMMMo                      `/yyysss++o+/+s++o+ooooso/+shyoossssssssooosyhddhhyyddhyo+++yhys++/:+sdddddy`                 \n" +
                "                      :MMMMo-dMMMd                       `/yhsssssso+++++++++oo+/+ohhsooo+osyhyssssoshddhdyyhdhhyo+/://///+shdddddh-                  \n" +
                "                      /hhhh- ohhhy`                        /hsoooosso+//+++++oo+oshhssoo+::::/syyyysoyddmddhyhhhhhyo++ooyhdddhddmy.                   \n" +
                "                                                            +oo++osyyo++/+++++oydddhyyhdddhs+/osyhyysyddmmdmdyssyhhhhhddhhyyhdhho.                    \n" +
                "                                                            :+/sdNdhhsosooooyyhhyysshhhmNMNmdyyyyhhyyyhddmmdddyyyyyyyyyyyhddhys:                      \n" +
                "                                                           `/+yNMmdhhoooossooo++oossyyhdmMMMNmhysyhyyyyhdmmmddddhhyyyhhhddhhy/`                       \n" +
                "  .ooooooooo/`+oooo++/-    /ooooo+`  :ooooo+/:` .oooo-      /smNmhhhsoooo+++++++oooossyhdmNMMNmhoohysyysydmmmmmdddhhhhhhhhddo`                        \n" +
                "  :NMMMMMMMMd-NMMMNNMMNs` `mMMMMMM:  oMMMMNMMMd.:MMMM+      /ymdhhhsoooooooo+++++oooossyyhdNNNNmhsshssoosdmmmmmmmmmmmmmmmdmddho+:-..``                \n" +
                "  `//hMMMM+/:-NMMMs/MMMN- -MMMNMMMs  oMMMN:sMMM+.MMMM/      :yhyhyssssyyyyyyyyssoooooosyyyhddmNmhsoysooosdmmNNNNNmmmmmmNNNNddmmNNdo:--.`              \n" +
                "     oMMMN.  .NMMMs:NMMN- +MMMymMMm` oMMMN.oMMM+`NMMM-      .yyyyyhhhdmNNNNNmmddhhysssosyhhhdhhhyhyssyyssdmmmNNNNNNNNNNNNNmmdmmNMMMms:---.`           \n" +
                "     oMMMN.  .NMMMNNMMmo  hMMM/dMMN- oMMMMhmMMM/ mMMN.      -yyyhhdmNMMMMMMMMMMMNNmmdhhyyyyyhddddhhyyyyhsymdmmNNNmNNNNmNNNmmdmmNMMMMNms----.          \n" +
                "     oMMMN.  .NMMMhyMMNy``NMMM.sMMMo oMMMMmddy+` dMMN`      :yhdmNNNNmdddhhhyyhdNNMMMMNmmdhhhyyhhhyyyyyhyydhddmNNNNNNNmNNNmNmmmNMMMMMMN+...-.``       \n" +
                "     oMMMN.  .NMMMo.NMMM-:MMMMhmMMMd`oMMMN-`     hMMm       `oymmmhs+++ooooo+//+oyhdmNNMMNNmmddhhhhhyyyyhyddhdmmmmNNNNmNNmmNNmmmNNNMMMMh``.----``     \n" +
                "     oMMMN.  .NMMMo.NMMM-oMMMMdmMMMN.oMMMN.      oyys`      `+yhyso++ooooossssssooosyhhdmNNNNNNNNmmmdmdmdyhhdddmmmmmNNmNNmmNNmmmmmmNMMNh``.------`    \n" +
                "     oMMMN.  .NMMMo.NMMN:dMMMm`:NMMM/oMMMN.     `NMMM-      `-/ooooooooooosoooooooooossyyyhhhddmmmNNNNmdhssyydmmmmmmmmmNNmmNNmmmmmmNMMm:  `-:---.-.`  \n" +
                "     -++++`  `++++-`++++./+++/ `++++:-++++`      ++++`        .oooooooosssyyssssssssooooossssssssyyyyyssoosyyymmmdmmmmmNNmmmmmmmmmmNNm+`  `.-----.--.`\n" +
                "                                                               :++ooosssyosssyyyyssssssooooooosssssoo++ooosdhyhmmmmmmdmmNmmmmmmmmmmNd+.    -------.---\n" +
                "                                                                `./soosss+oooyyssososyysooooooooooooooosoohmmhymmmmmmdmmmmmmmmmmmmmh:`    `--:---.`..-\n" +
                "                                                                  `+oooooooooosooossyyyysssooooooosssyyyysymmdydmmmdmmmmmdmdddddmmy-      .--:--.`` `.\n" +
                "                                                                    :oooossososoosssyyyyyssssoooosssssssssydmdydmddddmddddddmmmdh+`      .---:--```` `\n" +
                "                                                                    `/+oossysssssssssyyyyysssssssssssyyyhhhhdmhmdhhddddddhhdmmdy-       `----...`     \n" +
                "                                                                    `-../ssyyssssssysyyyyysooooooosssyyyyysyyyyddhyhddhhhhddmh/`      ``.---...       \n" +
                "                                                                  `..``./+/syssssoossyyyyysooooooooossssyyyhhhhhssyhhhhhhddh+.     ````.----..        \n" +
                "                                                              ``.--.  ./++/+syyysossssyyyysssssssoossssyyyyysssssyhyyyhdhs/.`      ```.----..         \n" +
                "                                                          ```.```.`   -o////ohyysoosssssssssssssyyyssssyyyyyyyyyyyyyyyhs:.`      ```..----..`         \n" +
                "                                                        ````   `..    :o////smyyysooosssssssssssssssssssssssssssyyyyyo-``       ``..-----.``          \n" +
                "                                                      ..`      .-`    .o+/+/yNNdssssossyyyyyyyyyyyyyyyyyyyyysyyyyyo:.``        `..-----.`            .\n" +
                "                                                     `.        ..`    `/o/+omNMm+++syyyyyhhhhhhhhhhhdhhhhhhhhhhs+-.``         `..-----.            `..\n" +
                "                                                     `.`.`     ``      .++/smNMMh//osyhyyyyyhyyyyyyysyyyhhyyso+-``          `...----.`            `...\n" +
                "                                                      `...      ``     `-oosmNNNmo:+shhhyyysyyhhhhhhhhhhhhyo/.``         `..--.---.`              ``.-\n" +
                "                                                     ...-.       `      `-/odNNMNh+/shdhhhhhhhhddddmmdddy+:-```        `...-----.`               ``.--\n" +
                "                                                   `.`..-.               ``-+dmNNdo+shddddddddddmmmdhs+:.``         ``....----.`                 `.--.\n" +
                "                                                 ..`  `.-.                   ./sdyysyhdddmmmmdddhys/-.`           `.....---.``                  .---.`\n" +
                "                                                ``    `--.                    ``.-://+ossoo++//:-.`             `.....--..`                    `.--.` \n" +
                "                                                `      .--`                         ``````` ```              ``....---`                        .--..  \n" +
                "                                               ``   ```.--.`                            ``                 ```..---.`                         `.-.``  ");
        q3.addAnswer(a1);
        sasha.addTbQuestion(q3);
        sasha.addNotTbQuestion(q1);
        sasha.addNotTbQuestion(q2);

        InventoryItem water = new InventoryItem("Протекающее окно", "Протекающее окно", "Надо рассказать Тимуру об этом безобразии.", 0, 0, "%s");

        Creep timur = new Creep("Тимур", Creep.CreepSex.MALE);
        timur.setInfluence(50);
        q1 = new Question("Ты почену не на тимбилдинге?");
        a1 = new Answer("У нас в общаге крыша протекает", 10, "Мы работаем над этим");
        q1.addAnswer(a1);
        a2 = new Answer("Я хотел чего-нибудь поделать...", -5, "_");
        q1.addAnswer(a2);
        a3 = new Answer("Я документы несу", 10, "Хорошо");
        q1.addAnswer(a3);
        a4 = new Answer("Сегодня финал по доте", -2, "_");
        q1.addAnswer(a4);
        a5 = new Answer("Да я слишком крутой для этого. <Поправляет ручку в переднем кармане>", -10, "_");
        q1.addAnswer(a5);
        a6 = new Answer("Я хотел постирать носки...", -2, "_");
        q1.addAnswer(a6);
        a4 = new Answer("Они там снимают видео под бренный рок, я же сторонник неблэк-металла...", 3, "_");
        q1.addAnswer(a4);
        q2 = new Question("Привет");
        a1 = new Answer("Привет", 1, "");
        q2.addAnswer(a1);
        timur.addTbQuestion(q1);
        timur.addNotTbQuestion(q2);

        game.addCreep(lena);
        game.addCreep(sasha);
        game.addCreep(timur);

        List<Event> eventList = game.getEvents();
        Event eventBreakfastStarts = new EatEvent("Зaвтрак", 0, Event.EventType.STARTED);
        eventList.add(eventBreakfastStarts);
        Event eventTeambuildingStarts1 = new TeambuildingEvent("Тимбилдинг", 12, Event.EventType.STARTED);
        eventList.add(eventTeambuildingStarts1);
        Event eventBreakfastFinished = new EatEvent("Зaвтрак", 24, Event.EventType.FINISHED);
        eventList.add(eventBreakfastFinished);
        Event eventMessageRecieved1 = new PhoneMessageEvent("Телефон", 36, Event.EventType.STARTED, "Лена: собираемся в 108, будет организационное собрание");
        eventList.add(eventMessageRecieved1);
        Event eventTeambuildingStartsFinished1 = new TeambuildingEvent("Тимбилдинг", 60, Event.EventType.FINISHED);
        eventList.add(eventTeambuildingStartsFinished1);
        Event eventLunchStarts = new EatEvent("Обед", 72, Event.EventType.STARTED);
        eventList.add(eventLunchStarts);
        Event eventMessageRecieved2 = new PhoneMessageEvent("Телефон", 84, Event.EventType.STARTED, "Лена: собираемся в читальном зале, будет организационное собрание");
        eventList.add(eventMessageRecieved2);
        Event eventTeambuildingStarts2 = new TeambuildingEvent("Тимбилдинг", 84, Event.EventType.STARTED);
        eventList.add(eventTeambuildingStarts2);
        Event eventLunchFinished = new EatEvent("Обед", 96, Event.EventType.FINISHED);
        eventList.add(eventLunchFinished);
        Event eventDinnerStarts = new EatEvent("Ужин", 132, Event.EventType.STARTED);
        eventList.add(eventDinnerStarts);
        Event eventTeambuildingStartsFinished2 = new TeambuildingEvent("Тимбилдинг", 144, Event.EventType.FINISHED);
        eventList.add(eventTeambuildingStartsFinished2);
        Event eventMessageRecieved3 = new PhoneMessageEvent("Телефон", 144, Event.EventType.STARTED, "Лена: собираемся в 211, будет организационное собрание");
        eventList.add(eventMessageRecieved3);
        Event eventDinnerFinished = new EatEvent("Ужин", 156, Event.EventType.FINISHED);
        eventList.add(eventDinnerFinished);
        Event eventMessageRecieved4 = new PhoneMessageEvent("Телефон", 170, Event.EventType.STARTED, "Лена: собираемся в 310, будет организационное собрание");
        eventList.add(eventMessageRecieved4);

        game.setEvents(eventList);

        holeCampus.pushAvailableRoom(room);
        int waterRoom = (new Random()).nextInt(6) + 400;
        for (int i = 400; i <= 410; i++)
        {
            if (i != 406)
            {
                Room r = new Room("Номер " + i, "Это чья-то комната, она почему-то была открыта", "Это чья-то комната", true, true);
                r.pushAvailableRoom(holeCampus);
                if (i == waterRoom) {
                    r.pushItem(water);
                }
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
                if (i == 410)
                {
                    r.pushItem(new Charger(phone));
                }
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
