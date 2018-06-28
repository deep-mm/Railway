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
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Listener.GetUserListener;
import com.kjsce.train.cia.Listener.UserAuthListener;
import com.kjsce.train.cia.Utilities.UserUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private Boolean success;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private int RC_SIGN_IN = 1000;
    private List<String> users;
    private List<Boolean> firstTime;
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
                onProgressStart();
                SplashActivity.userUtility.getUser(user.getPhoneNumber(), new GetUserListener() {
                    @Override
                    public void onCompleteTask(UserEntity userEntity) {
                        if (userEntity != null) {
                            sharedData.isLoggedIn(true);
                            sharedData.isUserVerified(true);
                            sharedData.setUserEntity(userEntity);
                            sharedData.setFirstTime(firstTime);
                            onProgressStop();
                            Intent intent1 = new Intent(getApplicationContext(),BackgroundService.class);
                            startService(intent1);
                            intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            logout();
                        }
                    }
                });
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
                .positiveText("OK")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        sharedData.clearAll();
                        FirebaseAuth.getInstance().signOut();
                        finishAffinity();
                    }
                })
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        helper = new Helper(getApplicationContext());
        users = new ArrayList<String>();
        firstTime = Arrays.asList(true,true,true);
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
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
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
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
            .show();
    }

    public void onProgressStop(){
        materialDialog.hide();
    }

}


