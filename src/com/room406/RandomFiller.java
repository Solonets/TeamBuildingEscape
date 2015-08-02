package com.room406;

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
        String[] items = {"ручка", "блокнот", "футболка", "толстовка", "чеснок"};
        String[] accusative = {"ручку", "блокнот", "футболку", "толстовку", "чеснок"};
        int[] influences = {1, 2, 5, 10, 25};
        int i = 0;
        while(random.nextInt(3) <= 1 && i < 4) {i++;}
        return new InventoryItem(items[i], accusative[i], influences[i], locations[random.nextInt(locations.length)]);
    }
    static void fillRandomInventory(Room room) {
        while (random.nextInt(2) == 1)
        {
            room.pushItem(getRandomInventory());
        }
    }
}
