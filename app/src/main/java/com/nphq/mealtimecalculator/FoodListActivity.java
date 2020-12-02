package com.nphq.mealtimecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.nphq.mealtimecalculator.ui.home.FoodListAdapter;
import com.nphq.mealtimecalculator.ui.home.HomeFragment;

public class FoodListActivity extends AppCompatActivity {


    FoodListAdapter foodListAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        TextView breakfast = findViewById(R.id.totalInsulinBreakfast);
        TextView lunch = findViewById(R.id.totalInsulinLunch);
        TextView dinner = findViewById(R.id.totalInsulinDinner);

        String string_breakfast = String.format("%.2f", HomeFragment.breakFastInsulin);
        String string_lunch = String.format("%.2f", HomeFragment.LunchInsulin);
        String string_dinner = String.format("%.2f", HomeFragment.DinnerInsulin);

        breakfast.setText(string_breakfast);
        lunch.setText(string_lunch);
        dinner.setText(string_dinner);

        recyclerView = findViewById(R.id.foodListRecyclerView);
        foodListAdapter = new FoodListAdapter(this, HomeFragment.meal,HomeFragment.name,HomeFragment.portion,HomeFragment.insulin);
        recyclerView.setAdapter(foodListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}