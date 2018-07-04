package com.kjsce.train.cia.Activity;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.Listener.OnNewNotificationAddedListener;
import com.kjsce.train.cia.Listener.OnNotificationListChangeListener;
import com.kjsce.train.cia.Listener.OnTrainListChangeListener;
import com.kjsce.train.cia.Listener.OnUserListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.NotificationUtility;
import com.kjsce.train.cia.Utilities.TrainListUtility;
import com.kjsce.train.cia.Utilities.TrainUtility;
import com.kjsce.train.cia.Utilities.UserUtility;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static int SPLASH_TIME_OUT = 2000;
    private SharedData sharedData;
    private Helper helper;
    private Intent intent;
    private KeyguardManager keyguardManager;
    private static int CODE_AUTHENTICATION_VERIFICATION=241;
    public static NotificationUtility notificationUtility;
    public static UserUtility userUtility;

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
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
                    .show();
        }
        
    }

    public void initialize(){
        mAuth = FirebaseAuth.getInstance();
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        userUtility = new UserUtility();
        //helper.clearData();
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
            if(sharedData.isLoggedIn()) {
                Intent intent1 = new Intent(getApplicationContext(), BackgroundService.class);
                startService(intent1);
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("from", "mainActivity");
                startActivity(intent);
            }
        }
        else
        {
            //Toast.makeText(this, "Failure: Unable to verify user's identity", Toast.LENGTH_SHORT).show();
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
                if(sharedData.isLoggedIn()) {
                    Intent intent1 = new Intent(getApplicationContext(), BackgroundService.class);
                    startService(intent1);
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("from", "mainActivity");
                    startActivity(intent);
                }
            }
        }
        else{
            intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
