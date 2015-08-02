package com.room406;

import java.util.List;
import java.util.Random;

/**
 * Created by Наська on 02.08.2015.
 */
public class Dialog {
    private int lastAnswer;
    public boolean dialog(Creep creep, Player player) {
        Random random = new Random();
        List<Question> questions = creep.getQuestions();
        Question question = questions.get(random.nextInt(questions.size()));
        System.out.println(question);
        List<Answer> answers = question.getAnswers();
        Answer answer = answers.get(lastAnswer++);
        int inventoryNeeded = 0;
        int outcome = 0;
        int playerInfluence = 0;
        List<InventoryItem> usefulThings = answer.getUsefulThings();
        while (inventoryNeeded == usefulThings.size()) {
            for (int i = 0; i < usefulThings.size(); i++) {
                if (player.getInventory().contains(usefulThings.get(i))) {
                    inventoryNeeded++;
                }
            }
            answer = answers.get(lastAnswer++);
        }
        System.out.println(answer);
        playerInfluence = player.getInfluence() + answer.getResult();
        outcome = random.nextInt(playerInfluence + creep.getInfluence());
        if(outcome < playerInfluence) {
            return true;
        }
        else {
            return false;
        }
    }
}
