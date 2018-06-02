package com.example.amey.loginfirebase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Entity.TrainEntity;
import com.example.amey.loginfirebase.Entity.UserEntity;
import com.example.amey.loginfirebase.Listener.AddDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetTrainListener;
import com.example.amey.loginfirebase.Listener.GetUserListener;
import com.example.amey.loginfirebase.Listener.RemoveDetailedReportListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.DetailedReportUtility;
import com.example.amey.loginfirebase.Utilities.Backend.TrainUtility;
import com.example.amey.loginfirebase.Utilities.Backend.UserUtility;
import com.google.firebase.auth.FirebaseAuth;

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


                detailedReportUtility.getDetailedReport("1511092","231097", new GetDetailedReportListener() {
                    @Override
                    public void onCompleteTask(DetailedReport detailedReport) {

                    }
                });
            }
        });

        add_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DetailedReport detailedReport = new DetailedReport();

                UserUtility userUtility = new UserUtility();


                detailedReport.setPlaceOfInspection("LTT");
                detailedReport.setTypeOfInspection("General");
                detailedReport.setTrainNumber("1511100");
                detailedReport.setTrainName("Rajdhani");
                detailedReport.setDateTime("datetime daalde");

                TrainUtility trainUtility = new TrainUtility();
                trainUtility.getTrain("1511100", new GetTrainListener() {
                    @Override
                    public void onCompleteTask(TrainEntity trainEntity) {
                        detailedReport.setPlaceOfInspection(trainEntity.getManufacturer());
                    }
                });

                BogeyEntity bogeyEntity = new BogeyEntity();

                bogeyEntity.setType("Sleeper");






                DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
                DetailedReport detailedRep = new DetailedReport("Ramani", "Dombivli",
                        "1511092", "Rajdhani", "231097", "Amey",  null, null);
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
                        "1511092", "Rajdhani", "231097", "Amey",  null, null);
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