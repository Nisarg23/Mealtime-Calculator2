package com.nphq.mealtimecalculator.ui.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
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
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nphq.mealtimecalculator.MainActivity;
import com.nphq.mealtimecalculator.R;
import com.nphq.mealtimecalculator.helper.NotificationHelper;
import com.nphq.mealtimecalculator.ui.home.HomeViewModel;
import com.nphq.mealtimecalculator.ui.reminder.ReminderAdapater;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
    private RecyclerView notification_recyclerView;
    public static ArrayList<String> notification_name = new ArrayList<String>();
    public static ArrayList<String> notification_am_or_pm = new ArrayList<String>();
    public static ArrayList<String> notification_hour = new ArrayList<String>();
    public static ArrayList<String> notification_minute = new ArrayList<String>();
    public static ArrayList<ArrayList<Boolean>> notification_days = new ArrayList<>();
    public static ArrayList<Boolean> notification_activated = new ArrayList<Boolean>();

    public static NotificationAdapter notificationAdapater;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notification, container, false);

        Button toReminder = root.findViewById(R.id.nav_to_reminder);
        toReminder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().hide(MainActivity.fragmentManager.findFragmentByTag("notification")).commitNow();
                MainActivity.fragmentManager.beginTransaction().show(MainActivity.fragmentManager.findFragmentByTag("reminder")).commitNow();
                MainActivity.fragment_selected.replace("reminder",true);
                MainActivity.fragment_selected.replace("notification",false);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Reminder Page");
            }
        });


        ImageView add_notification = root.findViewById(R.id.add_notification);

        add_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // build notification

               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    sendNoticationAPI26();

               }
               else{
                   sendNotication();

               }

                startActivity(new Intent(getActivity(),NotificationActivity.class));

            }
        });


        notification_recyclerView = root.findViewById(R.id.list_of_notifications);


        notificationAdapater = new NotificationAdapter(getActivity(),notification_name,
                notification_am_or_pm,notification_hour,notification_minute,notification_days);

        notification_recyclerView.setAdapter(notificationAdapater);
        notification_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(notification_recyclerView.getContext(),
                getResources().getConfiguration().orientation);
        notification_recyclerView.addItemDecoration(dividerItemDecoration);

        return root;
    }

    private void sendNotication() {
        String title = "Mealtime Calculator App API Deprecated";
        String message = "Remember to take your Insulin! API Deprecated ";


        Intent intent = new Intent(getActivity(),MainActivity.class);
        intent.putExtra("goToNotification", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder builder = new Notification.Builder(getActivity())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(sound);

        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1,builder.build());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNoticationAPI26() {
        String title = "Mealtime Calculator App";
        String message = "Remember to take your Insulin! ";

        NotificationHelper helper;
        Notification.Builder builder;

        Intent intent = new Intent(getActivity(),MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        helper = new NotificationHelper(getActivity());

        builder = helper.getNotification(title,message,sound,pendingIntent,true);

        helper.getManager().notify(1,builder.build());




    }


}
