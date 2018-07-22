package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.kjsce.train.cia.R;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.List;

public class AnalysisActivity extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton exportButton, backButton;
    private List<Boolean> statusChecked, typeChecked;
    private List<String> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        initialize();

        StackedBarChart mStackedBarChart = (StackedBarChart) findViewById(R.id.stackedbarchart);

        StackedBarModel s1 = new StackedBarModel("12.4");

        s1.addBar(new BarModel(2.3f, 0xFF63CBB0));
        s1.addBar(new BarModel(2.3f, 0xFF56B7F1));
        s1.addBar(new BarModel(2.3f, 0xFFCDA67F));

        StackedBarModel s2 = new StackedBarModel("13.4");
        s2.addBar(new BarModel(1.1f, 0xFF63CBB0));
        s2.addBar(new BarModel(2.7f, 0xFF56B7F1));
        s2.addBar(new BarModel(0.7f, 0xFFCDA67F));

        StackedBarModel s3 = new StackedBarModel("14.4");

        s3.addBar(new BarModel(2.3f, 0xFF63CBB0));
        s3.addBar(new BarModel(2.f, 0xFF56B7F1));
        s3.addBar(new BarModel(3.3f, 0xFFCDA67F));

        StackedBarModel s4 = new StackedBarModel("15.4");
        s4.addBar(new BarModel(1.f, 0xFF63CBB0));
        s4.addBar(new BarModel(4.2f, 0xFF56B7F1));
        s4.addBar(new BarModel(2.1f, 0xFFCDA67F));

        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);
        mStackedBarChart.addBar(s4);

        mStackedBarChart.startAnimation();
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        exportButton = (ImageButton) findViewById(R.id.button_export);
        backButton = (ImageButton) findViewById(R.id.back_button);
        statusChecked = sharedData.getStatusCheckedList();
        typeChecked = sharedData.getTypeCheckedList();
        dateList = sharedData.getDateList();
    }
}
