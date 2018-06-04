package com.kjsce.train.cia.Activity.Maintainence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.MaintainenceGridAdapter;
import com.kjsce.train.cia.Entity.BogeyEntity;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Entity.TrainEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BogeyActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> coach;
    TextView number,name;
    SharedData sd;
    DetailedReport detailedReport;
    List<BogeyEntity> bogeyEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey);
        SharedData sd = new SharedData(getApplicationContext());
        coach = new ArrayList<String>();
        TrainEntity trainEntity= sd.getTrainEntity();
        detailedReport = sd.getDetailedReport();
        bogeyEntities = new ArrayList<BogeyEntity>();
        bogeyEntities = detailedReport.getBogeyEntityList();
        System.out.println("enti"+trainEntity);
        for(int i=0;i<bogeyEntities.size();i++){
            coach.add(bogeyEntities.get(i).getBogeyNumber());
        }
        //coach = new ArrayList<>(Arrays.asList("S1", "S2 ", "S3", "S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3"));
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        String trainName =  getIntent().getExtras().getString("TrainName");

        name = (TextView)findViewById(R.id.train_name);

        name.setText(trainName);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MaintainenceGridAdapter(BogeyActivity.this, coach);
        mRecyclerView.setAdapter(mAdapter);
    }
}
