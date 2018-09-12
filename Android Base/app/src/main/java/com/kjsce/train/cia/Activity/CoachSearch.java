package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.TrainAdapter;
import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Listener.OnBogeyListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.TrainUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class CoachSearch extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private MaterialDialog materialDialog;
    private ImageButton backButton, addButton, submitButton, analysisButton;
    private RecyclerView details;
    private TrainAdapter trainAdapter;
    private List<String> coach_list, data1, allCoaches;
    private SharedData sharedData;
    private Helper helper;
    private SearchView searchView;
    private String searchBoxValue, coachNumber;
    private Intent intent;
    public static TrainUtility trainUtility;
    private List<Boolean> firstTime;
    private RelativeLayout empty_list;
    private List<Boolean> statusList,typeList;
    private List<String> dateList;
    private int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogie_search);

        details = (RecyclerView) findViewById(R.id.details);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setItemAnimator(new DefaultItemAnimator());

        initialize();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        allCoaches = coach_list;
        System.out.println("SEtting: "+coach_list);
        searchView.setOnQueryTextListener(this);
        trainAdapter = new TrainAdapter(coach_list, CoachSearch.this, "coach");
        System.out.println("SEtting: "+coach_list);
        details.setAdapter(trainAdapter);
        
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCoach();
            }
        });

        analysisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(x==1) {
                    if (helper.isInternetConnected()) {
                        Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
                        intent.putExtra("from", "Analysis");
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Active Internet Connection Required for Analysis", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allCoaches.size()==0){
                    Toast.makeText(getApplicationContext(),"Atleast one coach needed to generate the report",Toast.LENGTH_SHORT).show();
                }
                else {
                    new MaterialDialog.Builder(CoachSearch.this)
                            .title("Confirm")
                            .content("Do you want to notify users of the train report?")
                            .positiveText("Yes")
                            .negativeText("No")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    intent = new Intent(getApplicationContext(), NotifyContacts.class);
                                    intent.putExtra("from","notification");
                                    startActivity(intent);
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    trainUtility.detachListner();
                                    intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .canceledOnTouchOutside(false)
                            .cancelable(false)
                            .show();
                }
            }
        });
    }

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(CoachSearch.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .show();
    }

    public void onProgressStop(){
        materialDialog.hide();
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        backButton = (ImageButton) findViewById(R.id.back_button);
        addButton = (ImageButton) findViewById(R.id.button_add);
        submitButton = (ImageButton) findViewById(R.id.done_button);
        searchView = (SearchView) findViewById(R.id.search_bar);
        analysisButton = (ImageButton) findViewById(R.id.button_analysis);
        coach_list = new ArrayList<String>();
        data1 = new ArrayList<String>();
        allCoaches = new ArrayList<String>();
        searchBoxValue = "";
        empty_list = (RelativeLayout) findViewById(R.id.empty_page);
        empty_list.setVisibility(View.GONE);

        /*if(helper.isInternetConnected())
            onProgressStart();*/
        trainUtility = new TrainUtility(sharedData.getTrain(), new OnBogeyListChangeListener() {
            @Override
            public void onDataChanged(TrainEntity newTrainEntity) {
                coach_list = newTrainEntity.getBogeyList();
                System.out.println("SEtting: "+coach_list);
                sharedData.setCoachList(coach_list);
                coach_list.add("General");
                allCoaches = coach_list;
                trainAdapter = new TrainAdapter(coach_list, CoachSearch.this, "coach");
                System.out.println("SEtting: "+coach_list);
                details.setAdapter(trainAdapter);
                x=1;
                //empty_coaches();
                /*if(helper.isInternetConnected())
                    onProgressStop();*/
            }
        });

        statusList = Arrays.asList(false,true);
        typeList = Arrays.asList(false,false,false,false,false,false,false,false);
        dateList = Arrays.asList("dd/MM/yyyy","dd/MM/yyyy");

        sharedData.setDateList(dateList);
        sharedData.setTypeCheckedList(typeList);
        sharedData.setStatusCheckedList(statusList);
    }

    public void addNewCoach() {

        new MaterialDialog.Builder(this)
                .title("New Coach")
                .content("Enter coach number")
                .inputRangeRes(2, 40, R.color.red_error)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Enter Text Here", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        coachNumber = input.toString();
                        trainUtility.addBogey(coachNumber);
                        sharedData.setBogie(coachNumber);
                        intent = new Intent(getApplicationContext(),SelectType.class);
                        startActivity(intent);
                    }
                })
                .canceledOnTouchOutside(false)
                .show();
    }

    @Override
    public void onBackPressed(){
        new MaterialDialog.Builder(CoachSearch.this)
                .title("Finish")
                .content("Are you sure you want to exit from train inspection of "+sharedData.getTrain()+" ?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        trainUtility.detachListner();
                        intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                    }
                })
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkInternetConnection();
        firstTime = sharedData.getFirstTime();
        if(firstTime.get(1)) {
            sequence();
            firstTime.set(1, false);
            sharedData.setFirstTime(firstTime);
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        searchBoxValue = newText;
        System.out.println("search: " + searchBoxValue);
        newText = newText.toLowerCase(Locale.getDefault());
        data1.clear();
        int i;

        System.out.println("SEtting: "+allCoaches);
        for (i = 0; i < allCoaches.size(); i++) {
            if (allCoaches.get(i).toLowerCase().contains(newText))
                data1.add(allCoaches.get(i));
        }

        coach_list = data1;
        System.out.println("train_list: " + data1);
        trainAdapter = new TrainAdapter(data1, CoachSearch.this, "coach");
        System.out.println("SEtting: "+data1);
        details.setAdapter(trainAdapter);
        trainAdapter.notifyDataSetChanged();

        if (newText.length() == 0) {
            data1.clear();
            coach_list = allCoaches;
            trainAdapter = new TrainAdapter(coach_list, CoachSearch.this, "coach");
            System.out.println("SEtting: "+coach_list);
            details.setAdapter(trainAdapter);
            trainAdapter.notifyDataSetChanged();
        }

        empty_coaches();

        return false;

    }

    public void empty_coaches(){
        if(coach_list.size()==0)
            empty_list.setVisibility(View.VISIBLE);
        else
            empty_list.setVisibility(View.GONE);
    }

    public void sequence() {
        new MaterialTapTargetPrompt.Builder(CoachSearch.this)
                .setTarget(findViewById(R.id.button_add))
                .setPrimaryText("Add New Coach")
                .setSecondaryText("If none of the coaches appear in your search, click on this and add the coach you want to inspect")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {
                            // User has pressed the prompt target
                        }
                    }
                })
                .show();
    }

    public void checkInternetConnection(){
        if(!helper.isNetworkConnected()){
            new MaterialDialog.Builder(CoachSearch.this)
                    .title("No Internet Connection")
                    .content("You need active internet connection")
                    .positiveText("Retry")
                    .negativeText("Exit")
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
                            finishAffinity();
                        }
                    })
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
                    .show();
        }
    }
}
