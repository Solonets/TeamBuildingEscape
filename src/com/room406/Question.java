package com.room406;

import java.util.List;

/**
 * Created by ������ on 02.08.2015.
 */
public class Question {
    private String Message;
    private List<Answer> answers;

    public Question(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }
}
