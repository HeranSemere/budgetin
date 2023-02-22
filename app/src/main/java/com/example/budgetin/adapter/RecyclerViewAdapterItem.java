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

import com.example.budgetin.entity.Item;

import java.util.ArrayList;

public class RecyclerViewAdapterItem extends RecyclerView.Adapter<RecyclerViewAdapterItem.RecyclerViewHolder>{

    private ArrayList<Item> itemDataArrayList;
    private Context mcontext;

    public RecyclerViewAdapterItem(ArrayList<Item> itemDataArrayList, Context mcontext) {
        this.itemDataArrayList = itemDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterItem.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        return new RecyclerViewAdapterItem.RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterItem.RecyclerViewHolder holder, int position) {
        Item recyclerData = itemDataArrayList.get(position);
        holder.amount.setText(Float.toString(recyclerData.getAmount()));
        holder.note.setText(recyclerData.getNote());
    }

    @Override
    public int getItemCount() {
        return itemDataArrayList.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView note;
        private TextView amount;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.note);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
