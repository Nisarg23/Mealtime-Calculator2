package com.nphq.mealtimecalculator.ui.notification;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.nphq.mealtimecalculator.R;
import com.nphq.mealtimecalculator.ui.reminder.ReminderFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    TextView editTextName;
    TextView editTextHour;
    TextView editTextMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification);

        Button save = findViewById(R.id.notification_save_button);
        Button cancel = findViewById(R.id.notification_cancel_button);

        final TextView am_pm = findViewById(R.id.notification_am_pm);

        am_pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (am_pm.getText().toString() == "a.m."){
                    am_pm.setText("p.m.");
                }
                else{
                    am_pm.setText("a.m.");
                }
            }
        });

        Button monday = findViewById(R.id.notification_monday);
        Button tuesday = findViewById(R.id.notification_tuesday);
        Button wednesday = findViewById(R.id.notification_wednesday);
        Button thursday = findViewById(R.id.notification_thursday);
        Button friday = findViewById(R.id.notification_friday);
        Button saturday = findViewById(R.id.notification_saturday);
        Button sunday = findViewById(R.id.notification_sunday);

        final Button[] days = {monday,tuesday,wednesday,thursday,friday,saturday,sunday};
        final boolean[] days_selcted = {false,false,false,false,false,false,false};

        for (int i = 0; i<7; i++) {
            final int finalI = i;
            days[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (days_selcted[finalI] == false) {
                        days_selcted[finalI] = true;
                        days[finalI].setBackgroundResource(R.drawable.tiel_round_button);
                    }
                    else if (days_selcted[finalI] == true){
                        days_selcted[finalI] = false;
                        days[finalI].setBackgroundResource(R.drawable.white_round_button);
                    }
                    else{
                        Toast.makeText(getBaseContext(),"Error",Toast.LENGTH_LONG);
                    }
                }
            });
        }


        editTextName = findViewById(R.id.notification_editTextName);
        editTextHour = findViewById(R.id.notification_editTextHour);
        editTextMinute = findViewById(R.id.notification_editTextMinute);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextHour.getText().toString().isEmpty() || editTextMinute.getText().toString().isEmpty()){
                    Snackbar.make(findViewById(R.id.reminder_layout),"Please select a time",Snackbar.LENGTH_LONG).show();

                }
//                else if(Integer.parseInt(editTextHour.getText().toString()) > )
                else{
                    NotificationFragment.notificationAdapater.notifyDataSetChanged();
                    NotificationFragment.notification_name.add(editTextName.getText().toString());
                    NotificationFragment.notification_am_or_pm.add(am_pm.getText().toString());
                    NotificationFragment.notification_hour.add(editTextHour.getText().toString());
                    NotificationFragment.notification_minute.add(editTextMinute.getText().toString());
                    NotificationFragment.notification_days.add(new ArrayList<Boolean>());
                    for (int i=0; i<7; i++){
                        NotificationFragment.notification_days.get(NotificationFragment.notification_days.size()-1).add(days_selcted[i]);
                    }

                    NotificationFragment.notification_activated.add(true);

                    finish();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
