package com.example.androidcode.Inventory;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcode.BlankActivity;
import com.example.androidcode.QueueList.CheckInActivity;
import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity implements inventoryListener{

    private RecyclerView recyclerView;
    private InventoryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Card> dataSet;

    private ImageView selectedCardImage;
    private TextView selectedCardName;
    private TextView selectedCardDescription;
    private ImageView selectedCardType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        this.dataSet = new ArrayList<>();
        dataSet.add(new Card("Test", CardType.ROCK, "Hoi", "test"));
        dataSet.add(new Card("Test2", CardType.ROCK, "Hoi", "test"));
        dataSet.add(new Card("Test3", CardType.ROCK, "Hoi", "test"));



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

        Button back = findViewById(R.id.backbttn);
        Button option = findViewById(R.id.optionsbttn);




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
}
