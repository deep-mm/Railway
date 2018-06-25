package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.TypeCardAdapter;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

public class SelectType extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton backButton;
    private ArrayList<String> type_list;
    private TypeCardAdapter typeCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        initialize();

        type_list.add("Toilets");
        type_list.add("Coach Interior Amenities");
        type_list.add("Coach Interior Cleanliness");
        type_list.add("Coach Exterior");
        type_list.add("Undergear");
        type_list.add("Electricals");
        type_list.add("Windows");
        type_list.add("Others");

        final RecyclerView details = (RecyclerView) findViewById(R.id.details);
        typeCardAdapter = new TypeCardAdapter(type_list, SelectType.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(typeCardAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        backButton = (ImageButton) findViewById(R.id.back_button);
        type_list = new ArrayList<String>();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}

