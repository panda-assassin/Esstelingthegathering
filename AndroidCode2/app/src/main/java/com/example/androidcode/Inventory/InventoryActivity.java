package com.example.androidcode.Inventory;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcode.BlankActivity;
import com.example.androidcode.DataBase.CardReader;
import com.example.androidcode.QueueList.CheckInActivity;
import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class InventoryActivity extends AppCompatActivity implements inventoryListener{

    private RecyclerView recyclerView;
    private static InventoryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<Card> dataSet = new ArrayList<>();
    private static ArrayList<Card> lockedDataSet = new ArrayList<>();

    private ImageView selectedCardImage;
    private TextView selectedCardName;
    private TextView selectedCardDescription;
    private ImageView selectedCardType;

    private AssetManager assetManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        assetManager = getAssets();

        InputStream inputStream = null;


        try {
            inputStream = assetManager.open("jsonfiles/Cards.json");
            String json = null;
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");

            this.dataSet = CardReader.readCardsFromJson(new JSONObject(json));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            inputStream = assetManager.open("jsonfiles/LockedCards.json");
            String json = null;
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");

            this.lockedDataSet = CardReader.readCardsFromJson(new JSONObject(json));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        this.dataSet = new ArrayList<>();
//        dataSet.add(new Card("Test", CardType.ROCK, "Hoi", "test"));
//        dataSet.add(new Card("Test2", CardType.ROCK, "Hoi", "test"));
//        dataSet.add(new Card("Test3", CardType.ROCK, "Hoi", "test"));



        selectedCardType = findViewById(R.id.selectedCardTypeImage);
        selectedCardName = findViewById(R.id.selectedCardName);
        selectedCardDescription = findViewById(R.id.selectedCardDescription);
        selectedCardImage = findViewById(R.id.selectedCardImage);


        recyclerView = findViewById(R.id.inventory_recycler);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new InventoryAdapter(dataSet, this);
        recyclerView.setAdapter(adapter);

        ImageButton back = findViewById(R.id.backbttn);
        ImageButton option = findViewById(R.id.optionsbttn);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InventoryActivity.this, HomeScreenActivity.class));
            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InventoryActivity.this, BlankActivity.class));
            }
        });


    }

    @Override
    public void onCardSelected(Card card) {
        selectedCardImage.setImageResource(R.drawable.ic_launcher_background); //Get picture
        selectedCardName.setText(card.getName());
        selectedCardDescription.setText(card.getDescription());
        selectedCardType.setImageResource(R.drawable.ic_launcher_background);
    }

    public static void achievementCompleted(){
        int chance = 100;

        Random random = new Random();
        int n = random.nextInt(100)+1;

        if(n<chance) {
            if (lockedDataSet.size() > 0) {
                dataSet.add(lockedDataSet.get(0));
                lockedDataSet.remove(0);
                System.out.println("added card");
                adapter.notifyDataSetChanged();

            }
        }


    }


}
