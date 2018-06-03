package com.example.amey.loginfirebase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Card.DetailedCard;
import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Entity.TrainEntity;
import com.example.amey.loginfirebase.Entity.UserEntity;
import com.example.amey.loginfirebase.Listener.AddDetailedCardListener;
import com.example.amey.loginfirebase.Listener.AddDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetLatestDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetTrainListener;
import com.example.amey.loginfirebase.Listener.GetUserListener;
import com.example.amey.loginfirebase.Listener.RemoveDetailedReportListener;
import com.example.amey.loginfirebase.Listener.SetLatestDetailedReportListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.DetailedCardUtility;
import com.example.amey.loginfirebase.Utilities.Backend.DetailedReportUtility;
import com.example.amey.loginfirebase.Utilities.Backend.LatestUtility;
import com.example.amey.loginfirebase.Utilities.Backend.TrainUtility;
import com.example.amey.loginfirebase.Utilities.Backend.UserUtility;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DetailedReportActivity extends AppCompatActivity {
    private Button get_det, add_det, rem_det;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailreport);
        get_det=(Button)findViewById(R.id.get_det);
        add_det=(Button)findViewById(R.id.add_det);
        rem_det=(Button)findViewById(R.id.rem_det);


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
                DetailedReportUtility detailedReportUtility=new DetailedReportUtility();
                UserUtility userUtility = new UserUtility();


                detailedReport.setPlaceOfInspection("LTT");
                detailedReport.setTypeOfInspection("Detailed");
                detailedReport.setTrainNumber("1511092");
                detailedReport.setTrainName("SAMY");
                detailedReport.setDateTime("231097");
                detailedReport.setManufacturer("Parakh");
                BogeyEntity bogeyEntity = new BogeyEntity();
                bogeyEntity.setBogeyNumber("123");
                bogeyEntity.setType("Sleeper");
                detailedReport.addBogeyEntity(bogeyEntity);
                detailedReportUtility.addDetailedReport(detailedReport, new AddDetailedReportListener() {
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
    public void addCard(View view)
    {
        final DetailedCard detailedCard = new DetailedCard("Parakh","Toilet",null,null,"not OK",false,null);
        DetailedReportUtility detailedReportUtility=new DetailedReportUtility();
        detailedReportUtility.getDetailedReport("1511092", "231097", new GetDetailedReportListener() {
            @Override
            public void onCompleteTask(DetailedReport detailedReport) {
                DetailedCardUtility detailedCardUtility = new DetailedCardUtility();
                detailedCardUtility.addDetailedCard(detailedReport, detailedCard, "123",new AddDetailedCardListener() {
                    @Override
                    public void onCompleteTask(boolean result) {

                    }
                });
            }

        });
    }

    public void getDetailedL(View view){
        DetailedReportUtility detailedReportUtility=new DetailedReportUtility();
        detailedReportUtility.getDetailedReportList(new GetDetailedReportListListener() {
            @Override
            public void onCompleteTask(List<DetailedReport> detailedReportList) {
                Toast.makeText(getApplicationContext(),detailedReportList.toString(),Toast.LENGTH_SHORT).show();

                System.out.println("dddd"+detailedReportList);
            }
        });



    }

    public void setLatestR(View view){
        DetailedReport detailedRep = new DetailedReport("Ramani", "Dombivli",
                "1511092", "Rajdhani", "231097", "Amey",  null, null);
        LatestUtility latestUtility = new LatestUtility();
        latestUtility.setLatestDetailedReport(detailedRep, new SetLatestDetailedReportListener() {
            @Override
            public void onCompleteTask(String result) {

            }
        });

    }

    public void getLatestR(View view){
        LatestUtility latestUtility = new LatestUtility();
        latestUtility.getLatestDetailedReport("1511092", new GetLatestDetailedReportListener() {
            @Override
            public void onCompleteTask(String timestamp) {
                System.out.println("ttt"+timestamp);
            }
        });
    }


}