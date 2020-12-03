package com.nphq.mealtimecalculator.homeActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nphq.mealtimecalculator.MainActivity;
import com.nphq.mealtimecalculator.R;
import com.nphq.mealtimecalculator.ui.home.HomeFragment;

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
    String meal;
    double multiplier;
    boolean finish;

    double carbs = 0;
    double fiber = 0;
    double cal = 0;

    Intent previousScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_list);

        Intent intent = getIntent();
        food = intent.getExtras().getString("food","null");
        meal = intent.getExtras().getString("meal","null");
        System.out.println("meal is "+meal);
        multiplier = intent.getExtras().getDouble("multiplyBy",1);

         previousScreen = new Intent(getApplicationContext(), MainActivity.class);

        finish = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL urlForGetRequest = null;
                StringBuffer response = new StringBuffer();


                try {
                    urlForGetRequest = new URL("https://api.nal.usda.gov/fdc/v1/foods/list?api_key=kwAPxa4YJSYCvhOz6OZdJbOgZX1lse2FtpldfgG1&query=" + food);
                    String readLine = null;
                    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conection.getInputStream()));

                    while ((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                    in.close();

                } catch (MalformedURLException e) {
                    Toast.makeText(c, "Food Not Found", Toast.LENGTH_LONG).show();
                    //Starting the previous Intent

                    //Sending the data to Activity_A
                    previousScreen.putExtra("found", false);
                    setResult(1000, previousScreen);
                    finish = true;
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(c, "Food Not Found", Toast.LENGTH_LONG).show();
                    finish = true;
                    e.printStackTrace();
                }

                org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
                org.json.simple.JSONArray arr = null;
                try {
                    arr = (org.json.simple.JSONArray) parser.parse(response.toString());
                    for (int i = 0; i < 1; i++) {
                        org.json.simple.JSONObject jsonobject = (org.json.simple.JSONObject) arr.get(i);

                        org.json.simple.JSONArray arr2 = (org.json.simple.JSONArray) jsonobject.get("foodNutrients");

                        for (int j = 0; j < arr2.size(); j++) {
                            org.json.simple.JSONObject nutrients = (org.json.simple.JSONObject) arr2.get(j);

                            if (nutrients.get("name").toString().equals("Carbohydrate, by difference")){
                                carbs = Double.parseDouble(nutrients.get("amount").toString())*multiplier;
                            }
                            else if (nutrients.get("name").toString().equals("Fiber, total dietary")){
                                fiber = Double.parseDouble(nutrients.get("amount").toString())*multiplier;
                            }
                            nutrition_name.add(nutrients.get("name").toString());

                            double amount = Double.parseDouble(nutrients.get("amount").toString())*multiplier;
                            String string_amount = String.format("%.2f", amount);
                            nutrition_amount.add(string_amount);

                            if (nutrients.get("unitName").toString().equals("KCAL")) {
                                nutrition_unit.add("CAL");
                                cal = Double.parseDouble(nutrients.get("amount").toString());

                            } else {
                                nutrition_unit.add(nutrients.get("unitName").toString());
                            }

                        }
                    }
                } catch (ParseException e) {
                    Toast.makeText(c, "There was an error in the app", Toast.LENGTH_LONG).show();
                    finish = true;
                    e.printStackTrace();
                } catch (ClassCastException e) {
                    previousScreen.putExtra("found", false);
                    setResult(1000, previousScreen);
                    finish = true;
                }
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (finish == true){
            finish();
        }

        recyclerView = findViewById(R.id.nutrition_recyclerview);
        NutritionAdapter nutritionAdapter = new NutritionAdapter(c,nutrition_name,nutrition_amount,nutrition_unit);
        recyclerView.setAdapter(nutritionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));


        Button add = findViewById(R.id.addToList);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(NutritionList.this);
                builder.setTitle("Please enter your current blood sugar level (mmol/L)");

// Set up the input
                final EditText input = new EditText(NutritionList.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        boolean con = true;
//                        while (con){
//                            try {
//                                Double.parseDouble(input.getText().toString());
//                                con = false;
//                            } catch(NumberFormatException e){
//                                Toast.makeText(NutritionList.this,"Please input a number",Toast.LENGTH_LONG).show();
//                            }
//                        }
                        HomeFragment.meal.add(meal);
                        HomeFragment.name.add(food);
                        HomeFragment.portion.add(Double.toString(multiplier));

                        double time_factor = 1;
                        if (meal.equals("LUNCH")){
                            time_factor = 1.05;
                        }
                        else if (meal.equals("DINNER")){
                            time_factor = 1.2;
                        }

                        // calulate insulin
                        // insulin = CCD + HBSC
                        // CCD = (carbs - fiber) / ((500/daily inslin dosage)*18)
                        // HBSC = (blood sugar - target sugar) / (1800 / daily inslin dosage )
                        // daily insulin dosage = wight in lbs / 4

                        double CCD, HBSC, insulin, customize_correction,DID;
                        DID = 50;
                        CCD = (carbs - fiber) / ((500/50)*18);
                        HBSC = (170 - 120) / (1800 / 50);
                        customize_correction = 1;
                        insulin = (HBSC + CCD)*customize_correction*time_factor;


                        String string_insulin = String.format("%.2f", insulin);
                        HomeFragment.insulin.add(string_insulin);
                        previousScreen.putExtra("found", true);
                        setResult(1000, previousScreen);

                        if (meal.equals("BREAKFAST")){
                            HomeFragment.breakFastInsulin = HomeFragment.breakFastInsulin + insulin;
                            System.out.println("expected"+HomeFragment.breakFastInsulin + insulin);
                        }
                        else if (meal.equals("LUNCH")){
                            HomeFragment.LunchInsulin = HomeFragment.LunchInsulin + insulin;
                        }
                        else if (meal.equals("DINNER")){
                            HomeFragment.DinnerInsulin = HomeFragment.DinnerInsulin + insulin;
                        }
                        HomeFragment.calories = HomeFragment.calories + cal;

                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


            }
        });
    }

    public void onBackPressed(){
        previousScreen.putExtra("found", true);
        setResult(1000, previousScreen);
        super.onBackPressed();
    }
}
