package com.example.androidcode.Game;

import com.example.androidcode.Inventory.CardType;

import java.io.Serializable;

public class GameObject implements Serializable {

    private String image;
    private Enum<CardType> cardTypeEnum;

    public GameObject(String image, Enum<CardType> cardTypeEnum) {
        this.image = image;
        this.cardTypeEnum = cardTypeEnum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Enum<CardType> getCardTypeEnum() {
        return cardTypeEnum;
    }

    public void setCardTypeEnum(Enum<CardType> cardTypeEnum) {
        this.cardTypeEnum = cardTypeEnum;
    }
}
