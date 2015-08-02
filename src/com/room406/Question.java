package com.room406;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ������ on 02.08.2015.
 */
public class Question {
    private String message;
    private List<Answer> answers;

    public Question(String message) {
        this.message = message;
        this.answers = new ArrayList<Answer>();
    }

    public String getMessage() {
        return message;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public String toString() {
        return String.format("- %s\n", message);
    }
}
