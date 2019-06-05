package com.example.androidcode.DataBase;

import com.example.androidcode.Inventory.Card;
import com.example.androidcode.Inventory.CardType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CardReader {


    /**
     *  Reads a Json cardset and returns a arraylist of all cards in the set.
     * @param file
     * @return
     */
    public static ArrayList<Card> readCardsFromJson(JSONObject file){
        try {
            if(file.getString("Type").equals("CardSet")){

                System.out.println("Loading JSON: " + file.getString("Title") + ".");

                ArrayList<Card> cards = new ArrayList<>();
                JSONArray cardArray = file.getJSONArray("cards");

                for(int i=0; i<cardArray.length(); i++){
                    JSONObject card = cardArray.getJSONObject(i);
                    /* Todo: Not tested!
                    Image img = null;
                    try {
                        img = ImageIO.read(new File(card.getString("ImagePath");));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    */
                    cards.add(new Card(
                            card.getString("Name"),
                            CardType.valueOf(card.getString("CardType")),
                            card.getString("Description")/* Todo: ,
                            img */));


                }

                System.out.println("Finished");
                return cards;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Unable to load cardset!!!");
        return null;
    }

}
