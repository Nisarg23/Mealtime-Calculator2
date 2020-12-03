package com.nphq.mealtimecalculator.ui.stats;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.nphq.mealtimecalculator.ClaimsXAxisValueFormatter;
import com.nphq.mealtimecalculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StatsFragment extends Fragment {
    float max_daily;
    float min_daily;

    float max_weekly;
    float min_weekly;

    Calendar c;
    DatePickerDialog datePickerDialog;

    LineChart volumeReportChart;


    private SleepViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SleepViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stats, container, false);
//
//        LineChart dailyChart = root.findViewById(R.id.daily_chart);
//        LineChart weeklyChart = root.findViewById(R.id.weekly_chart);
//
//        LineDataSet lineDataSet1 = new LineDataSet(yourDailyValues(),"Your Insulin");
//        LineDataSet lineDataSet2 = new LineDataSet(recommendedInsulinValues(),"Recommended Insulin");
//        lineDataSet1.setColor(Color.BLUE);
//        lineDataSet1.setColor(Color.GREEN);
//
//        //lineDataSet1.setLineWidth(3f);
//        lineDataSet2.setLineWidth(3f);
//
//
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//        //dataSets.add(lineDataSet1);
//        dataSets.add(lineDataSet2);
//
//        LineData data = new LineData(dataSets);
//        dailyChart.setData(data);
//        weeklyChart.setData(data);
//
//        Legend d = dailyChart.getLegend();
//        d.setTextSize(200f);
//
//        Legend w = weeklyChart.getLegend();
//        w.setTextSize(200f);
//
//
//        XAxis xAxis = dailyChart.getXAxis();
//        xAxis.setDrawGridLines(true);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTextSize(15);
//
//        YAxis yAxis = dailyChart.getAxisLeft();
//
//        yAxis.setDrawGridLines(false);
//        yAxis.setTextSize(15);
//
//        YAxis yAxis_right = dailyChart.getAxisRight();
//        yAxis_right.setDrawLabels(false);
//
//        max_daily = 200;
//        min_daily = 80;
//
//        yAxis.setAxisMaxValue(max_daily);
//        yAxis.setAxisMinValue(min_daily);
//
//
//        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"));
//        dailyChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));

