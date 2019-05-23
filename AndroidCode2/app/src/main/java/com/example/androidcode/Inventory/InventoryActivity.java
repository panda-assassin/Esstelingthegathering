package com.example.androidcode.Inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.androidcode.BlankActivity;
import com.example.androidcode.QueueList.CheckInActivity;
import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;

public class InventoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InventoryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        recyclerView = findViewById(R.id.inventory_recycler);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InventoryAdapter();
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
}
