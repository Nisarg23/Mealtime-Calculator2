package com.nphq.mealtimecalculator.homeActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nphq.mealtimecalculator.R;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Button[] buttons = new Button[6];

        buttons[0] = findViewById(R.id.addBreakfast);
        buttons[1] = findViewById(R.id.addLunch);
        buttons[2] = findViewById(R.id.addDinner);
        buttons[3]  = findViewById(R.id.addMorningSnack);
        buttons[4] = findViewById(R.id.addAfternoonSnack);
        buttons[5] = findViewById(R.id.addEveningSnack);

        for (int i=0; i<6;i++){
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(FoodActivity.this,API_call.class));
                }
            });
        }



    }
}