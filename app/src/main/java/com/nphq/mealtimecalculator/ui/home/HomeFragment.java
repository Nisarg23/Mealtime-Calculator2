package com.nphq.mealtimecalculator.ui.home;

import android.content.Context;
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
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nphq.mealtimecalculator.R;
import com.nphq.mealtimecalculator.homeActivities.API_call;
import com.nphq.mealtimecalculator.homeActivities.ExerciseActivity;
import com.nphq.mealtimecalculator.homeActivities.FoodActivity;
import com.nphq.mealtimecalculator.homeActivities.GlucoseLevelsActivity;
import com.nphq.mealtimecalculator.homeActivities.NutritionList;
import com.nphq.mealtimecalculator.homeActivities.SleepActivity;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static String food="NULL";

    public static EditText foodEdit;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);


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
                else {


                    food = foodEdit.getEditableText().toString();
                    try {
                        final double multiplier = Double.parseDouble(portionSize.getText().toString());
                        startActivity(new Intent(getActivity(), NutritionList.class)
                                .putExtra("food", food).putExtra("multiplyBy", multiplier));
                        foodEdit.setText("");
                        portionSize.setText("");
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getActivity(), "Please enter a number for portion size", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });



        return root;
    }
}