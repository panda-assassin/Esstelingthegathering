package com.example.androidcode;

public class Attraction {

    private int name;
    private int queue;
    private int image;


    public Attraction(int name, int queue, int image) {
        this.name = name;
        this.queue = queue;
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public int getQueue() {
        return queue;
    }

    public int getImage() {
        return image;
    }
}
