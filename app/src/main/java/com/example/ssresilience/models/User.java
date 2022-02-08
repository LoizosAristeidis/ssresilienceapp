package com.example.ssresilience.models;

public class User {

    public String fullName, age, email;
    public Long progress;
    public User() {

    }

    public User(String fullName, String age, String email) {
        this.fullName = fullName;
        this.progress = progress;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
