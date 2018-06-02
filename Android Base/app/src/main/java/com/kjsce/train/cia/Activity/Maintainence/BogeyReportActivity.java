package com.kjsce.train.cia.Activity.Maintainence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.kjsce.train.cia.Activity.Inspection.InspectionBogeyReportActivity;
import com.kjsce.train.cia.Adapter.BogeyReportAdapter;
import com.kjsce.train.cia.Adapter.MaintainenceBogeyReportAdapter;
import com.kjsce.train.cia.Entity.CardFiles;
import com.kjsce.train.cia.Entity.MaintainenceCardFiles;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.Date;

public class BogeyReportActivity extends AppCompatActivity {
    ArrayList<CardFiles> cards = new ArrayList<CardFiles>();
    ArrayList<CardFiles> reportvalues = new ArrayList<CardFiles>();//fetch and store report values here
    BogeyReportAdapter adapter;
    CardFiles cardfiles;
    RelativeLayout EndCompliance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey_report);

        Date date = new java.util.Date();

        cardfiles = new CardFiles("Seat no:","Comment:","Type:");
        cards.add(cardfiles);
        //reportvalues.add();

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
        adapter = new BogeyReportAdapter(cards,spinner_list,BogeyReportActivity.this,true,comment);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        card.setLayoutManager(mlayoutmanager);
        card.setAdapter(adapter);

        for (int i=0;i<reportvalues.size();i++) //Instead of 1 put the size of reportvalues arraylist
        {
            cardfiles = new CardFiles("Seat no:","Comment:","Type:");
            cards.add(cardfiles);
            adapter.notifyDataSetChanged();
        }
    }
}
