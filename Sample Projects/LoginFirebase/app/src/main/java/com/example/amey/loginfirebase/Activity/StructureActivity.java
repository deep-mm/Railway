package com.example.amey.loginfirebase.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.Entity.Analysis.DetailedTrainAnalysis;
import com.example.amey.loginfirebase.Entity.Analysis.GeneralTrainAnalysis;
import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Entity.UserEntity;
import com.example.amey.loginfirebase.Listener.AddDetailedReportListener;
import com.example.amey.loginfirebase.Listener.AddGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListener;
import com.example.amey.loginfirebase.Listener.RemoveDetailedReportListener;
import com.example.amey.loginfirebase.Listener.RemoveGeneralReportListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.DetailedReportUtility;
import com.example.amey.loginfirebase.Utilities.Backend.GeneralReportUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StructureActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserIdDatabaseReference;
    private FirebaseAuth mAuth;
    private Button auth;
    private Button General;
    public Intent intent;
    boolean authFlag,genRepoFlag,detRepoFlag,trainFlag=false;
    Map<String, UserEntity> users = new HashMap<String, UserEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure);

        mAuth = FirebaseAuth.getInstance();

        Button auth = (Button) findViewById(R.id.auth);
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),AuthActivity.class);
                startActivity(intent);
            }
        });

    }


        public void toAuth(View view)
        {
                intent = new Intent(this,AuthActivity.class);
                startActivity(intent);
        }

        public void toGenRep(View view)
        {
            intent = new Intent(this , GeneralReportActivity.class);
            startActivity(intent);
        }

        public void toDetRep (View view)
        {
            intent = new Intent(this , DetailedReportActivity.class);
            startActivity(intent);
        }

        public void toTrain (View view)
        {
            intent = new Intent(this , TrainActivity.class);
            startActivity(intent);
        }
    }