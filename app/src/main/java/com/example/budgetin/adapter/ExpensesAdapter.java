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
import com.example.budgetin.entity.Item;

import java.util.ArrayList;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpenseRecyclerViewHolder> {

    private ArrayList<Item> itemsArrayList;
    private Context mcontext;

    public ExpensesAdapter(ArrayList<Item> itemsArrayList, Context mcontext) {
        this.itemsArrayList = itemsArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ExpensesAdapter.ExpenseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        return new ExpensesAdapter.ExpenseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ExpenseRecyclerViewHolder holder, int position) {
        Item recyclerData = itemsArrayList.get(position);
        holder.price.setText(Float.toString(recyclerData.getAmount())+" birr");
        holder.note.setText(recyclerData.getNote());
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class ExpenseRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView note;
        private TextView price;


        public ExpenseRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.note);
            price = itemView.findViewById(R.id.price);

        }
    }
}