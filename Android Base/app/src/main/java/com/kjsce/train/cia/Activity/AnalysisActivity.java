package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.kjsce.train.cia.Adapter.CardsAdapter;
import com.kjsce.train.cia.Adapter.TrainAdapter;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.ProblemReferenceEntity;
import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Listener.IdListener;
import com.kjsce.train.cia.Listener.OnBogeyListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.IdUtility;
import com.kjsce.train.cia.Utilities.TrainUtility;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton exportButton, backButton;
    private List<Boolean> statusChecked, typeChecked;
    private List<String> dateList;
    //private TrainUtility trainUtility;
    private List<String> coach_list;
    private List<String> type_list;
    private IdUtility idUtility;
    private List<IndexEntryEntity> indexEntryEntities;
    private List<Integer> totalProblems;
    private List<StackedBarModel> stackedBarModels;
    private int x = 0;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        initialize();

        int i;
        coach_list = sharedData.getCoachList();
        System.out.println("indexEntryEntities: " + x + " id = " + coach_list);
        for (i = 0; i < coach_list.size(); i++) {
            String coach = coach_list.get(i);
            System.out.println("indexEntryEntities: " + x + " id = " + coach);
            flag = true;
            x = 0;
            for (int j = 0; j < type_list.size(); j++) {
                if (typeChecked.get(j)) {
                    int check = j;
                    String type = type_list.get(j);
                    if(flag) {
                        flag = false;
                        idUtility = new IdUtility(new ProblemReferenceEntity(coach, type), new IdListener() {
                            @Override
                            public void onIdListChanged(ArrayList<IndexEntryEntity> idList) {
                                System.out.println("indexEntryEntities: " + x + " id = " + idList);
                                for (int k = 0; k < idList.size(); k++) {
                                    indexEntryEntities.add(idList.get(k));
                                }

                                System.out.println("indexEntryEntitities: " + indexEntryEntities);
                                //indexEntryEntities.addAll(idList);
                                idUtility.detachListner();
                                x++;
                                if (x == 8) {
                                    display();
                                }
                                flag = true;
                            }

                            @Override
                            public void onIdAdded(IndexEntryEntity indexEntryEntity) {

                            }

                            @Override
                            public void onIdRemoved(IndexEntryEntity indexEntryEntity) {

                            }

                            @Override
                            public void onIdChanged(IndexEntryEntity indexEntryEntity) {

                            }
                        });
                    }
                }
                else{
                    x++;
                    if (x == 8) {
                        display();
                    }
                }
            }
        }


    }

    public float getTotalSolved(String type){

        int i;
        int count=0;
        int flag=0;
        System.out.println("analysis: "+indexEntryEntities);
        for(i=0;i<indexEntryEntities.size();i++){
            IndexEntryEntity indexEntryEntity = indexEntryEntities.get(i);
            System.out.println("analysis: "+indexEntryEntity.getSubtype()+" to: "+type+" status: "+indexEntryEntity.isProblemStatus());
            if(indexEntryEntity.getSubtype().equalsIgnoreCase(type) && indexEntryEntity.isProblemStatus()) {
                count++;
                flag = 1;
            }
        }

        return (float)count;
    }

    public float getTotalUnsolved(String type){

        int i;
        int count=0;
        int flag=0;
        System.out.println("analysis: "+indexEntryEntities+" totalProblems= "+totalProblems);
        for(i=0;i<indexEntryEntities.size();i++){
            IndexEntryEntity indexEntryEntity = indexEntryEntities.get(i);
            System.out.println("analysis: "+indexEntryEntity.getSubtype()+" to: "+type+" status: "+indexEntryEntity.isProblemStatus());
            if(indexEntryEntity.getSubtype().equalsIgnoreCase(type) && (!indexEntryEntity.isProblemStatus())) {
                count++;
                flag = 1;
            }
        }

        return (float)count;
    }
    public void display() {
        System.out.println("analysis: done");
        StackedBarChart mStackedBarChart = (StackedBarChart) findViewById(R.id.stackedbarchart);
        StackedBarChart mStackedBarChartSolved = (StackedBarChart) findViewById(R.id.stackedbarchartSolved);
        StackedBarChart mStackedBarChartUnsolved = (StackedBarChart) findViewById(R.id.stackedbarchartUnsolved);

        //All Problems
        int i;
        for(i=0;i<type_list.size();i++){
            if(typeChecked.get(i)) {
                StackedBarModel s = new StackedBarModel(type_list.get(i));
                float solved = getTotalSolved(type_list.get(i));
                float unsolved = getTotalUnsolved(type_list.get(i));
                System.out.println("analysis: " + solved);
                System.out.println("analysis: " + unsolved);
                s.addBar(new BarModel(solved, 0xFF63CBB0));
                s.addBar(new BarModel(unsolved, 0xFF56B7F1));
                mStackedBarChart.addBar(s);
                stackedBarModels.add(s);
            }
        }

        mStackedBarChart.startAnimation();
    }

    public void initialize() {
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        exportButton = (ImageButton) findViewById(R.id.button_export);
        backButton = (ImageButton) findViewById(R.id.back_button);
        statusChecked = sharedData.getStatusCheckedList();
        typeChecked = sharedData.getTypeCheckedList();
        dateList = sharedData.getDateList();
        indexEntryEntities = new ArrayList<IndexEntryEntity>();
        totalProblems = new ArrayList<Integer>();
        stackedBarModels = new ArrayList<StackedBarModel>();

        type_list = new ArrayList<String>();
        type_list.add("Toilets");
        type_list.add("Coach Interior Amenities");
        type_list.add("Coach Interior Cleanliness");
        type_list.add("Coach Exterior");
        type_list.add("Undergear");
        type_list.add("Electricals");
        type_list.add("Windows");
        type_list.add("Others");
    }
}
