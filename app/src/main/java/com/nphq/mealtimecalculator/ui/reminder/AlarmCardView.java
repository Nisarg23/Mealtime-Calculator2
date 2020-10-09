package com.nphq.mealtimecalculator.ui.reminder;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class AlarmCardView {
    private CardView c;
    private Context context;

    public AlarmCardView(Context current, CardView c){
        this.c = c;
        this.context = current;
        c.setId(View.generateViewId());


        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, context.getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 450, context.getResources().getDisplayMetrics());
        float radius = (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13, context.getResources().getDisplayMetrics());

        c.setRadius(radius);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        c.setLayoutParams(layoutParams);

//        ConstraintSet constraintSet = new ConstraintSet();
//        ConstraintLayout constraintLayout = ReminderFragment.constraintLayout;
//        constraintLayout.addView(c);
//        constraintSet.clone(constraintLayout);
//        constraintSet.connect(c.getId(),constraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
//        constraintSet.connect(c.getId(),constraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
//        constraintSet.connect(c.getId(),constraintSet.TOP,ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
//        constraintSet.applyTo(constraintLayout);

    }

    public CardView getCardView(){
        return c;
    }


}
