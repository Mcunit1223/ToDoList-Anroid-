package com.example.edwardcrispen.todolist;

/**
 * Created by adena on 10/21/2016.
 */

public class Task {
    private String name;
    private boolean complete;

    public Task(){}

    public Task(String name, boolean complete) {
        this.name = name;
        this.complete = complete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return complete;
    }
}
