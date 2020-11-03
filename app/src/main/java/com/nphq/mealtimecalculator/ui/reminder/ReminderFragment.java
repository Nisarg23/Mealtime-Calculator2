package com.nphq.mealtimecalculator.ui.reminder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nphq.mealtimecalculator.MainActivity;
import com.nphq.mealtimecalculator.R;
import com.nphq.mealtimecalculator.ui.home.HomeViewModel;

import java.util.ArrayList;


public class ReminderFragment extends Fragment {


    private HomeViewModel homeViewModel;

    private RecyclerView recyclerView;
    public static ArrayList<String> name = new ArrayList<String>();
    public static ArrayList<String> am_or_pm = new ArrayList<String>();
    public static ArrayList<String> hour = new ArrayList<String>();
    public static ArrayList<String> minute = new ArrayList<String>();
    public static ArrayList<ArrayList<Boolean>> days = new ArrayList<>();
    public static ArrayList<Boolean> alarm_activated = new ArrayList<Boolean>();


    public static ReminderAdapater reminderAdapater;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reminder, container, false);

        recyclerView = root.findViewById(R.id.list_of_reminders);


        reminderAdapater = new ReminderAdapater(getActivity(),name,am_or_pm,hour,minute,days);

        recyclerView.setAdapter(reminderAdapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                getResources().getConfiguration().orientation);
        recyclerView.addItemDecoration(dividerItemDecoration);


        ImageView add = root.findViewById(R.id.add_notification);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ReminderActivity.class));

            }
        });


        Button toNotification = root.findViewById(R.id.nav_to_notification);
        toNotification.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
               MainActivity.fragmentManager.beginTransaction().hide(MainActivity.fragmentManager.findFragmentByTag("reminder")).commitNow();
                MainActivity.fragmentManager.beginTransaction().show(MainActivity.fragmentManager.findFragmentByTag("notification")).commitNow();
                MainActivity.fragment_selected.replace("reminder",false);
                MainActivity.fragment_selected.replace("notification",true);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notification Page");

            }
        });


        return root;
    }



}
