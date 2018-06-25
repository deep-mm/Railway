package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.kjsce.train.cia.Adapter.CardDetailsAdapter;
import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    private ImageButton backButton;
    private DetailsAdapter detailsAdapter;
    private ArrayList<String> detailedCards;
    private SharedData sharedData;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initialize();

        final RecyclerView details = (RecyclerView) findViewById(R.id.details);
        detailsAdapter = new DetailsAdapter(detailedCards, Details.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(detailsAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void initialize(){
        backButton = (ImageButton) findViewById(R.id.back_button);
        detailedCards = new ArrayList<String>();
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
