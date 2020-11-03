package com.nphq.mealtimecalculator.ui.notification;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nphq.mealtimecalculator.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    ArrayList<String> name;
    ArrayList<String> am_or_pm;
    ArrayList<String> hour;
    ArrayList<String> minute;
    ArrayList<ArrayList<Boolean>> days;
    Context c;

    public NotificationAdapter (Context c, ArrayList<String> name,ArrayList<String> am_or_pm,ArrayList<String> hour,
                                 ArrayList<String> minute,ArrayList<ArrayList<Boolean>> days){
        this.name = name;
        this.am_or_pm = am_or_pm;
        this.hour = hour;
        this.minute = minute;
        this.days = days;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(c);
        View view = layoutInflater.inflate(R.layout.item_notification,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (!name.isEmpty()){
            holder.alarm_name.setText(name.get(position));
        }

        holder.alarm_AM_or_PM.setText(am_or_pm.get(position));
        holder.alarm_time.setText(hour.get(position) +":"+minute.get(position));
        if (days.get(position).get(0).equals(true)){
            holder.M.setTextColor(Color.parseColor("#0033cd"));
        }
        if (days.get(position).get(1).equals(true)){
            holder.Tu.setTextColor(Color.parseColor("#0033cd"));
        }
        if (days.get(position).get(2).equals(true)){
            holder.W.setTextColor(Color.parseColor("#0033cd"));
        }
        if (days.get(position).get(3).equals(true)){
            holder.Th.setTextColor(Color.parseColor("#0033cd"));
        }
        if (days.get(position).get(4).equals(true)){
            holder.F.setTextColor(Color.parseColor("#0033cd"));
        }
        if (days.get(position).get(5).equals(true)){
            holder.Sa.setTextColor(Color.parseColor("#0033cd"));
        }
        if (days.get(position).get(6).equals(true)){
            holder.Su.setTextColor(Color.parseColor("#0033cd"));
        }

        holder.alarm_on_or_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NotificationFragment.notification_activated.get(position).equals(true)){
                    NotificationFragment.notification_activated.set(position,false);
                    System.out.println((position+1) + "  off");

                }
                else if (NotificationFragment.notification_activated.get(position).equals(false)){
                    NotificationFragment.notification_activated.set(position,true);
                    System.out.println((position+1) + "  on");

                }

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(c)
                        .setTitle("Delete Reminder")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                name.remove(position);
                                am_or_pm.remove(position);
                                hour.remove(position);
                                minute.remove(position);
                                days.remove(position);
                                NotificationFragment.notification_activated.remove(position);
                                notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null).show();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView alarm_AM_or_PM;
        TextView alarm_time;
        TextView alarm_days_activated;
        Switch alarm_on_or_off;
        TextView alarm_name;
        TextView M;
        TextView Tu;
        TextView W;
        TextView Th;
        TextView F;
        TextView Sa;
        TextView Su;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            alarm_AM_or_PM = itemView.findViewById(R.id.notification_AM_or_PM);
            alarm_time = itemView.findViewById(R.id.notification_time);
            alarm_name = itemView.findViewById(R.id.notification_name);
            alarm_on_or_off = itemView.findViewById(R.id.notification_on_or_off);
            M = itemView.findViewById(R.id.notification_M);
            Tu = itemView.findViewById(R.id.notification_Tu);
            W = itemView.findViewById(R.id.notification_W);
            Th = itemView.findViewById(R.id.notification_Th);
            F = itemView.findViewById(R.id.notification_F);
            Sa = itemView.findViewById(R.id.notification_Sa);
            Su = itemView.findViewById(R.id.notification_Su);



        }


    }
}
