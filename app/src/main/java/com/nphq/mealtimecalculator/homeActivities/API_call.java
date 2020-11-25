package com.nphq.mealtimecalculator.homeActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nphq.mealtimecalculator.R;

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class API_call extends AppCompatActivity {

    public static String food="NULL";
    Context c = this;
    EditText foodEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_p_i_call);

        foodEdit = findViewById(R.id.foodName);

        Button search = findViewById(R.id.searchFood);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food = foodEdit.getEditableText().toString();

                startActivity(new Intent(API_call.this,NutritionList.class));
            }
        });

    }
}