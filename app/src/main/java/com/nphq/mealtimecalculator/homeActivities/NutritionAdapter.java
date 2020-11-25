package com.nphq.mealtimecalculator.homeActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nphq.mealtimecalculator.R;

import java.util.ArrayList;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.NutrtionViewHolder> {
    Context c;
    ArrayList<String> s1;
    ArrayList<String> s2;
    ArrayList<String> s3;

    public NutritionAdapter(Context c,ArrayList<String> s1, ArrayList<String> s2, ArrayList<String> s3){
        this.c = c;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;

    }

    @NonNull
    @Override
    public NutrtionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View view = layoutInflater.inflate(R.layout.item_nutrition,parent,false);
        return new NutrtionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NutrtionViewHolder holder, int position) {
        holder.name.setText(s1.get(position));
        holder.amount.setText(s2.get(position));
        holder.unit.setText(s3.get(position));

    }

    @Override
    public int getItemCount() {
        return s1.size();
    }

    public class NutrtionViewHolder extends RecyclerView.ViewHolder{
        TextView name, amount, unit;

        public NutrtionViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            unit = itemView.findViewById(R.id.unit);
        }
    }
}
