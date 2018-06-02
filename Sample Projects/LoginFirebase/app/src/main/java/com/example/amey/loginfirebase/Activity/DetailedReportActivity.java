package com.example.amey.loginfirebase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Listener.AddDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListener;
import com.example.amey.loginfirebase.Listener.RemoveDetailedReportListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.DetailedReportUtility;

public class DetailedReportActivity extends AppCompatActivity {
    private Button get_det, add_det, rem_det;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailreport);

        get_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
                DetailedReport detailedRep = new DetailedReport("Ramani", "Dombivli",
                        "Error Resolving", "1511112", "Rajdhani", "23", "parakh", null, null);

                detailedReportUtility.getDetailedReport(detailedRep, new GetDetailedReportListener() {
                    @Override
                    public void onCompleteTask(DetailedReport detailedReport) {

                    }
                });
            }
        });

        add_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
                DetailedReport detailedRep = new DetailedReport("Ramani", "Dombivli",
                        "Error Resolving", "1511112", "Rajdhani", "23", "parakh", null, null);
                detailedReportUtility.addDetailedReport(detailedRep, new AddDetailedReportListener() {
                    @Override
                    public void onCompleteTask(String result) {

                    }
                });

            }
        });

        rem_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
                DetailedReport detailedRep = new DetailedReport("Ramani", "Dombivli",
                        "Error Resolving", "1511112", "Rajdhani", "23", "parakh", null, null);
                detailedReportUtility.removeDetailedReport(detailedRep, new RemoveDetailedReportListener() {
                    @Override
                    public void onCompleteTask(String result) {
                        System.out.println("Result: " + result);
                    }
                });

            }
        });

    }
}