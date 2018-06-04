package com.kjsce.train.cia.Activity.Inspection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.MaintainenceBogeyReportAdapter;
import com.kjsce.train.cia.Entity.MaintainenceCardFiles;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Entity.TrainEntity;
import com.kjsce.train.cia.Listeners.GetDetailedReportListListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.Backend.DetailedReportUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TotalInspection extends AppCompatActivity {
    ArrayList<MaintainenceCardFiles> cards = new ArrayList<MaintainenceCardFiles>();
    ArrayList<MaintainenceCardFiles> reportvalues = new ArrayList<MaintainenceCardFiles>();//fetch and store report values here
    MaintainenceBogeyReportAdapter adapter;
    MaintainenceCardFiles cardfiles;
    SharedData sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_inspection);

        sd = new SharedData(getApplicationContext());
        String date ="adsas";
        String train = "123 trainn";

        List<TrainEntity> trainEntities = sd.getTrainEntityList();
        DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
        List<DetailedReport> detailedReports = new ArrayList<DetailedReport>();
        for(int i=0;i<trainEntities.size();i++){
            detailedReportUtility.getDetailedReportList(trainEntities.get(i).getTrainNumber(), new GetDetailedReportListListener() {
                @Override
                public void onCompleteTask(List<DetailedReport> detailedReportList) {
                    detailedReports.addAll(detailedReportList);
                }
            });
        }
        //Get all reports from bhavik and display
        sd = new SharedData(getApplicationContext());
        //System.out.println("train"+sd.getTrain());

        for(int i=0;i<detailedReports.size();i++){

        }
        MaintainenceCardFiles m = new MaintainenceCardFiles(train,date);//example card
        reportvalues.add(m);

        final RecyclerView card = (RecyclerView)findViewById(R.id.card_list);
        adapter = new MaintainenceBogeyReportAdapter(cards,reportvalues, TotalInspection.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        card.setLayoutManager(mlayoutmanager);
        card.setAdapter(adapter);

        for (int i=0;i<reportvalues.size();i++)
        {
            cardfiles = reportvalues.get(i);
            cards.add(cardfiles);
            adapter.notifyDataSetChanged();
        }
    }
}