package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

public class NotifyContacts extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton sendButton, backButton;
    CheckBoxAdapter checkBoxAdapter;
    ArrayList<UserEntity> userEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_contacts);

        initialize();

        final RecyclerView details = (RecyclerView) findViewById(R.id.details);
        checkBoxAdapter = new CheckBoxAdapter(userEntities, NotifyContacts.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(checkBoxAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update the notifications database
            }
        });
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        sendButton = (ImageButton) findViewById(R.id.send_button);
        backButton = (ImageButton) findViewById(R.id.back_button);
        userEntities = new ArrayList<UserEntity>();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
