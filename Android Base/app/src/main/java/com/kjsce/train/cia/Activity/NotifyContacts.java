package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

public class NotifyContacts extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton sendButton, backButton;
    private CheckBoxAdapter checkBoxAdapter;
    private List<UserEntity> userEntities;
    private List<Boolean> checkedList;
    private MaterialDialog materialDialog;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_contacts);

        initialize();

        userEntities = SplashActivity.userUtility.getUserList();

        for(int i=0;i<userEntities.size();i++){
            checkedList.add(false);
        }
        sharedData.setCheckedList(checkedList);

        final RecyclerView details = (RecyclerView) findViewById(R.id.details);
        checkBoxAdapter = new CheckBoxAdapter(userEntities, NotifyContacts.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(checkBoxAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkedList = sharedData.getCheckedList();
                String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
                String train = sharedData.getTrain(), sender = sharedData.getUserEntity().getName();
                for(int i=0;i<userEntities.size();i++){
                    if(checkedList.get(i)) {
                        System.out.println("UserEntity: "+train);
                        UserNotificationEntity userNotificationEntity = new UserNotificationEntity(train, timeStamp, sender);
                        BackgroundService.notificationUtility.sendNotification(userEntities.get(i).getMobileNumber(), userNotificationEntity);
                    }
                }
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        sendButton = (ImageButton) findViewById(R.id.send_button);
        backButton = (ImageButton) findViewById(R.id.back_button);
        userEntities = new ArrayList<UserEntity>();
        checkedList = new ArrayList<Boolean>();
    }

    public void onProgressStart(){

        materialDialog = new MaterialDialog.Builder(NotifyContacts.this)
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
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkInternetConnection();
    }

    public void checkInternetConnection(){
        if(!helper.isNetworkConnected()){
            new MaterialDialog.Builder(NotifyContacts.this)
                    .title("No Internet Connection")
                    .content("You need active internet connection")
                    .positiveText("Retry")
                    .negativeText("Exit")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            intent = new Intent(getApplicationContext(),AddUser.class);
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
