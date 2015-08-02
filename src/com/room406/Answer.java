package com.room406;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ������ on 02.08.2015.
 */
public class Answer implements Serializable {
    private String message;
    private InventoryItem dependency;
    private int result;
    private String answer;

    public Answer(String message, int result, String answer) {
        this.message = message;
        this.result = result;
        this.answer = answer;
    }

    public InventoryItem getDependency() {
        return dependency;
    }

    public void setDependency(InventoryItem dependency) {
        this.dependency = dependency;
    }

    public String getMessage() {
        return message;
    }

    public int getInfluence() {
        return result;
    }

    public String getAnswer() {
        return answer;
    }

    public List getUsefulThings() {
        List<InventoryItem> res = new ArrayList<>();
        res.add(dependency);
        return res;
    }
}