//        List<String> xAxisValues = getWeeklyXLabels();
//        dailyChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));

         volumeReportChart = root.findViewById(R.id.weekly_chart);
        volumeReportChart.setTouchEnabled(true);
        volumeReportChart.setPinchZoom(true);

        LimitLine ll1 = new LimitLine(30f,"Title");
        ll1.setLineColor(getResources().getColor(R.color.tiel));
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(35f, "");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);

        XAxis xAxis = volumeReportChart.getXAxis();
        YAxis leftAxis = volumeReportChart.getAxisLeft();

        xAxis.setTextSize(20);
        leftAxis.setTextSize(20);

        XAxis.XAxisPosition position = XAxis.XAxisPosition.BOTTOM;
        xAxis.setPosition(position);

        volumeReportChart.getDescription().setEnabled(true);
        Description description = new Description();

        description.setText("Week");
        description.setTextSize(15f);

        Date d = new Date();

        List<String> datesList = new ArrayList<>(Arrays.asList("2020-11-01","2020-11-02","2020-11-03","2020-11-04","2020-11-05","2020-11-06","2020-11-07"));
        List<Double> values1 = new ArrayList<Double>(Arrays.asList(140.0,140.0,140.0,140.0,140.0,140.0,140.0));
        List<Double> values2 = new ArrayList<Double>(Arrays.asList(150.0,200.0,180.0,120.0,110.0,100.0,140.0));
        xAxis.setValueFormatter(new ClaimsXAxisValueFormatter(datesList));
        renderData(datesList,values1,values2);


        Legend e = volumeReportChart.getLegend();
        e.setTextSize(50f);

        Button date = root.findViewById(R.id.datePicker);
        final TextView textView = root.findViewById(R.id.dateText);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int Day = c.get(Calendar.DAY_OF_MONTH);
                int Month = c.get(Calendar.MONTH);
                int Year = c.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        textView.setText(day +"/" +(month+1)+"/"+year);
                    }
                },Year,Month,Day);
                datePickerDialog.show();

            }
        });

        return root;
    }
    public void renderData(List<String> dates, List<Double> allAmounts1, List<Double> allAmounts2 ) {

//        final ArrayList<String> xAxisLabel = new ArrayList<>();
//        xAxisLabel.add("1");
//        xAxisLabel.add("7");
//        xAxisLabel.add("14");
//        xAxisLabel.add("21");
//        xAxisLabel.add("28");
//        xAxisLabel.add("30");

        XAxis xAxis = volumeReportChart.getXAxis();
        XAxis.XAxisPosition position = XAxis.XAxisPosition.BOTTOM;
        xAxis.setPosition(position);
        xAxis.enableGridDashedLine(2f, 7f, 0f);
        xAxis.setAxisMaximum(7f);
        xAxis.setAxisMinimum(0f);
        xAxis.setLabelCount(8, true);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(7f);
        xAxis.setLabelRotationAngle(315f);

        xAxis.setValueFormatter(new ClaimsXAxisValueFormatter(dates));

        xAxis.setCenterAxisLabels(true);


        xAxis.setDrawLimitLinesBehindData(true);



//        LimitLine ll1 = new LimitLine(Float.parseFloat(UISetters.getDateInNumber()), UISetters.getDateInNumber());
//        ll1.setLineColor(getResources().getColor(R.color.greyish_brown));
//        ll1.setLineWidth(4f);
//        ll1.enableDashedLine(10f, 10f, 0f);
//        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(35f, "");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
        ll2.setLineColor(Color.parseColor("#FFFFFF"));

        xAxis.removeAllLimitLines();
        //xAxis.addLimitLine(ll1);
        xAxis.addLimitLine(ll2);


        YAxis leftAxis = volumeReportChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        //leftAxis.addLimitLine(ll1);
        //leftAxis.addLimitLine(ll2);
//
//        leftAxis.setAxisMaximum(findMaximumValueInList(allAmounts).floatValue() + 100f);
//        leftAxis.setAxisMinimum(0f);
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
//        leftAxis.setDrawZeroLine(false);
//        leftAxis.setDrawLimitLinesBehindData(false);
//        //XAxis xAxis = mBarChart.getXAxis();
//        leftAxis.setValueFormatter(new ClaimsYAxisValueFormatter());

        volumeReportChart.getDescription().setEnabled(true);
        Description description = new Description();
        // description.setText(UISetters.getFullMonthName());//commented for weekly reporting
        description.setText("Week");
        description.setTextSize(15f);
        volumeReportChart.getDescription().setPosition(0f, 0f);
        volumeReportChart.setDescription(description);
        volumeReportChart.getAxisRight().setEnabled(false);

        //setData()-- allAmounts is data to display;
        setDataForWeeksWise(allAmounts1,allAmounts2);



    }

    private void setDataForWeeksWise(List<Double> amounts1, List<Double> amounts2 ) {

        ArrayList<Entry> values1 = new ArrayList<>();
        for (int i=0; i<amounts1.size();i++){
            values1.add(new Entry(i, amounts1.get(i).floatValue()));
        }

        ArrayList<Entry> values2 = new ArrayList<>();
        for (int i=0; i<amounts2.size();i++){
            values2.add(new Entry(i, amounts2.get(i).floatValue()));
        }


        LineDataSet set1;
        LineDataSet set2;
        if (volumeReportChart.getData() != null &&
                volumeReportChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) volumeReportChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            volumeReportChart.getData().notifyDataChanged();
            volumeReportChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values1, "Recommended Insulin");
            set1.setDrawCircles(true);
            set1.enableDashedLine(10f, 0f, 0f);
            set1.enableDashedHighlightLine(10f, 0f, 0f);
            set1.setColor(getResources().getColor(R.color.tiel));
            set1.setCircleColor(getResources().getColor(R.color.tiel));
            set1.setLineWidth(2f);//line size
            set1.setCircleRadius(5f);
            set1.setDrawCircleHole(true);
            set1.setValueTextSize(10f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(5f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(5.f);

            set2 = new LineDataSet(values2, "Your Insulin");
            set2.setDrawCircles(true);
            set2.enableDashedLine(10f, 0f, 0f);
            set2.enableDashedHighlightLine(10f, 0f, 0f);
            set2.setColor(getResources().getColor(R.color.green));
            set2.setCircleColor(getResources().getColor(R.color.green));
            set2.setLineWidth(2f);//line size
            set2.setCircleRadius(5f);
            set2.setDrawCircleHole(true);
            set2.setValueTextSize(10f);
            //set2.setDrawFilled(true);
            set2.setFormLineWidth(5f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(5.f);

            if (Utils.getSDKInt() >= 18) {
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.blue_bg);
//                set1.setFillDrawable(drawable);
                set1.setFillColor(Color.WHITE);

            } else {
                set1.setFillColor(Color.WHITE);
            }
            set1.setDrawValues(true);
            set2.setDrawValues(true);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            LineData data = new LineData(dataSets);

            volumeReportChart.setData(data);



        }
    }

    private List<String> getWeeklyXLabels(){
        List<String> xAxisValues = new ArrayList<>(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"));
        return  xAxisValues;
    }

    private ArrayList<Entry> yourDailyValues(){
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0,150));
        values.add(new Entry(1,140));
        values.add(new Entry(2,130));
        values.add(new Entry(3,145));

        return values;
    }

    private ArrayList<Entry> recommendedInsulinValues(){
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

}