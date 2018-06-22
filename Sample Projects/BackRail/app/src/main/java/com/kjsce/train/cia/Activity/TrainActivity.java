package com.kjsce.train.cia.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Listener.AddBogeyListener;
import com.kjsce.train.cia.Listener.AddTrainListener;
import com.kjsce.train.cia.Listener.DeleteBogeyListener;
import com.kjsce.train.cia.Listener.GetBogeyListListener;
import com.kjsce.train.cia.Listener.GetTrainListListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.TrainUtility;

import java.util.List;

public class TrainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

    }

    public void addTrainB(View view){
        TrainUtility trainUtility = new TrainUtility();
        trainUtility.addTrain("8511100", new AddTrainListener() {
            @Override
            public void onCompleteTask(String result) {
                Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addBogeyB(View view){
        TrainUtility trainUtility = new TrainUtility();
        trainUtility.addBogey("805", "8511100", new AddBogeyListener() {
            @Override
            public void onCompleteTask(String result) {
                Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getBogeyListB(View view){
        TrainUtility trainUtility = new TrainUtility();
        trainUtility.getBogeyList("8511100", new GetBogeyListListener() {
            @Override
            public void onCompleteTask(TrainEntity trainEntity) {
                Toast.makeText(getApplicationContext(), trainEntity.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteBogeyB(View view){
        TrainUtility trainUtility = new TrainUtility();
        trainUtility.deleteBogey("804", "8511100", new DeleteBogeyListener() {
            @Override
            public void onCompleteTask(String result) {
                Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getTrainListB(View view){
        TrainUtility trainUtility = new TrainUtility();
        trainUtility.getTrainList(new GetTrainListListener() {
            @Override
            public void onCompleteTask(List<String> trainList) {
                Toast.makeText(getApplicationContext(),trainList.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
