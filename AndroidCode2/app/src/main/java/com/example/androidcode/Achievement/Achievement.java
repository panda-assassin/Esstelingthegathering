package com.example.androidcode.Achievement;

import java.io.Serializable;

public class Achievement implements Serializable {

    private int imageURL;
    private String achievementName;
    private String progress;
    private int progressInt;
    private int goalInt;
    boolean completed;

    public Achievement(int imageURL, String achievementName, int goalInt) {
        this.imageURL = imageURL;
        this.achievementName = achievementName;
        this.goalInt = goalInt;
        this.progressInt = 0;
        this.progress = new String(progressInt+"/"+goalInt);
        this.completed = false;
    }

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getProgress() {
        return progress;
    }

    private void updateProgress(){this.progress = new String(progressInt+"/"+goalInt);}

    public int getProgressInt() {
        return progressInt;
    }

    public void addProgress(int add){
        if(!completed) {
            setProgressInt(getProgressInt() + add);
        }

    }

    public void setProgressInt(int progressInt) {
        if(!completed) {
            this.progressInt = progressInt;
            if (this.progressInt >= goalInt) {
                completed = true;
                this.progressInt = this.getGoalInt();
            }
            updateProgress();
        }
    }

    public int getGoalInt() {
        return goalInt;
    }

    public void setGoalInt(int goalInt) {
        this.goalInt = goalInt;
        updateProgress();
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "imageURL=" + imageURL +
                ", achievementName='" + achievementName + '\'' +
                ", progress='" + progress + '\'' +
                ", progressInt=" + progressInt +
                ", goalInt=" + goalInt +
                '}';
    }
}
