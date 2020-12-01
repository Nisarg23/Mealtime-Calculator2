package com.nphq.mealtimecalculator.homeActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nphq.mealtimecalculator.R;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NutritionList extends AppCompatActivity {

    RecyclerView recyclerView;
    Context c = this;
    ArrayList<String> nutrition_name = new ArrayList<String>();
    ArrayList<String> nutrition_amount = new ArrayList<String>();
    ArrayList<String> nutrition_unit = new ArrayList<String>();

    String food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_list);

        Intent intent = getIntent();
        food = intent.getExtras().getString("food","null");

        System.out.println("food "+food);

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL urlForGetRequest = null;
                StringBuffer response = new StringBuffer();


                try {
                    urlForGetRequest = new URL("https://api.nal.usda.gov/fdc/v1/foods/list?api_key=kwAPxa4YJSYCvhOz6OZdJbOgZX1lse2FtpldfgG1&query="+food);
                    String readLine = null;
                    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conection.getInputStream()));

                    while ((readLine = in .readLine()) != null) {
                        response.append(readLine);
                    } in .close();

                } catch (MalformedURLException e) {
                    Toast.makeText(c,"Food Not Found",Toast.LENGTH_LONG).show();
                    finish();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(c,"Food Not Found",Toast.LENGTH_LONG).show();
                    finish();
                    e.printStackTrace();
                }


                org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
                org.json.simple.JSONArray arr = null;
                try {
                    arr = ( org.json.simple.JSONArray) parser.parse(response.toString());
                } catch (ParseException e) {
                    Toast.makeText(c,"There was an error in the app",Toast.LENGTH_LONG).show();
                    finish();
                    e.printStackTrace();
                }

                for (int i = 0; i < 1; i++) {
                    org.json.simple.JSONObject jsonobject = (org.json.simple.JSONObject) arr.get(i);

                    org.json.simple.JSONArray arr2 =(org.json.simple.JSONArray) jsonobject.get("foodNutrients");

                    for (int j = 0; j < arr2.size(); j++) {
                        org.json.simple.JSONObject nutrients = (org.json.simple.JSONObject) arr2.get(j);
                        nutrition_name.add(nutrients.get("name").toString());
                        nutrition_amount.add(nutrients.get("amount").toString());

                        if (nutrients.get("unitName").toString().equals("KCAL")){
                            nutrition_unit.add("CAL");
                        }
                        else{
                            nutrition_unit.add(nutrients.get("unitName").toString());
                        }

                    }
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.nutrition_recyclerview);
        NutritionAdapter nutritionAdapter = new NutritionAdapter(c,nutrition_name,nutrition_amount,nutrition_unit);
        recyclerView.setAdapter(nutritionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
    }
}
