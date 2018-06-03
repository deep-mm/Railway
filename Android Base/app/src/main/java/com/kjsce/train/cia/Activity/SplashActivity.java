package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kjsce.train.cia.Activity.Inspection.InspectionMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.TrainSearchActivity;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.R;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static int SPLASH_TIME_OUT = 2000;
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

        if(helper.isNetworkConnected()) {

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    checkLoggedIn();
                }

            }, SPLASH_TIME_OUT);
        }
        else{
            new MaterialDialog.Builder(this)
                    .title("No Internet Connection")
                    .content("You need active internet connection to login")
                    .positiveText("Retry")
                    .negativeText("Exit")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            Intent intent = new Intent(getApplicationContext(),SplashActivity.class);
                            startActivity(intent);
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
            UserEntity userEntity = sd.getUserEntity();
            if(userEntity.getType().equals("inspection")){
                i = new Intent(getApplicationContext(), InspectionMenuActivity.class);
                startActivity(i);
                finish();
            }
                else{
                i = new Intent(getApplicationContext(), TrainSearchActivity.class);
                startActivity(i);
                finish();
            }
        }
        else{
            i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
    }
}
