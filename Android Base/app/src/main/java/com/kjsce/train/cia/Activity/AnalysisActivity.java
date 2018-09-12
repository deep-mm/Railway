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
    private int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        initialize();

                coach_list = sharedData.getCoachList();
                System.out.println("indexEntryEntities: "+x+" id = "+coach_list);
                for (int i = 0; i < coach_list.size(); i++) {
                    String coach = coach_list.get(i);
                    System.out.println("indexEntryEntities: " + x + " id = " + coach);
                    for (int j = 0; j < type_list.size(); j++) {
                        String type = type_list.get(i);
                        idUtility = new IdUtility(new ProblemReferenceEntity(coach, type), new IdListener() {
                            @Override
                            public void onIdListChanged(ArrayList<IndexEntryEntity> idList) {
                                System.out.println("indexEntryEntities: " + x + " id = " + idList);
                                indexEntryEntities.addAll(idList);
                                x++;
                                idUtility.detachListner();
                                if (x == 8) {
                                    display();
                                }
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

        StackedBarChart mStackedBarChart = (StackedBarChart) findViewById(R.id.stackedbarchart);

        StackedBarModel s1 = new StackedBarModel("12.4");

        s1.addBar(new BarModel(2.3f, 0xFF63CBB0));
        s1.addBar(new BarModel(2.3f, 0xFF56B7F1));
        s1.addBar(new BarModel(2.3f, 0xFFCDA67F));

        StackedBarModel s2 = new StackedBarModel("13.4");
        s2.addBar(new BarModel(1.1f, 0xFF63CBB0));
        s2.addBar(new BarModel(2.7f, 0xFF56B7F1));
        s2.addBar(new BarModel(0.7f, 0xFFCDA67F));

        StackedBarModel s3 = new StackedBarModel("14.4");

        s3.addBar(new BarModel(2.3f, 0xFF63CBB0));
        s3.addBar(new BarModel(2.f, 0xFF56B7F1));
        s3.addBar(new BarModel(3.3f, 0xFFCDA67F));

        StackedBarModel s4 = new StackedBarModel("15.4");
        s4.addBar(new BarModel(1.f, 0xFF63CBB0));
        s4.addBar(new BarModel(4.2f, 0xFF56B7F1));
        s4.addBar(new BarModel(2.1f, 0xFFCDA67F));

        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);
        mStackedBarChart.addBar(s4);

        mStackedBarChart.startAnimation();
    }

    public void display(){
        System.out.println("analysis: done");
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        exportButton = (ImageButton) findViewById(R.id.button_export);
        backButton = (ImageButton) findViewById(R.id.back_button);
        statusChecked = sharedData.getStatusCheckedList();
        typeChecked = sharedData.getTypeCheckedList();
        dateList = sharedData.getDateList();

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
