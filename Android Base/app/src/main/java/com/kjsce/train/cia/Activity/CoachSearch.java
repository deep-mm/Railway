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
import java.util.List;
import java.util.Locale;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class CoachSearch extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private MaterialDialog materialDialog;
    private ImageButton backButton, addButton, submitButton;
    private RecyclerView details;
    private TrainAdapter trainAdapter;
    private List<String> coach_list, data1, allCoaches;
    private SharedData sharedData;
    private Helper helper;
    private SearchView searchView;
    private String searchBoxValue, coachNumber;
    private Intent intent;
    private TrainUtility trainUtility;
    private List<Boolean> firstTime;

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
        searchView.setOnQueryTextListener(this);

        trainAdapter = new TrainAdapter(coach_list, CoachSearch.this, "coach");
        details.setAdapter(trainAdapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCoach();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(CoachSearch.this)
                        .title("Confirm")
                        .content("Are you sure you want to submit?")
                        .positiveText("Yes")
                        .negativeText("No")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                intent = new Intent(getApplicationContext(),NotifyContacts.class);
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
        });
    }

    public void onProgressStart(){
        materialDialog = new MaterialDialog.Builder(CoachSearch.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void onProgressStop(){
        materialDialog.hide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        backButton = (ImageButton) findViewById(R.id.back_button);
        addButton = (ImageButton) findViewById(R.id.button_add);
        submitButton = (ImageButton) findViewById(R.id.done_button);
        searchView = (SearchView) findViewById(R.id.search_bar);
        coach_list = new ArrayList<String>();
        data1 = new ArrayList<String>();
        allCoaches = new ArrayList<String>();
        searchBoxValue = "";

        trainUtility = new TrainUtility(sharedData.getTrain(), new OnBogeyListChangeListener() {
            @Override
            public void onDataChanged(TrainEntity newTrainEntity) {
                coach_list = newTrainEntity.getBogeyList();
                trainAdapter = new TrainAdapter(coach_list, CoachSearch.this, "coach");
                details.setAdapter(trainAdapter);
            }
        });
    }

    public void addNewCoach() {

        new MaterialDialog.Builder(this)
                .title("New Coach")
                .content("Enter coach number")
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
                        intent = new Intent(getApplicationContext(),NotifyContacts.class);
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

        for (i = 0; i < allCoaches.size(); i++) {
            if (allCoaches.get(i).toLowerCase().contains(newText))
                data1.add(allCoaches.get(i));
        }

        coach_list = data1;
        System.out.println("train_list: " + data1);
        trainAdapter = new TrainAdapter(data1, CoachSearch.this, "coach");
        details.setAdapter(trainAdapter);
        trainAdapter.notifyDataSetChanged();

        if (newText.length() == 0) {
            data1.clear();
            coach_list = allCoaches;
            trainAdapter = new TrainAdapter(coach_list, CoachSearch.this, "coach");
            details.setAdapter(trainAdapter);
            trainAdapter.notifyDataSetChanged();
        }

        return false;

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
                            intent = new Intent(getApplicationContext(),AddUser.class);
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
