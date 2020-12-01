package com.nphq.mealtimecalculator.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nphq.mealtimecalculator.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.NutrtionViewHolder> {
    Context c;
    ArrayList<String> s1;
    ArrayList<String> s2;
    ArrayList<String> s3;
    ArrayList<String> s4;

    public FoodListAdapter(Context c,ArrayList<String> s1, ArrayList<String> s2, ArrayList<String> s3,ArrayList<String> s4){
        this.c = c;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;

    }

    @NonNull
    @Override
    public NutrtionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View view = layoutInflater.inflate(R.layout.item_food,parent,false);
        return new NutrtionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutrtionViewHolder holder, int position) {
        holder.meal.setText(s1.get(position));
        holder.name.setText(s2.get(position));
        holder.portion.setText(s3.get(position));
        holder.insulin.setText(s4.get(position));


    }

    @Override
    public int getItemCount() {
        return s1.size();
    }

    public class NutrtionViewHolder extends RecyclerView.ViewHolder{
        TextView meal,name,portion,insulin;

        public NutrtionViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.food_name);
            portion = itemView.findViewById(R.id.food_portion);
            insulin = itemView.findViewById(R.id.insulin_needed);
            meal = itemView.findViewById(R.id.type_of_meal);
        }
    }
}
