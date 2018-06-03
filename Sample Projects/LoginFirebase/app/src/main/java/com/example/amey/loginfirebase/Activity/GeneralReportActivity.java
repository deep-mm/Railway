package com.example.amey.loginfirebase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Entity.TrainEntity;
import com.example.amey.loginfirebase.Entity.UserEntity;
import com.example.amey.loginfirebase.Listener.AddAudioListener;
import com.example.amey.loginfirebase.Listener.AddGeneralCardListener;
import com.example.amey.loginfirebase.Listener.AddGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetTrainListener;
import com.example.amey.loginfirebase.Listener.GetUserListener;
import com.example.amey.loginfirebase.Listener.RemoveGeneralReportListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.AudioUtility;
import com.example.amey.loginfirebase.Utilities.Backend.GeneralCardUtility;
import com.example.amey.loginfirebase.Utilities.Backend.GeneralReportUtility;
import com.example.amey.loginfirebase.Utilities.Backend.TrainUtility;
import com.example.amey.loginfirebase.Utilities.Backend.UserUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class GeneralReportActivity extends AppCompatActivity {
    private Button getReport,addReport,removeReport;
    public void addCard(View view)
    {
        final GeneralCard generalCard=new GeneralCard("Parakh","Toilet",null,null,"Not OK",false);

        GeneralReportUtility generalReportUtility=new GeneralReportUtility();
        generalReportUtility.getGeneralReport("1511092", "231097", new GetGeneralReportListener() {
            @Override
            public void onCompleteTask(GeneralReport generalReport) {

                GeneralCardUtility generalCardUtility=new GeneralCardUtility();
                generalCardUtility.addGeneralCard(generalReport, generalCard, new AddGeneralCardListener() {
                    @Override
                    public void onCompleteTask(boolean result) {

                    }
                });
            }
        });
    }
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

                generalReportUtility.getGeneralReport("1511092" ,"231097", new GetGeneralReportListener() {
                    @Override
                    public void onCompleteTask(GeneralReport generalReport) {

                    }
                });
            }
        });

        addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final GeneralReport generalReport = new GeneralReport();

                /*generalReport.setPlaceOfInspection("LTT");
                generalReport.setTypeOfInspection("General");
                generalReport.setTrainNumber("1511092");
                generalReport.setTrainName("Rajdhani");
                generalReport.setDateTime("231097");

                TrainUtility trainUtility = new TrainUtility();
                trainUtility.getTrain("1511100", new GetTrainListener() {
                    @Override
                    public void onCompleteTask(TrainEntity trainEntity) {
                        generalReport.setManufacturer(trainEntity.getManufacturer());
                    }
                });
*/
                GeneralReport generalRep = new GeneralReport("Dadar","General","1511092","SAMY","231097","tp",null,null);

                GeneralReportUtility generalReportUtility= new GeneralReportUtility();
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
                GeneralReport generalRep = new GeneralReport("Dadar","General","1511092","SAMY","231097","tp",null,null);

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
