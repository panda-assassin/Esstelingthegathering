package com.example.androidcode.Achievement;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidcode.R;

import java.util.ArrayList;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ImageViewHolder> {

    private ArrayList<Achievement> dataset;

    public AchievementAdapter(Context context, ArrayList dataset) {
        this.dataset = dataset;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_achievements, viewGroup, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);

        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder viewHolder, int i) {
        final Achievement ac = dataset.get(i);
        viewHolder.name.setText(ac.getAchievementName());
        viewHolder.progress.setText(ac.getProgress());
        viewHolder.image.setImageResource(ac.getImageURL());
        System.out.println(ac.getProgressbarInt());
        viewHolder.bar.setProgress(ac.getProgressbarInt());
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name;
        ImageView image;
        TextView progress;
        ProgressBar bar;


        public ImageViewHolder(View itemview) {
            super(itemview);

            cardView = itemview.findViewById(R.id.achievementCardView);
            image = itemview.findViewById(R.id.achievementImage);
            name = itemview.findViewById(R.id.achievementName);
            progress = itemview.findViewById(R.id.progress);
            bar = itemview.findViewById(R.id.achievementProgressBar);

        }
    }
}
