package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.Inspection.InspectionMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.MaintainenceMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.TrainSearchActivity;
import com.kjsce.train.cia.R;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    ImageButton submit;
    String get_username,get_password;
    SharedData sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Validate the username and password for null and other validations
                get_username = username.getText().toString();
                get_password = password.getText().toString();

                if (get_username.equalsIgnoreCase("") || get_password.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be left empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (get_username.equalsIgnoreCase("test") && get_password.equalsIgnoreCase("123456")) {
                        sd.isLoggedIn(true);
                        Intent i;
                        if (sd.getType().equalsIgnoreCase("Inspection")) {
                            i = new Intent(getApplicationContext(), InspectionMenuActivity.class);
                        } else {
                            i = new Intent(getApplicationContext(), TrainSearchActivity.class);
                        }
                        startActivity(i);
                    } else {
                        new MaterialDialog.Builder(LoginActivity.this)
                                .title("Incorrect")
                                .content("Invalid Credentials")
                                .positiveText("OK")
                                .negativeText("EXIT")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(MaterialDialog dialog, DialogAction which) {
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
        });
    }

    public void initialize(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        submit = (ImageButton) findViewById(R.id.submit);
        sd = new SharedData(getApplicationContext());
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }


}
