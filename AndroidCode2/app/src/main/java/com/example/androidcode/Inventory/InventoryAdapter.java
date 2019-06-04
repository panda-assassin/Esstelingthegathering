package com.example.androidcode.Inventory;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcode.QueueList.QueueAdapter;
import com.example.androidcode.R;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ImageViewHolder> {

    public InventoryAdapter() {

    }

    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_inventory,parent,false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {

        viewHolder.cardImage.setImageResource(R.drawable.ic_launcher_background);
        viewHolder.cardName.setText("Naam");


    }

    @Override
    public int getItemCount() {
        return 50;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView cardImage;
        TextView cardName;


        public ImageViewHolder(View itemview) {
            super (itemview);

            cardImage = itemview.findViewById(R.id.card_image);
            cardName = itemview.findViewById(R.id.card_name);

        }
    }
}
