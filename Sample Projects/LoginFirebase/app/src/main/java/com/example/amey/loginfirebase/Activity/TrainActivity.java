package com.example.amey.loginfirebase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.Entity.TrainEntity;
import com.example.amey.loginfirebase.Listener.AddTrainListener;
import com.example.amey.loginfirebase.Listener.GetTrainListener;
import com.example.amey.loginfirebase.Listener.RemoveTrainListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.TrainUtility;

public class TrainActivity extends AppCompatActivity{
    private Button get_train,add_train,rem_train;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_activity);
        get_train = (Button)findViewById(R.id.get_train);
        add_train = (Button)findViewById(R.id.add_train);
        rem_train = (Button)findViewById(R.id.remove_train);
        get_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainUtility trainUtility = new TrainUtility();
                TrainEntity trainEntity = new TrainEntity("123456","Rajdhani","Delhi","Mumbai","nagpur","12","6","LeyLand","obhs","2000","MainTain",10);
                trainUtility.getTrain(trainEntity, new GetTrainListener() {
                    @Override
                    public void onCompleteTask(TrainEntity trainEntity) {

                    }
                });

            }
        });

        add_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainUtility trainUtility = new TrainUtility();
                TrainEntity trainEntity = new TrainEntity("123456","Rajdhani","Delhi","Mumbai","nagpur","12","6","LeyLand","obhs","2000","MainTain",10);
                trainUtility.addTrain(trainEntity, new AddTrainListener() {
                    @Override
                    public void onCompleteTask(String result) {

                    }
                });
            }
        });

        rem_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainUtility trainUtility = new TrainUtility();
                TrainEntity trainEntity = new TrainEntity("123456","Rajdhani","Delhi","Mumbai","nagpur","12","6","LeyLand","obhs","2000","MainTain",10);
                trainUtility.removeTrain(trainEntity, new RemoveTrainListener() {
                    @Override
                    public void onCompleteTask(String result) {

                    }
                });
            }
        });
    }
}
