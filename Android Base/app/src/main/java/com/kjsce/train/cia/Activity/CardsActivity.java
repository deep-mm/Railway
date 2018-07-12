package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.CardDetailsAdapter;
import com.kjsce.train.cia.Adapter.CardsAdapter;
import com.kjsce.train.cia.Entities.IdEntity;
import com.kjsce.train.cia.Entities.IdReferenceEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.ProblemReferenceEntity;
import com.kjsce.train.cia.Listener.IdListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.IdUtility;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class CardsActivity extends AppCompatActivity {

    private SwipeToAction swipeToAction;
    private ImageButton backButton, addButton, filterButton;
    private TextView bogieNumber;
    private SharedData sharedData;
    private Helper helper;
    private CardsAdapter cardsAdapter;
    private ArrayList<IndexEntryEntity> indexEntryEntities, allIndexEntryEntitities;
    private Intent intent;
    private IdUtility idUtility;
    private RecyclerView details;
    private List<Boolean> firstTime, statusList;
    private List<String> dateList;
    private MaterialDialog materialDialog;
    private RelativeLayout empty_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        initialize();

        bogieNumber.setText("Coach: "+sharedData.getBogie());

        details = (RecyclerView) findViewById(R.id.details);
        cardsAdapter = new CardsAdapter(indexEntryEntities, CardsActivity.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(cardsAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),CardDetails.class);
                intent.putExtra("flag",true);
                startActivity(intent);
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FilterActivity.class);
                intent.putExtra("from","CardsActivity");
                startActivity(intent);
            }
        });

        swipeToAction = new SwipeToAction(details, new SwipeToAction.SwipeListener<IndexEntryEntity>() {
            @Override
            public boolean swipeLeft(IndexEntryEntity itemData) {
                if(!itemData.isProblemStatus()) {
                    new MaterialDialog.Builder(CardsActivity.this)
                            .title("Confirm")
                            .content("Are you sure, issue resolved?")
                            .positiveText("Yes")
                            .negativeText("No")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    idUtility.changeProblemStatus(new IdReferenceEntity(sharedData.getBogie(), sharedData.getType(), itemData.getId()), true);
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

                    return true;
                }
                return true;
            }

            @Override
            public boolean swipeRight(IndexEntryEntity itemData) {
                return false;
            }

            @Override
            public void onClick(IndexEntryEntity itemData) {
                //TODO: set itemData in sharedData to access in CardDetails
                System.out.println("itemData: "+itemData);
                intent = new Intent(getApplicationContext(),CardDetails.class);
                intent.putExtra("flag",false);
                intent.putExtra("subType",itemData.getSubtype());
                intent.putExtra("id",itemData.getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(IndexEntryEntity itemData) {

            }

        });
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        backButton = (ImageButton) findViewById(R.id.back_button);
        indexEntryEntities = new ArrayList<IndexEntryEntity>();
        allIndexEntryEntitities = new ArrayList<IndexEntryEntity>();
        addButton = (ImageButton) findViewById(R.id.add_button);
        filterButton = (ImageButton) findViewById(R.id.filter_button);
        bogieNumber = (TextView) findViewById(R.id.bogey_number);
        empty_list = (RelativeLayout) findViewById(R.id.empty_page);
        empty_list.setVisibility(View.GONE);

        if(helper.isNetworkConnected()){
            onProgressStart();
        }
        idUtility = new IdUtility(new ProblemReferenceEntity(sharedData.getBogie(), sharedData.getType()), new IdListener() {
            @Override
            public void onIdListChanged(ArrayList<IndexEntryEntity> idList) {
                indexEntryEntities = idList;
                allIndexEntryEntitities = indexEntryEntities;
                System.out.println("indexEntryEntities: "+indexEntryEntities.size());
                for(int i=0;i<indexEntryEntities.size();i++){
                    if(statusList.get(0) && statusList.get(1)){
                        //Remove nothing
                    }
                    else if(statusList.get(0)){
                        if(indexEntryEntities.get(i).isProblemStatus()==false) {
                            indexEntryEntities.remove(i);
                            i--;
                        }
                    }
                    else if(statusList.get(1)){
                        if(indexEntryEntities.get(i).isProblemStatus()==true) {
                            indexEntryEntities.remove(i);
                            i--;
                        }
                    }
                    else{
                        //Remove nothing
                    }

                }
                cardsAdapter = new CardsAdapter(indexEntryEntities, CardsActivity.this);
                details.setAdapter(cardsAdapter);
                if(helper.isNetworkConnected()){
                    onProgressStop();
                }
                if(indexEntryEntities.size()==0)
                    empty_list.setVisibility(View.VISIBLE);
                else
                    empty_list.setVisibility(View.INVISIBLE);
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

    @Override
    public void onResume() {
        super.onResume();
        checkInternetConnection();
        firstTime = sharedData.getFirstTime();
        if(firstTime.get(2)) {
            sequence();
            firstTime.set(2, false);
            sharedData.setFirstTime(firstTime);
        }

        statusList = sharedData.getStatusCheckedList();
        dateList = sharedData.getDateList();

        indexEntryEntities = allIndexEntryEntitities;
        for(int i=0;i<allIndexEntryEntitities.size();i++){
            if(statusList.get(0) && statusList.get(1)){
                //Remove nothing
            }
            else if(statusList.get(0)){
                if(indexEntryEntities.get(i).isProblemStatus()==false) {
                    indexEntryEntities.remove(i);
                    i--;
                }
            }
            else if(statusList.get(1)){
                if(indexEntryEntities.get(i).isProblemStatus()==true) {
                    indexEntryEntities.remove(i);
                    i--;
                }
            }
            else{
                //Remove nothing
            }

        }
        cardsAdapter = new CardsAdapter(indexEntryEntities, CardsActivity.this);
        details.setAdapter(cardsAdapter);
        if(indexEntryEntities.size()==0)
            empty_list.setVisibility(View.VISIBLE);
        else
            empty_list.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause(){
        super.onPause();
        idUtility.detachListner();
    }

    @Override
    public void onBackPressed(){
        idUtility.detachListner();
        intent = new Intent(getApplicationContext(),SelectType.class);
        startActivity(intent);
    }

    public void sequence() {
        new MaterialTapTargetPrompt.Builder(CardsActivity.this)
                .setTarget(findViewById(R.id.add_button))
                .setPrimaryText("Add New Card")
                .setSecondaryText("Click this button to add a new sub-type card")
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
            new MaterialDialog.Builder(CardsActivity.this)
                    .title("No Internet Connection")
                    .content("You need active internet connection")
                    .positiveText("Retry")
                    .negativeText("Exit")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            intent = new Intent(getApplicationContext(),CardsActivity.class);
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

    public void onProgressStart() {
        materialDialog = new MaterialDialog.Builder(CardsActivity.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    public void onProgressStop() {
        materialDialog.hide();
    }
}
