package com.kjsce.train.cia.Activity.Inspection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.MaintainenceBogeyReportAdapter;
import com.kjsce.train.cia.Entity.MaintainenceCardFiles;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.Date;

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

        String date ="adsas";
        String train = "123 trainn";
        sd = new SharedData(getApplicationContext());
        //System.out.println("train"+sd.getTrain());
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