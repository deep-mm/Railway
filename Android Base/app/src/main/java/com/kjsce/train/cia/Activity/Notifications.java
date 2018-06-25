package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.kjsce.train.cia.Adapter.CardsAdapter;
import com.kjsce.train.cia.Adapter.NotificationsAdapter;
import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private MaterialDialog materialDialog;
    private ImageButton backButton, clearButton;
    private SharedData sharedData;
    private Helper helper;
    private NotificationsAdapter notificationsAdapter;
    private ArrayList<DetailedCard> detailedCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        initialize();

        DetailedCard detailedCard = new DetailedCard();
        detailedCard.setType("Hello");
        detailedCard.setSubmittedBy("23/11/18");
        detailedCards.add(detailedCard);

        final RecyclerView details = (RecyclerView) findViewById(R.id.details);
        notificationsAdapter = new NotificationsAdapter(detailedCards, Notifications.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(notificationsAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(Notifications.this)
                        .title("Confirm")
                        .content("Are you sure you want to clear all?")
                        .positiveText("Yes")
                        .negativeText("No")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                detailedCards.clear();
                                //TODO: delete all notifications from database
                                notificationsAdapter.notifyDataSetChanged();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                            }
                        })
                        .show();
            }
        });
    }

    public void initialize(){
        backButton = (ImageButton) findViewById(R.id.back_button);
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        clearButton = (ImageButton) findViewById(R.id.clear_button);
        detailedCards = new ArrayList<DetailedCard>();
    }

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(Notifications.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .show();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void onProgressStop(){
        materialDialog.hide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
