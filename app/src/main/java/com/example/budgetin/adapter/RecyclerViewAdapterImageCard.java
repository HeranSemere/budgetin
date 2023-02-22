package com.example.budgetin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetin.R;
import com.example.budgetin.entity.ImageRecyclerData;

import java.util.ArrayList;

public class RecyclerViewAdapterImageCard extends RecyclerView.Adapter<RecyclerViewAdapterImageCard.RecyclerViewHolder> {

    private ArrayList<ImageRecyclerData> imageDataArrayList;
    private Context mcontext;

    public RecyclerViewAdapterImageCard(ArrayList<ImageRecyclerData> recyclerDataArrayList, Context mcontext) {
        this.imageDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        ImageRecyclerData recyclerData = imageDataArrayList.get(position);
        holder.categoryImage.setImageResource(recyclerData.getImgid());
        holder.categoryImageLabel.setText(recyclerData.getLabel());
        holder.categoryImageAmount.setText(recyclerData.getAmount());


    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return imageDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryImageLabel;
        private TextView categoryImageAmount;
        private ImageView categoryImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImageLabel = itemView.findViewById(R.id.label);
            categoryImage = itemView.findViewById(R.id.roundedImageView);
            categoryImageAmount = itemView.findViewById(R.id.amount);
        }
    }
}

