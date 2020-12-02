package com.nphq.mealtimecalculator.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nphq.mealtimecalculator.FoodListActivity;
import com.nphq.mealtimecalculator.R;
import com.nphq.mealtimecalculator.homeActivities.NutritionList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static String food="NULL";

    public static EditText foodEdit;


    public static ArrayList<String> meal = new ArrayList<>();
    public static ArrayList<String> name= new ArrayList<>();
    public static ArrayList<String> portion= new ArrayList<>();
    public static ArrayList<String> insulin= new ArrayList<>();

    public static double breakFastInsulin;
    public static double LunchInsulin;
    public static double DinnerInsulin;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        breakFastInsulin = 0;
        LunchInsulin = 0;
        DinnerInsulin = 0;

        TextView date = root.findViewById(R.id.date);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);


        final Button[] buttons = new Button[6];
        final boolean[] button_active = {false,false,false};

        buttons[0] = root.findViewById(R.id.addBreakfast);
        buttons[1] = root.findViewById(R.id.addLunch);
        buttons[2] = root.findViewById(R.id.addDinner);
        buttons[3]  = root.findViewById(R.id.addMorningSnack);
        buttons[4] = root.findViewById(R.id.addAfternoonSnack);
        buttons[5] = root.findViewById(R.id.addEveningSnack);

        for (int i=0; i<6;i++){
            final int a = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    buttons[a].setAlpha(1f);
                    for (int j=0; j<6;j++){
                        if (j != a){
                            buttons[j].setAlpha(.5f);
                        }
                    }

                    for (int k = 0; k<3;k++){
                        button_active[k] = false;
                    }
                    button_active[a%3] = true;
                }
            });
        }


        foodEdit = root.findViewById(R.id.foodName);

        Button search = root.findViewById(R.id.searchFood);



        final EditText portionSize = root.findViewById(R.id.portion_size);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (portionSize.getText().toString().isEmpty() ||foodEdit.getText().toString().isEmpty() ){
                    Toast.makeText(getActivity(), "Please enter food name and portion size", Toast.LENGTH_SHORT).show();
                }
                else if (button_active[0] == false && button_active[1] == false && button_active[2] == false){
                    Toast.makeText(getActivity(), "Please choose type of meal", Toast.LENGTH_SHORT).show();
                }
                else {


                    food = foodEdit.getEditableText().toString();
                    try {
                        final double multiplier = Double.parseDouble(portionSize.getText().toString());
                        foodEdit.setText("");
                        portionSize.setText("");

                        String s = "";
                        if (button_active[0] == true){
                            s = "BREAKFAST";
                        }
                        else if (button_active[1] == true){
                            s = "LUNCH";
                        }
                        else if (button_active[2] == true){
                            s = "DINNER";
                        }

                        Intent next_screen = new Intent(getActivity(), NutritionList.class)
                                .putExtra("food", food).putExtra("multiplyBy", multiplier).putExtra("meal",s);
                        startActivityForResult(next_screen,1000);
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getActivity(), "Please enter a number for portion size", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


        Button foodList = root.findViewById(R.id.foodList);
        foodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FoodListActivity.class));
            }
        });



        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        boolean found = data.getBooleanExtra("found",false);
        if (!found){
            Toast.makeText(getActivity(),"Food not found in the database",Toast.LENGTH_LONG).show();
        }
        System.out.println("Insulin"+breakFastInsulin);

    }
}