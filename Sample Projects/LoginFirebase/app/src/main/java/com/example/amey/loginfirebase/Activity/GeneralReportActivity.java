package com.example.amey.loginfirebase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListener;
import com.example.amey.loginfirebase.Listener.RemoveGeneralReportListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.GeneralReportUtility;

public class GeneralReportActivity extends AppCompatActivity {
    private Button getReport,addReport,removeReport;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        getReport = (Button)findViewById(R.id.getReport);
        addReport = (Button)findViewById(R.id.addReport);
        removeReport = (Button)findViewById(R.id.removeReport);

        getReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralReportUtility generalReportUtility= new GeneralReportUtility();
                GeneralReport generalRep = new GeneralReport("Ramani" , "Dombivli" ,
                        "Error Resolving", "1511102","Rajdhani","23","bcd",
                        new GeneralCard("xyzz", null,null,null,false),null);

                generalReportUtility.getGeneralReport(generalRep, new GetGeneralReportListener() {
                    @Override
                    public void onCompleteTask(GeneralReport generalReport) {

                    }
                });
            }
        });

        addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralReportUtility generalReportUtility= new GeneralReportUtility();
                GeneralReport generalRep = new GeneralReport("Ramani" , "Dombivli" ,
                        "Error Resolving", "1511102","Rajdhani","23","bcd",
                        new GeneralCard("xyzz", null,null,null,false),null);
                generalReportUtility.addGeneralReport(generalRep, new AddGeneralReportListener() {
                    @Override
                    public void onCompleteTask(String result) {

                    }
                });
            }
        });

        removeReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GeneralReportUtility generalReportUtility= new GeneralReportUtility();
                GeneralReport generalRep = new GeneralReport("Ramani" , "Dombivli" ,
                        "Error Resolving", "1511102","Rajdhani","23","bcd",
                        new GeneralCard("xyzz", null,null,null,false),null);
                generalReportUtility.removeGeneralReport(generalRep, new RemoveGeneralReportListener() {
                    @Override
                    public void onCompleteTask(String result) {
                        System.out.println("Result: "+result);
                    }
                });

            }
        });
    }
}
