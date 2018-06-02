package com.kjsce.train.cia.Activity.Inspection;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.Adapter.BogeyReportAdapter;
import com.kjsce.train.cia.Entity.CardFiles;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

public class InspectionBogeyReportActivity extends AppCompatActivity {
    ArrayList<CardFiles> cards = new ArrayList<CardFiles>();
    ArrayList<String> spinner_list = new ArrayList<String>();
    TextView next,previous;
    Button add;
    ImageButton back_button;
    BogeyReportAdapter adapter;
    String[] permissions={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    TextView bogey_number,train_number;
    String previous_coach="",next_coach="";
    SharedData sd;
    List<String> coach_list = new ArrayList<String>();
    int position;
    LinearLayout detailed,general;

    CardFiles cardfiles;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpection_bogey_report);
        setResult(RESULT_OK);
        previous = (TextView)findViewById(R.id.previous);
        bogey_number = (TextView)findViewById(R.id.bogey_number);
        train_number = (TextView)findViewById(R.id.train_number);
        sd = new SharedData(getApplicationContext());
        back_button = (ImageButton) findViewById(R.id.back_button);
        detailed = (LinearLayout) findViewById(R.id.bottom_bar);
        general = (LinearLayout) findViewById(R.id.bottom_bar_1);

        coach_list = sd.getCoachList();

        if(getIntent().hasExtra("type")){
            String type = getIntent().getExtras().getString("type");
            if(type.equalsIgnoreCase("detailed")){
                detailed.setVisibility(View.VISIBLE);
                general.setVisibility(View.GONE);
            }
            else{
                detailed.setVisibility(View.GONE);
                general.setVisibility(View.VISIBLE);
                String train[] = sd.getTrain().split("\\s+");
                bogey_number.setText(train[1]);
                train_number.setText(train[0]);
            }
        }
        if(getIntent().hasExtra("coach")){
            bogey_number.setText(getIntent().getExtras().getString("coach"));
            train_number.setText(sd.getTrain());
        }

        if(getIntent().hasExtra("coach_index")){
            position = getIntent().getExtras().getInt("coach_index");
            if(position>0) {
                previous_coach = coach_list.get(position-1);
            }
            if(position!=coach_list.size()-1) {
                next_coach = coach_list.get(position+1);
            }
        }

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(InspectionBogeyReportActivity.this)
                        .title("Confirm")
                        .content("Are you sure you want to end inspection?")
                        .positiveText("Yes")
                        .negativeText("No")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                Intent intent = new Intent(getApplicationContext(),InspectionMenuActivity.class);
                                startActivity(intent);
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                //
                            }
                        })
                        .show();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!previous_coach.equalsIgnoreCase("")) {
                    Intent i = new Intent(getApplicationContext(), InspectionBogeyReportActivity.class);
                    i.putExtra("coach", previous_coach);
                    i.putExtra("coach_index",position-1);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"This is the first coach",Toast.LENGTH_SHORT).show();
                }
            }
        });

        next = (TextView)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!next_coach.equalsIgnoreCase("")) {
                    Intent i = new Intent(getApplicationContext(), InspectionBogeyReportActivity.class);
                    i.putExtra("coach", next_coach);
                    i.putExtra("coach_index",position+1);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"This is the last coach",Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        spinner_list.add("Toilets");
        spinner_list.add("Coach Interior Amenities");
        spinner_list.add("Coach Interior Cleanliness");
        spinner_list.add("Coach Exterior");
        spinner_list.add("Undergear");
        spinner_list.add("Electricals");
        spinner_list.add("Windows");
        final RecyclerView card = (RecyclerView)findViewById(R.id.card_list);
        adapter = new BogeyReportAdapter(cards,spinner_list,InspectionBogeyReportActivity.this,true,"");
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        card.setLayoutManager(mlayoutmanager);
        card.setAdapter(adapter);

        card.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardfiles = new CardFiles("Seat no:","Comment:","Type:");
                cards.add(cardfiles);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), InspectionBogeyActivity.class);
        startActivity(i);
    }



}
