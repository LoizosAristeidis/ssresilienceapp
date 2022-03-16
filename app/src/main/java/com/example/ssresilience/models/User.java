package com.example.ssresilience.models;

public class User {

    public String fullName, email, goal;
    public Long progress;

    public User() {
    }

    public User(String fullName, String email, String goal, Long progress) {
        this.fullName = fullName;
        this.email = email;
        this.goal = goal;
        this.progress = progress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoals(String goal) {
        this.goal = goal;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }
}