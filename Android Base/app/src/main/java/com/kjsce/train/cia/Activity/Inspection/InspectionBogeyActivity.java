package com.kjsce.train.cia.Activity.Inspection;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.LoginActivity;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.GridAdapter;
import com.kjsce.train.cia.Adapter.MaintainenceGridAdapter;
import com.kjsce.train.cia.Entity.BogeyEntity;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Entity.TrainEntity;
import com.kjsce.train.cia.Listeners.AddDetailedReportListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.Backend.DetailedCardUtility;
import com.kjsce.train.cia.Utilities.Backend.DetailedReportUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InspectionBogeyActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> coach;
    SharedData sd;
    RelativeLayout generateReport;
    TextView train_name;
    List<BogeyEntity> bogeyEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpection_bogey);

        generateReport=findViewById(R.id.generaterepo);
        sd = new SharedData(getApplicationContext());
        train_name = (TextView) findViewById(R.id.train_name);

        coach = new ArrayList<String>();
        TrainEntity trainEntity= sd.getTrainEntity();
        for(int i=0;i<Integer.parseInt(trainEntity.getCompartments());i++){
            coach.add("S"+(i+1));
        }
        //coach = new ArrayList<>(Arrays.asList("S1", "S2 ", "S3", "S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3"));
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        sd.setCoachList(coach);
        train_name.setText(sd.getTrain());

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter(InspectionBogeyActivity.this, coach);
        mRecyclerView.setAdapter(mAdapter);

        generateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bogeyEntities =sd.getBogieEntity();
                TrainEntity trainEntity1 = sd.getTrainEntity();

                DetailedReport detailedReport = new DetailedReport(sd.getStation(),sd.getType(),trainEntity.getTrainNumber(),trainEntity.getTrainNumber(),
                        "2018",trainEntity.getManufacturer(),bogeyEntities,null);
                DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
                detailedReportUtility.addDetailedReport(detailedReport, new AddDetailedReportListener() {
                    @Override
                    public void onCompleteTask(String result) {
                        Toast.makeText(getApplicationContext(),"Complete",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent i = new Intent(getApplicationContext(), InspectionTrainReportActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed(){
        new MaterialDialog.Builder(InspectionBogeyActivity.this)
                .title("Confirm")
                .content("All data will be lost, Confirm?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Intent i = new Intent(getApplicationContext(),InspectionMenuActivity.class);
                        startActivity(i);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                    }
                })
                .show();

    }
}