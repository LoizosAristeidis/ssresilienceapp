package com.example.ssresilience.models;

public class User {

    public String fullName, email;
    public Long progress;

    public User() {

    }

    public User(String fullName, String email, Long progress) {
        this.fullName = fullName;
        this.email = email;
        this.progress = progress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

//    public String getAge() {
//        return age;
//    }
//
//    public void setAge(String age) {
//        this.age = age;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }
}