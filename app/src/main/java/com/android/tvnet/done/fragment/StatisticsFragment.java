package com.android.tvnet.done.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tvnet.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsFragment extends Fragment {
    @BindView(R.id.lineChart)
    LineChart lineChart;
    private Context context;

    public static StatisticsFragment newInstance() {
        final StatisticsFragment fragment = new StatisticsFragment();
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        ButterKnife.bind(this, view);


        lineChart.setDrawGridBackground(false);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setScaleYEnabled(false);
        lineChart.setScaleXEnabled(true);

        setCharterData();

        return view;
    }

private void setCharterData(){
        //TODO Delet this logic
    ArrayList<Entry> entries = new ArrayList<>();
    entries.add(new Entry(0, 4));
    entries.add(new Entry(1, 1));
    entries.add(new Entry(2, 2));
    entries.add(new Entry(3, 4));

    LineDataSet dataSet = new LineDataSet(entries, "Customized values");
    dataSet.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
    dataSet.setValueTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

    //****
    // Controlling X axis
    XAxis xAxis = lineChart.getXAxis();
    // Set the xAxis position to bottom. Default is top
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    //Customizing x axis value
    final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr"};


    //***
    // Controlling right side of y axis
    YAxis yAxisRight = lineChart.getAxisRight();
    yAxisRight.setEnabled(false);

    //***
    // Controlling left side of y axis
    YAxis yAxisLeft = lineChart.getAxisLeft();
    yAxisLeft.setGranularity(1f);

    // Setting Data
    LineData data = new LineData(dataSet);
    lineChart.setData(data);
    lineChart.animateX(2500);
    //refresh
    lineChart.invalidate();
}
}