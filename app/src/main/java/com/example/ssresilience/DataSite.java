package com.example.ssresilience;

import android.app.Application;

public class DataSite extends Application {

    private String goal;

    public String getGoal(){
        return goal;
    }

    public void setGoal(String goal){
        this.goal = goal;
    }

}
