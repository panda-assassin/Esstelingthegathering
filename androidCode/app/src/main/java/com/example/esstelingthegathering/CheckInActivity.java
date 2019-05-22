package com.example.esstelingthegathering;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class CheckInActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QueueAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        recyclerView = findViewById(R.id.queue_recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QueueAdapter();
        recyclerView.setAdapter(adapter);


        Button back = findViewById(R.id.backbttn);
        Button option = findViewById(R.id.optionsbttn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckInActivity.this, MainActivity.class));
            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckInActivity.this, TestActivity.class));
            }
        });


    }
}
