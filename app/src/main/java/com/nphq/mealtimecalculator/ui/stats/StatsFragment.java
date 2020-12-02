package com.nphq.mealtimecalculator.ui.stats;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.nphq.mealtimecalculator.R;

import java.util.ArrayList;
import java.util.Map;

public class StatsFragment extends Fragment {
    float max_daily;
    float min_daily;

    float max_weekly;
    float min_weekly;


    private SleepViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SleepViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stats, container, false);

        LineChart dailyChart = root.findViewById(R.id.daily_chart);
        LineChart weeklyChart = root.findViewById(R.id.weekly_chart);

        LineDataSet lineDataSet1 = new LineDataSet(yourDailyValues(),"Your Insulin");
        LineDataSet lineDataSet2 = new LineDataSet(recommendedDailyValues(),"Recommended Insulin");
        lineDataSet1.setColor(Color.BLUE);
        lineDataSet1.setColor(Color.GREEN);

        lineDataSet1.setLineWidth(3f);
        lineDataSet2.setLineWidth(3f);



        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        LineData data = new LineData(dataSets);
        dailyChart.setData(data);
        weeklyChart.setData(data);

        Legend d = dailyChart.getLegend();
        d.setTextSize(200f);

        Legend w = weeklyChart.getLegend();
        w.setTextSize(200f);


        XAxis xAxis = dailyChart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(15);

        YAxis yAxis = dailyChart.getAxisLeft();

        yAxis.setDrawGridLines(false);
        yAxis.setTextSize(15);

        YAxis yAxis_right = dailyChart.getAxisRight();
        yAxis_right.setDrawLabels(false);

        max_daily = 200;
        min_daily = 80;

        yAxis.setAxisMaxValue(max_daily);
        yAxis.setAxisMinValue(min_daily);


        return root;
    }


    private ArrayList<Entry> yourDailyValues(){
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0,150));
        values.add(new Entry(1,140));
        values.add(new Entry(2,130));
        values.add(new Entry(3,145));

        return values;
    }

    private ArrayList<Entry> recommendedDailyValues(){
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0,140));
        values.add(new Entry(1,140));
        values.add(new Entry(2,140));
        values.add(new Entry(3,140));

        return values;
    }
    private ArrayList<Entry> yourWeeklyValues(){
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0,150));
        values.add(new Entry(1,140));
        values.add(new Entry(2,130));
        values.add(new Entry(3,145));

        return values;
    }

    private ArrayList<Entry> recommendedWeeklyValues(){
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0,140));
        values.add(new Entry(1,140));
        values.add(new Entry(2,140));
        values.add(new Entry(3,140));

        return values;
    }
}