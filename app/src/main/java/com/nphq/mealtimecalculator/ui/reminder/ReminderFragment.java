package com.nphq.mealtimecalculator.ui.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nphq.mealtimecalculator.R;
import com.nphq.mealtimecalculator.ui.home.HomeViewModel;

import java.util.ArrayList;


public class ReminderFragment extends Fragment {
    public static ArrayList<AlarmCardView> alarmCardViews = new ArrayList<AlarmCardView>();
    ConstraintSet constraintSet = new ConstraintSet();
    public static ConstraintLayout constraintLayout;

    private HomeViewModel homeViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reminder, container, false);

        constraintLayout = root.findViewById(R.id.layout2);


        ImageView add = root.findViewById(R.id.add_reminder);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ReminderActivity.class));
            }
        });


        return root;
    }



}
