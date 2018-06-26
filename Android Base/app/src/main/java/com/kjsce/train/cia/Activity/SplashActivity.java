package com.kjsce.train.cia.Activity;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.kjsce.train.cia.R;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static int SPLASH_TIME_OUT = 2000;
    private SharedData sharedData;
    private Helper helper;
    private Intent intent;
    private KeyguardManager keyguardManager;
    private static int CODE_AUTHENTICATION_VERIFICATION=241;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initialize();

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
                    .content("You need active internet connection")
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
                            onBackPressed();
                        }
                    })
                    .show();
        }
        
    }

    public void initialize(){
        mAuth = FirebaseAuth.getInstance();
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==CODE_AUTHENTICATION_VERIFICATION)
        {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Failure: Unable to verify user's identity", Toast.LENGTH_SHORT).show();
            finishAffinity();
        }
    }

    public void checkLoggedIn(){
        if(sharedData.isLoggedIn()){
            if(keyguardManager.isKeyguardSecure()) {
                intent = keyguardManager.createConfirmDeviceCredentialIntent("Authentication","Please Authenticate yourself to the app");
                startActivityForResult(intent, CODE_AUTHENTICATION_VERIFICATION);
            }
            else {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
        else{
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
