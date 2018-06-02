package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.Inspection.InspectionMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.MaintainenceMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.TrainSearchActivity;
import com.kjsce.train.cia.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    public SharedData sd;
    public Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sd = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());

        sd.setType("Inspection"); //set default right now, change when connected to database
        if(true) {
//!helper.isNetworkConnected()
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i;
                    if (!sd.isLoggedIn()) {
                        i = new Intent(getApplicationContext(), LoginActivity.class);
                    } else {
                        if (sd.getType().equalsIgnoreCase("Inspection")) {
                            i = new Intent(getApplicationContext(), InspectionMenuActivity.class);
                        } else {
                            i = new Intent(getApplicationContext(), TrainSearchActivity.class);
                        }
                    }
                    startActivity(i);
                    finish();
                }

            }, SPLASH_TIME_OUT);
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
}
