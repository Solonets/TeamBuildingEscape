package com.room406;

import java.util.List;
import java.util.Random;

/**
 * Created by Наська on 02.08.2015.
 */
public class Dialog {
    public void dialog(Creep creep, Player player) {
        Random random = new Random();
        List<Question> questions = creep.getQuestions();
        Question question = questions.get(random.nextInt(questions.size()));
        List<Answer> answers = question.getAnswers();
        Answer answer = answers.get(answers.size());

    }
}
