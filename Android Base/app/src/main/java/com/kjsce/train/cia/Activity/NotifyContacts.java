package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

public class NotifyContacts extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton sendButton, backButton;
    private CheckBoxAdapter checkBoxAdapter;
    private ArrayList<UserEntity> userEntities;
    private MaterialDialog materialDialog;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_contacts);

        initialize();

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Deep Mehta");
        userEntity.setDesignation("CRPF");
        userEntities.add(userEntity);

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
                //TODO: Update the notifications database
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
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

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(NotifyContacts.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .show();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void onProgressStop(){
        materialDialog.hide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
