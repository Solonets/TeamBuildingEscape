package com.room406.dialog;

import com.room406.humans.Creep;
import com.room406.humans.Player;
import com.room406.inventory.InventoryItem;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ������ on 02.08.2015.
 */
public class Dialog {
    /*private int lastAnswer;
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
    }*/
    private Player player;
    private Creep creep;
    private Random random;

    public Dialog(Creep creep, Player player) {
        this.player = player;
        this.creep = creep;
        this.random = new Random();
    }

    public boolean dialog() {
        System.out.println("Твоя влиятельность: " + player.getInfluence());
        System.out.println(creep + " находится в этом помещении (влиятельность " + creep.getInfluence() + ")");
        System.out.println("Увидев тебя, он(а) быстро подходит к тебе");
        List<Question> questions = creep.getQuestions();
        Question question = questions.get(random.nextInt(questions.size()));
        System.out.println(creep + ": " + question);
        List<Answer> answers = new ArrayList<>();
        int i = 0;
        for (Answer ans : question.getAnswers()) {
            if (ans.getDependency() == null || player.hasItem(ans.getDependency())) {
                answers.add(ans);
                i++;
                System.out.println(i + ". " + ans);
            }
        }
        Scanner scanner = new Scanner(System.in);
        int answerNumber = scanner.nextInt() - 1;
        int battleInfluence = 0;
        int creepInfluence = creep.getInfluence();
        int playerInfluence = player.getInfluence() + battleInfluence;
        boolean result = true;
        if (answerNumber >= answers.size() || answerNumber < 0) {
            System.out.println("Ты промолчал. " + creep + " посмотрел(а) на тебя с высока.");
        } else {
            System.out.println(player + ": " + answers.get(answerNumber));
            battleInfluence += answers.get(answerNumber).getInfluence();
            if (battleInfluence > 0) {
                player.addInfluence(battleInfluence);
            }
            if (answers.get(answerNumber).getDependency() != null) {
                result = true;
            } else if (!answers.get(answerNumber).getAnswer().isEmpty()) {
                int rnd = random.nextInt(playerInfluence + creepInfluence);
                result = (playerInfluence + creepInfluence > 0
                        && rnd < playerInfluence);
                if (!result) {
                    System.out.println(creep + ": " + answers.get(answerNumber).getAnswer());
                }
            }
        }
        return result;
    }
}
