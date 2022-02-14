package com.example.ssresilience;

import android.app.Application;

public class DataSite extends Application {

    private String goal;
    private int gadpoints, reflectpoints, exercisepoints, noiselevel;
    private int check = 0;
    private int checkifmeasured = 0;

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

    public int getReflectPoints(){
        return reflectpoints;
    }

    public void setReflectPoints(int reflectpoints){
        this.reflectpoints = reflectpoints;
    }

    public int getExercisePoints(){
        return exercisepoints;
    }

    public void setExercisePoints(int exercisepoints){
        this.exercisepoints = exercisepoints;
    }

    public int getCheck(){
        return check;
    }

    public void setCheck(int check){
        this.check = check;
    }

    public int getCheckIfMeasured(){
        return checkifmeasured;
    }

    public void setCheckIfMeasured(int checkifmeasured){
        this.checkifmeasured = checkifmeasured;
    }

    public int getNoiseLevel(){
        return noiselevel;
    }

    public void setNoiseLevel(int noiselevel){
        this.noiselevel = noiselevel;
    }
}
