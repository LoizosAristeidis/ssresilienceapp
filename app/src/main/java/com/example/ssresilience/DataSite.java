package com.example.ssresilience;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.Calendar;

public class DataSite extends Application {

    private String goal;
    private int gadpoints, reflectpoints, exercisepoints, noiselevel;
    private int hour = 0;
    private int minute = 0;
    private int check = 0;
    private int checkifmeasured = 0;
    private int gadcheck = 0;

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

    public int getGadCheck(){
        return gadcheck;
    }

    public void setGadCheck(int gadcheck){
        this.gadcheck = gadcheck;
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

    public int getHour(){
        return hour;
    }

    public void setHour(int hour){ this.hour = hour; }

    public int getMinute(){
        return minute;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public void reflectNotification() {
        createNotificationChannel();

        int hour = this.getHour();
        int minute = this.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        Toast.makeText(this, "Reflect Reminder Set!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DataSite.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(DataSite.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "ReflectNotification";
            String description = "Time to Reflect on today's Goal!";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("reflectnotification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
