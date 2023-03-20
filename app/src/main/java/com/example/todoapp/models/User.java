package com.example.todoapp.models;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String UID;
    private String email;
    private String imageUrl;
    private List<Task> tasks;

    public User() {}

    public User(String firstName, String lastName, String UID, String email, String imageUrl, List<Task> tasks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.UID = UID;
        this.email = email;
        this.imageUrl = imageUrl;
        this.tasks = tasks;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
