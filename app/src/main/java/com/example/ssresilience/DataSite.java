package com.example.ssresilience;

import android.app.Application;

public class DataSite extends Application {

    private String goal;
    private int gadpoints;

    public String getGoal(){
        return goal;
    }

    public void setGoal(String goal){
        this.goal = goal;
    }

    public int getGadPoints(){
        return gadpoints;
    }

    public void setGadPoints(int gadpoints){
        this.gadpoints = gadpoints;
    }

}
