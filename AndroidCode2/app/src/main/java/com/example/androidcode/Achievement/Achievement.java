package com.example.androidcode.Achievement;

import java.io.Serializable;

public class Achievement implements Serializable {

    private int imageURL;
    private String achievementName;
    private String progress;

    public Achievement(int imageURL, String achievementName, String progress) {
        this.imageURL = imageURL;
        this.achievementName = achievementName;
        this.progress = progress;
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

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "imageURL='" + imageURL + '\'' +
                ", achievementName='" + achievementName + '\'' +
                ", progress=" + progress +
                '}';
    }
}
