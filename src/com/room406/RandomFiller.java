package com.room406;

import com.room406.inventory.InventoryItem;
import com.room406.rooms.Room;

import java.util.Random;

/**
 * Created by user on 02.08.2015.
 */
public class RandomFiller {
    static Random random = new Random();
    static InventoryItem getRandomInventory()
    {
        String[] locations = {
                "на столе лежит %s.",
                "в шкафу виднеется %s.",
                "%s лежит на подоконнике.",
                "%s валяется под кроватью.",
                "из под кровати торчит %s"
        };
        String[] items = {"ручка", "блокнот", "браслет", "футболка", "очки",  "Корман", "сумка для обуви", "толстовка", "чеснок",  "бинт"};
        String[] accusative = {"ручку", "блокнот", "браслет", "футболку", "очки",  "Кормана", "сумку для обуви", "толстовку", "чеснок",  "бинт"};
        String[] pickedMessages = {
                "Такая ручка есть почти у каждого, но в переднем кармане рубашки, она добавляет тебе важности",
                "Во время разговора, важно поглядывай в свой блокнот, пусть он и пустой",
                "Пожимая руку, приподними рукав, пускай все видят какой у тебя браслет",
                "Футболка Иннополиса поднимает твой авторитет у администрации",
                "Очки всегда добавляют важности в разговоре, поправляя их пальцем после каждой фразы, ты добавляешь весомости сказанному",
                "Умные парни всегда нравятся руководству университета, разве глупый парень будет носить с собой такую книгу?",
                "Положи свою сменку в сумку для обуви, ты не только сохранишь ее чистой, но и покажешь себя стильным парнем",
                "Зеленая толстовка Иннополиса - редкий атрибут, ты выделяешься из толпы",
                "Ты подобрал и съел чеснок, вряд ли кто-то захочет спорить с тобой",
                "Обмотав руку бинтом ты вызовешь сострадание у организации, а разве сострадание и власть не одно и то же?"
        };
        int[] influences = {1, 2, 3, 4, 5, 6, 7, 10, 25, 30};
        int i = 0;
        while(random.nextInt(3) <= 1 && i < items.length - 1) {i++;}
        return new InventoryItem(items[i], accusative[i], pickedMessages[i], influences[i], locations[random.nextInt(locations.length)]);
    }
    static void fillRandomInventory(Room room) {
        while (random.nextInt(2) == 1)
        {
            room.pushItem(getRandomInventory());
        }
    }
}
