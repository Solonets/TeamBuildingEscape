package com.room406.dialog;

import com.room406.Humans.Creep;
import com.room406.Humans.Player;
import com.room406.inventory.InventoryItem;

import java.util.List;
import java.util.Random;

/**
 * Created by ������ on 02.08.2015.
 */
public class Dialog {
    private int lastAnswer;
    public boolean dialog(Creep creep, Player player) {
        Random random = new Random();
        List<Question> questions = creep.getQuestions();
        Question question = questions.get(random.nextInt(questions.size()));
        System.out.println(question);
        List<Answer> answers = question.getAnswers();
        Answer answer = null;
        int inventoryNeeded = 1;
        int outcome = 0;
        int playerInfluence = 0;
        List<InventoryItem> usefulThings;
        while (inventoryNeeded > 0) {
            answer = answers.get(lastAnswer++);
            usefulThings = answer.getUsefulThings();
                inventoryNeeded = answer.getUsefulThings().size();
            for (int i = 0; i < usefulThings.size(); i++) {
                if (player.getInventory().contains(usefulThings.get(i))) {
                    inventoryNeeded--;
                }
            }
        }
        System.out.println(answer);
        playerInfluence = player.getInfluence() + answer.getInfluence();
        outcome = random.nextInt(playerInfluence + creep.getInfluence());
        if(outcome < playerInfluence) {
            return true;
        }
        else {
            return false;
        }
    }
}
