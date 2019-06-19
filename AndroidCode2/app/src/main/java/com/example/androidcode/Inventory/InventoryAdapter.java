package com.example.androidcode.Inventory;

import android.content.Intent;
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

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ImageViewHolder> {

    private ArrayList<Card> cards;
    private inventoryListener listener;

    public InventoryAdapter(ArrayList<Card> cards, inventoryListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_inventory,parent,false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;

    }

    @Override
    public void onBindViewHolder(final ImageViewHolder viewHolder, int i) {

//        viewHolder.cardImage.setImageResource(cards.get(i).getImage);
        viewHolder.cardImage.setImageResource(R.drawable.ic_launcher_background);
        viewHolder.cardName.setText(cards.get(i).getName());
        viewHolder.card = cards.get(i);

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView cardImage;
        TextView cardName;
        Card card;

        public ImageViewHolder(final View itemview) {
            super (itemview);

            cardImage = itemview.findViewById(R.id.card_image);
            cardName = itemview.findViewById(R.id.card_name);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardImage.setPadding(1,1,1,1);
                    listener.onCardSelected(card);

                }
            });
        }

    }
}
