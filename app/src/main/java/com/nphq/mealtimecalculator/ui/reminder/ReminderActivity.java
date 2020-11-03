package com.nphq.mealtimecalculator.ui.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nphq.mealtimecalculator.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {


    TextView editTextName;
    TextView editTextHour;
    TextView editTextMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Button save = findViewById(R.id.save_button);
        Button cancel = findViewById(R.id.cancel_button);

        final TextView am_pm = findViewById(R.id.am_pm);

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

        Button monday = findViewById(R.id.monday);
        Button tuesday = findViewById(R.id.tuesday);
        Button wednesday = findViewById(R.id.wednesday);
        Button thursday = findViewById(R.id.thursday);
        Button friday = findViewById(R.id.friday);
        Button saturday = findViewById(R.id.saturday);
        Button sunday = findViewById(R.id.sunday);

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


        editTextName = findViewById(R.id.editTextName);
        editTextHour = findViewById(R.id.editTextHour);
        editTextMinute = findViewById(R.id.editTextMinute);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextHour.getText().toString().isEmpty() || editTextMinute.getText().toString().isEmpty()){
                    Snackbar.make(findViewById(R.id.reminder_layout),"Please select a time",Snackbar.LENGTH_LONG).show();

                }
//                else if(Integer.parseInt(editTextHour.getText().toString()) > )
                else{
                    ReminderFragment.reminderAdapater.notifyDataSetChanged();
                    ReminderFragment.name.add(editTextName.getText().toString());
                    ReminderFragment.am_or_pm.add(am_pm.getText().toString());
                    ReminderFragment.hour.add(editTextHour.getText().toString());
                    ReminderFragment.minute.add(editTextMinute.getText().toString());
                    ReminderFragment.days.add(new ArrayList<Boolean>());
                    for (int i=0; i<7; i++){
                        ReminderFragment.days.get(ReminderFragment.days.size()-1).add(days_selcted[i]);
                    }

                    ReminderFragment.alarm_activated.add(true);
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_HOUR,Integer.parseInt(editTextHour.getText().toString()));
                    intent.putExtra(AlarmClock.EXTRA_MINUTES,Integer.parseInt(editTextMinute.getText().toString()));

                    Boolean isPm = true;

                    if (am_pm.getText().toString().equals("a.m.")){
                        isPm = false;
                    }
                    intent.putExtra(AlarmClock.EXTRA_IS_PM,isPm);

                    ArrayList<Integer> alarmDays = new ArrayList<>();
                    if (days_selcted[0] == true){
                        alarmDays.add(Calendar.MONDAY);
                    }
                    if (days_selcted[1] == true){
                        alarmDays.add(Calendar.TUESDAY);
                    }
                    if (days_selcted[2] == true){
                        alarmDays.add(Calendar.WEDNESDAY);
                    }
                    if (days_selcted[3] == true){
                        alarmDays.add(Calendar.THURSDAY);
                    }
                    if (days_selcted[4] == true){
                        alarmDays.add(Calendar.FRIDAY);
                    }
                    if (days_selcted[5] == true){
                        alarmDays.add(Calendar.SATURDAY);
                    }
                    if (days_selcted[6] == true){
                        alarmDays.add(Calendar.SUNDAY);
                    }
                    intent.putExtra(AlarmClock.EXTRA_DAYS,alarmDays);
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE,"Mealtime App: "+editTextName.getText().toString());




                    CheckBox vibrate = findViewById(R.id.vibrate);

                    intent.putExtra(AlarmClock.EXTRA_VIBRATE,vibrate.isChecked());


                    sendBroadcast(intent);
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