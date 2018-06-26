package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.kjsce.train.cia.Adapter.CardDetailsAdapter;
import com.kjsce.train.cia.Adapter.CardsAdapter;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

import co.dift.ui.SwipeToAction;

public class CardsActivity extends AppCompatActivity {

    private SwipeToAction swipeToAction;
    private ImageButton backButton, addButton;
    private TextView bogieNumber;
    private SharedData sharedData;
    private Helper helper;
    private CardsAdapter cardsAdapter;
    private ArrayList<DetailedCard> detailedCards;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        initialize();

        bogieNumber.setText("Coach: "+sharedData.getBogie());

        DetailedCard detailedCard = new DetailedCard();
        detailedCard.setType("Cleanliness");
        detailedCard.setSubmittedBy("11/1/18 23:19:09");
        detailedCards.add(detailedCard);

        final RecyclerView details = (RecyclerView) findViewById(R.id.details);
        cardsAdapter = new CardsAdapter(detailedCards, CardsActivity.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(cardsAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),CardDetails.class);
                intent.putExtra("flag",true);
                startActivity(intent);
            }
        });

        swipeToAction = new SwipeToAction(details, new SwipeToAction.SwipeListener<DetailedCard>() {
            @Override
            public boolean swipeLeft(DetailedCard itemData) {
                //TODO: set status of card to solved and remove from recycler view list
                return false;
            }

            @Override
            public boolean swipeRight(DetailedCard itemData) {
                return false;
            }

            @Override
            public void onClick(DetailedCard itemData) {
                //TODO: set itemData in sharedData to access in CardDetails
                intent = new Intent(getApplicationContext(),CardDetails.class);
                intent.putExtra("flag",false);
                intent.putExtra("subType","Cleanliness");
                startActivity(intent);
            }

            @Override
            public void onLongClick(DetailedCard itemData) {

            }

        });
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        backButton = (ImageButton) findViewById(R.id.back_button);
        detailedCards = new ArrayList<DetailedCard>();
        addButton = (ImageButton) findViewById(R.id.add_button);
        bogieNumber = (TextView) findViewById(R.id.bogey_number);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
