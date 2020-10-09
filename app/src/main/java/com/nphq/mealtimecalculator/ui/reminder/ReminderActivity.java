package com.nphq.mealtimecalculator.ui.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nphq.mealtimecalculator.R;

import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {
    Context context = this;
    ArrayList<AlarmCardView> alarmCardViews = ReminderFragment.alarmCardViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Button save = findViewById(R.id.save_button);
        Button cancel = findViewById(R.id.cancel_button);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintSet constraintSet = new ConstraintSet();
                ConstraintLayout constraintLayout = ReminderFragment.constraintLayout;

                CardView c = new CardView(context);
                AlarmCardView a = new AlarmCardView(context,c);
                constraintLayout.addView(c);
                constraintSet.clone(constraintLayout);
                if (alarmCardViews.size() == 0) {
                    constraintSet.connect(a.getCardView().getId(), constraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                    constraintSet.connect(a.getCardView().getId(), constraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                    constraintSet.connect(a.getCardView().getId(), constraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 20);
                }
                else{
                    constraintSet.connect(a.getCardView().getId(), constraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                    constraintSet.connect(a.getCardView().getId(), constraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                    constraintSet.connect(a.getCardView().getId(), constraintSet.TOP, alarmCardViews.get(alarmCardViews.size()-1).getCardView().getId(), ConstraintSet.BOTTOM, 20);
                }
                constraintSet.applyTo(constraintLayout);
                alarmCardViews.add(a);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void createCardView(CardView c){
//        c.setId(View.generateViewId());
//
//
//        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, context.getResources().getDisplayMetrics());
//        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 450, context.getResources().getDisplayMetrics());
//        float radius = (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13, context.getResources().getDisplayMetrics());
//
//        c.setRadius(radius);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
//        c.setLayoutParams(layoutParams);
//
//        alarmCardViews.add(c);
    }
}