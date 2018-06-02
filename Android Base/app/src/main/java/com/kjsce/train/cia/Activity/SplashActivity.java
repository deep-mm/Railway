package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kjsce.train.cia.Activity.Inspection.InspectionMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.MaintainenceMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.TrainSearchActivity;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.Listeners.GetUserListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.Backend.UserUtility;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static int SPLASH_TIME_OUT = 10000;
    public SharedData sd;
    public Helper helper;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        sd = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());

        sd.setType("Inspection"); //set default right now, change when connected to database
        if(true) {
//!helper.isNetworkConnected()
            checkLoggedIn();
            /*new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    checkLoggedIn();
                    *//*Intent i;
                    if (!sd.isLoggedIn()) {
                        i = new Intent(getApplicationContext(), LoginActivity.class);
                    } else {
                        if (sd.getType().equalsIgnoreCase("Inspection")) {
                            i = new Intent(getApplicationContext(), InspectionMenuActivity.class);
                        } else {
                            i = new Intent(getApplicationContext(), TrainSearchActivity.class);
                        }
                    }
                    startActivity(i);*//*
                    if(i!= null)
                        startActivity(i);
                    finish();
                }

            }, SPLASH_TIME_OUT);*/
        }
        else{
            new MaterialDialog.Builder(this)
                    .title("No Internet Connection")
                    .content("You need active internet connection to login")
                    .positiveText("Exit")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            finishAffinity();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            finishAffinity();
                        }
                    })
                    .show();
        }
        
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

    public void checkLoggedIn(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            System.out.println("USer is there");
            checkType(currentUser);
        }
        else{
            i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
    }

    public void checkType(FirebaseUser user){

        UserUtility userUtility = new UserUtility();
        userUtility.getUser(user.getUid(), new GetUserListener() {
            @Override
            public void onCompleteTask(UserEntity userEntity) {

                if(userEntity.getDesignation().equals("Inspection")){
                    i = new Intent(getApplicationContext(), InspectionMenuActivity.class);
                    startActivity(i);
                }
                else{
                    System.out.println("type is there");
                    i = new Intent(getApplicationContext(), TrainSearchActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
