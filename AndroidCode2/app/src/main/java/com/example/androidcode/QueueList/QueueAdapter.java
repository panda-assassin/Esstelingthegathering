package com.example.androidcode.QueueList;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidcode.R;

public class QueueAdapter extends RecyclerView.Adapter<QueueAdapter.ImageViewHolder> {

    public QueueAdapter() {

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_queue, viewGroup, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder viewHolder, int i) {

        viewHolder.name.setText("koppeling naar naam");
        viewHolder.exp.setText("koppeling naar exp");
        viewHolder.image.setImageResource(R.drawable.ic_launcher_background);

    }

    @Override
    public int getItemCount() {
        return 5;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name;
        TextView exp;
        ImageView  image;


        public ImageViewHolder(View itemview) {
            super(itemview);

            image = itemview.findViewById(R.id.attractionImage);
            cardView = itemview.findViewById(R.id.cardview);
            name = itemview.findViewById(R.id.attractionname);
            exp = itemview.findViewById(R.id.queueExp);
        }
    }
}
