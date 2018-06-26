package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kjsce.train.cia.Entity.UserEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private Boolean success;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private int RC_SIGN_IN = 1000;
    private ArrayList<String> users;
    private Intent intent;
    private UserEntity userEntity;
    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                        startActivityForResult(
                                AuthUI.getInstance()
                                        .createSignInIntentBuilder()
                                        .setIsSmartLockEnabled(false)
                                        .setAvailableProviders(
                                                Arrays.asList(
                                                        new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                                                ))
                                        .build(),
                                RC_SIGN_IN);
                    }
                }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(users.contains(user.getPhoneNumber())){
                    sharedData.isLoggedIn(true);
                    //TODO: Get user entity by mobile number and store in sharedData
                    //sharedData.setUserEntity(userEntity);
                    startService(new Intent(this, BackgroundService.class));
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else{
                    logout();
                }
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                finishAffinity();
            }
        }
    }

    public void logout(){
        new MaterialDialog.Builder(LoginActivity.this)
                .title("Unverified User")
                .content("You are not authorized to use this app\nPlease contact the customer support for help")
                .negativeText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        finishAffinity();
                    }
                })
                .show();
        sharedData.clearAll();
        FirebaseAuth.getInstance().signOut();
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        helper = new Helper(getApplicationContext());
        users = new ArrayList<String>();
        users.add("+919999999999");
        //TODO: Get list of all user mobile numbers from database
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!helper.isNetworkConnected()){
            new MaterialDialog.Builder(LoginActivity.this)
                    .title("No Internet Connection")
                    .content("You need active internet connection")
                    .positiveText("Retry")
                    .negativeText("Exit")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            intent = new Intent(getApplicationContext(),LoginActivity.class);
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
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(LoginActivity.this)
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


