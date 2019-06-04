package com.example.androidcode.Inventory;

import android.media.Image;

public class Card {

    private String name;
    private CardType type;
    private String description;
    // Todo: private Image image;

    public String getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    /*Todo:
    public String getImage() {
        return image;
    }
     */

    public Card(String name, CardType type, String description /* BufferedImage image*/){
        this.name = name;
        this.type = type;
        this.description = description;
        //this.image = image;
    }


}
