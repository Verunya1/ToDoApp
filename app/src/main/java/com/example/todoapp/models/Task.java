package com.example.todoapp.models;

public class Task {
    private String task;
    private boolean done;

    public Task() {}

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
