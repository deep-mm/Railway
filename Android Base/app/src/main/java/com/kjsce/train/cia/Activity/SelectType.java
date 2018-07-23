package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.TypeCardAdapter;
import com.kjsce.train.cia.Entities.BogeyAnalysisEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Listener.GetBogeyAnalysisListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.AnalysisUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectType extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton backButton;
    private ArrayList<String> type_list;
    private TypeCardAdapter typeCardAdapter;
    private Intent intent;
    private List<Boolean> statusList, typeList;
    private List<String> dateList;
    private ArrayList<IndexEntryEntity> indexEntryEntities;

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

        //Testing area
        /*try {
            AnalysisUtility analysisUtility = new AnalysisUtility();
            analysisUtility.getBogeyAnalysis(sharedData.getBogie(), new GetBogeyAnalysisListener() {
                @Override
                public void onCompleteTask(BogeyAnalysisEntity bogeyAnalysisEntity) {
                    System.out.println("Testing 123: bogey check \n" + bogeyAnalysisEntity);
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        backButton = (ImageButton) findViewById(R.id.back_button);
        type_list = new ArrayList<String>();

        statusList = Arrays.asList(false,true);
        typeList = Arrays.asList(false,false,false,false,false,false,false,false);
        dateList = Arrays.asList("dd/MM/yyyy","dd/MM/yyyy");
        indexEntryEntities = new ArrayList<IndexEntryEntity>();

        sharedData.setDateList(dateList);
        sharedData.setTypeCheckedList(typeList);
        sharedData.setStatusCheckedList(statusList);
        sharedData.setIndexEntryEntities(indexEntryEntities);
    }

    @Override
    public void onBackPressed(){
        new MaterialDialog.Builder(SelectType.this)
                .title("Finish")
                .content("Are you sure you want to exit from coach inspection of "+sharedData.getBogie()+" ?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        intent = new Intent(getApplicationContext(),CoachSearch.class);
                        startActivity(intent);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                    }
                })
                .canceledOnTouchOutside(false)
                .show();
    }
}

