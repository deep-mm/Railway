package com.kjsce.train.cia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.kjsce.train.cia.Adapter.CardsAdapter;
import com.kjsce.train.cia.Adapter.TrainAdapter;
import com.kjsce.train.cia.Entities.AnalysisEntity;
import com.kjsce.train.cia.Entities.BogeyAnalysisEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.ProblemReferenceEntity;
import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Listener.GetBogeyAnalysisListener;
import com.kjsce.train.cia.Listener.IdListener;
import com.kjsce.train.cia.Listener.OnBogeyListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.AnalysisUtility;
import com.kjsce.train.cia.Utilities.IdUtility;
import com.kjsce.train.cia.Utilities.TrainUtility;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisActivity extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton exportButton, backButton;
    private List<Boolean> statusChecked, typeChecked;
    private List<String> dateList;
    //private TrainUtility trainUtility;
    private List<String> coach_list;
    private List<String> type_list,short_type_list;
    private IdUtility idUtility;
    private List<IndexEntryEntity> indexEntryEntities;
    private List<Integer> totalProblems;
    private List<StackedBarModel> stackedBarModels;
    private Map<String,AnalysisEntity> analysisEntityMap;
    private int x = 0;
    private boolean flag;
    private String coach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        initialize();

        AnalysisUtility analysisUtility = new AnalysisUtility();
        analysisUtility.getBogeyAnalysis(coach, new GetBogeyAnalysisListener() {
            @Override
            public void onCompleteTask(BogeyAnalysisEntity bogeyAnalysisEntity) {
                ArrayList<String> bogeyAnalysisList = bogeyAnalysisEntity.getProblemList();

                for(int i=0;i<type_list.size();i++) {
                    if (typeChecked.get(i) && bogeyAnalysisList.contains(type_list.get(i))) {
                        AnalysisEntity ae = bogeyAnalysisEntity.getProblemAnalysis(type_list.get(i));
                        analysisEntityMap.put(type_list.get(i),ae);
                    }
                    else{
                        continue;
                    }
                }
                display();
            }
        });

    }

    public void display() {
        System.out.println("analysis: done");
        StackedBarChart mStackedBarChart = (StackedBarChart) findViewById(R.id.stackedbarchart);
        StackedBarChart mStackedBarChartSolved = (StackedBarChart) findViewById(R.id.stackedbarchartSolved);

        //All Problems
        int i;
        for(i=0;i<type_list.size();i++){
            if(typeChecked.get(i)) {
                StackedBarModel s = new StackedBarModel(short_type_list.get(i));
                AnalysisEntity ae = analysisEntityMap.get(type_list.get(i));
                float solved;
                float unsolved;
                if(ae==null){
                    solved = 0.0f;
                    unsolved = 0.0f;
                }else{
                    solved = (float) ae.getSolvedProblems();
                    unsolved = (float) ae.getUnsolvedProblems();
                }
                s.addBar(new BarModel(solved, 0xFF63CBB0));
                s.addBar(new BarModel(unsolved, 0xFF56B7F1));
                mStackedBarChart.addBar(s);
                stackedBarModels.add(s);
            }
        }

        mStackedBarChart.startAnimation();

        //Solved Problems
        for(i=0;i<type_list.size();i++){
            if(typeChecked.get(i)) {
                StackedBarModel s = new StackedBarModel(short_type_list.get(i));
                AnalysisEntity ae = analysisEntityMap.get(type_list.get(i));
                float low,medium,high;
                if(ae==null){
                    low = 0.0f;
                    medium = 0.0f;
                    high = 0.0f;
                }else{
                    low = (float) ae.getLowProblems();
                    medium = (float) ae.getMediumProblems();
                    high = (float) ae.getCriticalProblems();
                }
                s.addBar(new BarModel(low, 0xFF000000));
                s.addBar(new BarModel(medium, 0xFFFFFB60));
                s.addBar(new BarModel(high, 0xFFFFA8B2));
                mStackedBarChartSolved.addBar(s);
                stackedBarModels.add(s);
            }
        }

        mStackedBarChartSolved.startAnimation();
    }

    public void initialize() {
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        //exportButton = (ImageButton) findViewById(R.id.button_export);
        backButton = (ImageButton) findViewById(R.id.back_button);
        statusChecked = sharedData.getStatusCheckedList();
        typeChecked = sharedData.getTypeCheckedList();
        dateList = sharedData.getDateList();
        indexEntryEntities = new ArrayList<IndexEntryEntity>();
        totalProblems = new ArrayList<Integer>();
        stackedBarModels = new ArrayList<StackedBarModel>();
        analysisEntityMap = new HashMap<>();
        coach = sharedData.getBogie();

        type_list = new ArrayList<String>();
        type_list.add("Toilets");
        type_list.add("Coach Interior Amenities");
        type_list.add("Coach Interior Cleanliness");
        type_list.add("Coach Exterior");
        type_list.add("Undergear");
        type_list.add("Electricals");
        type_list.add("Windows");
        type_list.add("Others");

        short_type_list = new ArrayList<String>();
        short_type_list.add("Toilets");
        short_type_list.add("Coach Amenities");
        short_type_list.add("Coach Cleanliness");
        short_type_list.add("Coach Exterior");
        short_type_list.add("Undergear");
        short_type_list.add("Electricals");
        short_type_list.add("Windows");
        short_type_list.add("Others");
    }
}
