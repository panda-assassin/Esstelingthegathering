package com.example.androidcode.Inventory;

import android.media.Image;
import android.os.Parcelable;

import java.io.Serializable;

public class Card implements Serializable {

    private String name;
    private CardType type;
    private String description;
    private String  testImage;
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

    public Card(String name, CardType type, String description , String testImage/* BufferedImage image*/){
        this.name = name;
        this.type = type;
        this.description = description;
        this.testImage = testImage;
        //this.image = image;
    }


}
