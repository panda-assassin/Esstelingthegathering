package com.example.androidcode.Inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.androidcode.R;

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


    }
}
