package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.CardDetailsAdapter;
import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Details extends AppCompatActivity {

    private ImageButton backButton;
    private DetailsAdapter detailsAdapter;
    private ArrayList<String> detailedCards;
    private SharedData sharedData;
    private Helper helper;
    private MaterialDialog materialDialog;
    private CardEntity cardEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initialize();

        cardEntity = sharedData.getCardEntity();
        detailedCards.add("Train-Number: "+cardEntity.getTrainNumber());
        detailedCards.add("Date: "+getDate(cardEntity.getDateTime()));
        detailedCards.add("Place: "+cardEntity.getPlaceOfInspection());
        detailedCards.add("Sender: "+cardEntity.getSender());

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

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(Details.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    public void onProgressStop(){
        materialDialog.hide();
    }

    public String getDate(String date){
        SimpleDateFormat spf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("dd/MM/yyyy | hh:mm");
        date = spf.format(newDate);
        return date;
    }
}
