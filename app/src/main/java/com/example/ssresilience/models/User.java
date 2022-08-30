package com.example.ssresilience.models;

public class User {

    public String fullName, email, goal, measureme, reflect, createD, updateD;
    public Long progress;

    public User() {
    }

    public User(String fullName, String email, String goal, Long progress, String measureme, String reflect, String createD, String updateD) {
        this.fullName = fullName;
        this.email = email;
        this.goal = goal;
        this.progress = progress;
        this.measureme = measureme;
        this.reflect = reflect;
        this.createD = createD;
        this.updateD = updateD;
    }
    
    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getGoal() { return goal; }

    public void setGoal(String goal) { this.goal = goal; }

    public Long getProgress() { return progress; }

    public void setProgress(Long progress) { this.progress = progress; }

    public String getMeasureme() { return measureme; }

    public void setMeasureme(String measureme) { this.measureme = measureme; }

    public String getReflect() { return reflect; }

    public void setReflect(String reflect) { this.reflect = reflect; }

    public String getCreateD() { return createD; }

    public void setCreateD(String createD) { this.createD = createD; }

    public String getUpdateD() { return updateD; }

    public void setUpdateD(String updateD) { this.updateD = updateD; }
}