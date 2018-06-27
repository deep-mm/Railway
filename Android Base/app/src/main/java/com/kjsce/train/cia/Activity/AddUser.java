package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.dd.processbutton.iml.ActionProcessButton;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.UserUtility;

import java.util.ArrayList;

public class AddUser extends AppCompatActivity {

    private ImageButton backButton;
    private SharedData sharedData;
    private Helper helper;
    private TextView name,mobile,designation;
    private ImageView train;
    private ActionProcessButton submit_button;
    private String name_text,mobile_text,designation_text;
    private Intent intent;
    private UserUtility userUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initialize();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submit_button.setMode(ActionProcessButton.Mode.ENDLESS);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_text = name.getText().toString();
                mobile_text = "+91"+mobile.getText().toString();
                designation_text = designation.getText().toString();

                if(name_text.isEmpty() || mobile_text.isEmpty() || designation_text.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields cannot be left empty",Toast.LENGTH_LONG).show();
                    submit_button.setProgress(-1);
                }
                else {
                    submit_button.setProgress(1);
                    UserEntity userEntity = new UserEntity(name_text,designation_text,mobile_text);
                    userUtility.addUser(userEntity);
                    submit_button.setProgress(100);
                    new MaterialDialog.Builder(AddUser.this)
                            .title("Add New User")
                            .content("Do you want to add another user?")
                            .positiveText("Yes")
                            .negativeText("No")
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
                                    intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
            }
        });
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        backButton = (ImageButton) findViewById(R.id.back_button);
        name = (TextView) findViewById(R.id.user_name_text);
        mobile = (TextView) findViewById(R.id.mobile_text);
        designation = (TextView) findViewById(R.id.designation_text);
        train = (ImageView) findViewById(R.id.train_gif);
        submit_button = (ActionProcessButton) findViewById(R.id.submit_button);
        userUtility = new UserUtility();
    }

    @Override
    public void onBackPressed(){
        intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkInternetConnection();
    }

    public void checkInternetConnection(){
        if(!helper.isNetworkConnected()){
            new MaterialDialog.Builder(AddUser.this)
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
                    .show();
        }
    }
}
