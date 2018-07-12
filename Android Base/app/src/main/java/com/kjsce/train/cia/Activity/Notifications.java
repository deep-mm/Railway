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
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.kjsce.train.cia.Adapter.CardsAdapter;
import com.kjsce.train.cia.Adapter.NotificationsAdapter;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Notifications extends AppCompatActivity {

    private MaterialDialog materialDialog;
    private ImageButton backButton, clearButton;
    private SharedData sharedData;
    private Helper helper;
    public NotificationsAdapter notificationsAdapter;
    public RecyclerView details;
    private List<UserNotificationEntity> detailedCards;
    private Intent intent;
    private RelativeLayout empty_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        initialize();

        detailedCards = sharedData.getNotificationEntityList();
        if(detailedCards.size()==0)
            empty_list.setVisibility(View.VISIBLE);
        else
            empty_list.setVisibility(View.INVISIBLE);

        Collections.reverse(detailedCards);
        details = (RecyclerView) findViewById(R.id.details);
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
                                BackgroundService.notificationUtility.clearNotification();
                                notificationsAdapter.notifyDataSetChanged();
                                if(detailedCards.size()==0)
                                    empty_list.setVisibility(View.VISIBLE);
                                else
                                    empty_list.setVisibility(View.INVISIBLE);
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                            }
                        })
                        .canceledOnTouchOutside(false)
                        .cancelable(false)
                        .show();
            }
        });
    }

    public void initialize(){
        backButton = (ImageButton) findViewById(R.id.back_button);
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        clearButton = (ImageButton) findViewById(R.id.clear_button);
        detailedCards = new ArrayList<UserNotificationEntity>();
        empty_list = (RelativeLayout) findViewById(R.id.empty_page);
        empty_list.setVisibility(View.GONE);
    }

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(Notifications.this)
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

    @Override
    public void onResume() {
        super.onResume();
        checkInternetConnection();
    }

    @Override
    public void onBackPressed(){
        intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void checkInternetConnection(){
        if(!helper.isNetworkConnected()){
            new MaterialDialog.Builder(Notifications.this)
                    .title("No Internet Connection")
                    .content("You need active internet connection")
                    .positiveText("Retry")
                    .negativeText("Exit")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            intent = new Intent(getApplicationContext(),Notifications.class);
                            startActivity(intent);
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            finishAffinity();
                        }
                    })
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
                    .show();
        }
    }


}
