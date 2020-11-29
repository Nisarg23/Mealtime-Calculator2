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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static String food="NULL";

    public static EditText foodEdit;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button[] buttons = new Button[6];

        buttons[0] = root.findViewById(R.id.addBreakfast);
        buttons[1] = root.findViewById(R.id.addLunch);
        buttons[2] = root.findViewById(R.id.addDinner);
        buttons[3]  = root.findViewById(R.id.addMorningSnack);
        buttons[4] = root.findViewById(R.id.addAfternoonSnack);
        buttons[5] = root.findViewById(R.id.addEveningSnack);

        for (int i=0; i<6;i++){
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }


        foodEdit = root.findViewById(R.id.foodName);

        Button search = root.findViewById(R.id.searchFood);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food = foodEdit.getEditableText().toString();
                startActivity(new Intent(getActivity(), NutritionList.class).putExtra("food",food));
                foodEdit.setText("");
            }
        });



        return root;
    }
}