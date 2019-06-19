package com.example.androidcode.QueueList;

import java.io.Serializable;

public class Attraction implements Serializable {

    private String name;
    private String queue;
    private int image;


    public Attraction( int image, String name, String queue) {
        this.name = name;
        this.queue = queue;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getQueue() {
        return queue;
    }

    public int getImage() {
        return image;
    }
}
