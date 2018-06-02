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
    ArrayList<MaintainenceCardFiles> cards = new ArrayList<MaintainenceCardFiles>();
    ArrayList<MaintainenceCardFiles> reportvalues = new ArrayList<MaintainenceCardFiles>();//fetch and store report values here
    MaintainenceBogeyReportAdapter adapter;
    MaintainenceCardFiles cardfiles;
    RelativeLayout EndCompliance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey_report);

        Date date = new java.util.Date();

        MaintainenceCardFiles m = new MaintainenceCardFiles("",date);//example card
        reportvalues.add(m);

        EndCompliance = (RelativeLayout)findViewById(R.id.endcompliance_button);
        EndCompliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final RecyclerView card = (RecyclerView)findViewById(R.id.card_list);
        adapter = new MaintainenceBogeyReportAdapter(cards,reportvalues,BogeyReportActivity.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        card.setLayoutManager(mlayoutmanager);
        card.setAdapter(adapter);

        for (int i=0;i<1;i++) //Instead of 1 put the size of reportvalues arraylist
        {
            cardfiles = new MaintainenceCardFiles("",date);;
            cards.add(cardfiles);
            adapter.notifyDataSetChanged();
        }
    }
}
