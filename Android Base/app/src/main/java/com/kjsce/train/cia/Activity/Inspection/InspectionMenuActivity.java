package com.kjsce.train.cia.Activity.Inspection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kjsce.train.cia.Activity.LoginActivity;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

public class InspectionMenuActivity extends AppCompatActivity {
    Button createreport,totalinspection,help;
    RelativeLayout logout_button;
    SharedData sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        createreport= (Button) findViewById(R.id.createReport);
        totalinspection= (Button) findViewById(R.id.TotalInspect);
        help= (Button) findViewById(R.id.help);
        logout_button = (RelativeLayout) findViewById(R.id.logout_button);
        sd = new SharedData(getApplicationContext());

        List<Boolean> type_list = new ArrayList<Boolean>();
        for(int i=0;i<8;i++){
            type_list.add(false);
        }
        sd.setTypeList(type_list);

        System.out.println("zzzzz"+sd.getTypeList());

      createreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), InspectionTrainReportActivity.class);
                startActivity(i);
            }
        });
       totalinspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), TotalInspection.class);
                startActivity(i);
            }
        });
       help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a help page
            }
        });

       logout_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new MaterialDialog.Builder(InspectionMenuActivity.this)
                       .title("Confirm")
                       .content("Are you sure you want to Logout?")
                       .positiveText("Yes")
                       .negativeText("No")
                       .onPositive(new MaterialDialog.SingleButtonCallback() {
                           @Override
                           public void onClick(MaterialDialog dialog, DialogAction which) {
                               sd.isLoggedIn(false);
                               sd.clearAll();

                               FirebaseAuth.getInstance().signOut();
                               Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                               startActivity(i);
                           }
                       })
                       .onNegative(new MaterialDialog.SingleButtonCallback() {
                           @Override
                           public void onClick(MaterialDialog dialog, DialogAction which) {
                           }
                       })
                       .show();
           }
       });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}








