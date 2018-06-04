package com.kjsce.train.cia.Activity.Maintainence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.kjsce.train.cia.Activity.Inspection.InspectionBogeyReportActivity;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.BogeyReportAdapter;
import com.kjsce.train.cia.Adapter.MaintainenceBogeyReportAdapter;
import com.kjsce.train.cia.Entity.BogeyEntity;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.CardFiles;
import com.kjsce.train.cia.Entity.MaintainenceCardFiles;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BogeyReportActivity extends AppCompatActivity {
    List<DetailedCard> cards = new ArrayList<DetailedCard>();
    ArrayList<CardFiles> reportvalues = new ArrayList<CardFiles>();//fetch and store report values here
    BogeyReportAdapter adapter;
    CardFiles cardfiles;
    RelativeLayout EndCompliance;
    SharedData sd;
    BogeyEntity bogeyEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey_report);
        sd = new SharedData(getApplicationContext());
        Date date = new java.util.Date();

       //String bogey = ;
        bogeyEntity =sd.getBogeyEntityobject();
        cards = bogeyEntity.getDetailedCard();
        sd.setDetailedCards(cards);

        EndCompliance = (RelativeLayout)findViewById(R.id.endcompliance_button);
        EndCompliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ArrayList<String> spinner_list = new ArrayList<String>();
        spinner_list.add("toilets");
        String comment = "aa";
        final RecyclerView card = (RecyclerView)findViewById(R.id.card_list);
        adapter = new BogeyReportAdapter(cards,spinner_list,BogeyReportActivity.this,false,comment);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        card.setLayoutManager(mlayoutmanager);
        card.setAdapter(adapter);

        for (int i=0;i<reportvalues.size();i++) //Instead of 1 put the size of reportvalues arraylist
        {
           // cards.add(detailedCard);
            adapter.notifyDataSetChanged();
        }
    }
}
