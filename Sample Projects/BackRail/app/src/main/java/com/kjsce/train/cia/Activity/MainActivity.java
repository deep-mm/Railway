package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.NotificationUtility;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toUser(View view){
        intent = new Intent(this,UserActivity.class);
        startActivity(intent);
    }

    public void toTrain(View view){
        intent = new Intent(this,TrainActivity.class);
        startActivity(intent);
    }

    public void toBogey(View view){
        intent = new Intent(this,BogeyActivity.class);
        startActivity(intent);
    }

    public void toNotification(View view)
    {
        intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

}
