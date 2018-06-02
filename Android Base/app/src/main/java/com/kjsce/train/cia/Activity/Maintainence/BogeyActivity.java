package com.kjsce.train.cia.Activity.Maintainence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kjsce.train.cia.Adapter.MaintainenceGridAdapter;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.Arrays;

public class BogeyActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> coach;
    TextView number,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey);



        coach = new ArrayList<>(Arrays.asList("S1", "S2 ", "S3", "S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3","S1", "S2 ", "S3"));
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        String trainNo =  getIntent().getExtras().getString("TrainNo");
        String trainName =  getIntent().getExtras().getString("TrainName");

        number = (TextView)findViewById(R.id.txtNum);
        name = (TextView)findViewById(R.id.trainname);

        number.setText(trainNo);
        name.setText(trainName);

        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MaintainenceGridAdapter(BogeyActivity.this, coach);
        mRecyclerView.setAdapter(mAdapter);
    }
}
