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
import com.kjsce.train.cia.Listener.OnBogeyListChangeListener;
import com.kjsce.train.cia.Listener.OnTrainListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.TrainListUtility;
import com.kjsce.train.cia.Utilities.TrainUtility;

import java.util.List;

public class TrainActivity extends AppCompatActivity {
    private int i=0;

    private TrainUtility trainUtility = new TrainUtility("8511100", new OnBogeyListChangeListener() {
        @Override
        public void onDataChanged(TrainEntity newTrainEntity) {
            Toast.makeText(getApplicationContext(), newTrainEntity.toString(), Toast.LENGTH_LONG).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

    }

    public void addTrainB(View view){
       /* TrainUtility trainUtility = new TrainUtility();
        trainUtility.addTrain("8511100", new AddTrainListener() {
            @Override
            public void onCompleteTask(String result) {
                Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
            }
        });*/
    }

    public void addBogeyB(View view){

        trainUtility.addBogey("800");
    }

    public void getBogeyListB(View view){
        /*TrainUtility trainUtility = new TrainUtility();
        trainUtility.getBogeyList("8511100", new GetBogeyListListener() {
            @Override
            public void onCompleteTask(TrainEntity trainEntity) {
                Toast.makeText(getApplicationContext(), trainEntity.toString(), Toast.LENGTH_LONG).show();
            }
        });*/
    }

    public void deleteBogeyB(View view){
        trainUtility.deleteBogey("8002");


    }

    public void getTrainListB(View view){
         TrainListUtility trainListUtility = new TrainListUtility(new OnTrainListChangeListener() {
            @Override
            public void OnDataChenged(List<String> newTrainList) {
                Toast.makeText(getApplicationContext(),"Train List   "+newTrainList.toString(),Toast.LENGTH_LONG).show();
            }
        });
        /*
        TrainUtility trainUtility = new TrainUtility();
        trainUtility.getTrainList(new GetTrainListListener() {
            @Override
            public void onCompleteTask(List<String> trainList) {
                Toast.makeText(getApplicationContext(),trainList.toString(),Toast.LENGTH_LONG).show();
            }
        });*/

    }

}
