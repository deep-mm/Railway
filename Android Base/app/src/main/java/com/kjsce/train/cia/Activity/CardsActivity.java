package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.kjsce.train.cia.Adapter.CardDetailsAdapter;
import com.kjsce.train.cia.Adapter.CardsAdapter;
import com.kjsce.train.cia.Entities.IdEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.ProblemReferenceEntity;
import com.kjsce.train.cia.Listener.IdListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.IdUtility;

import java.util.ArrayList;

import co.dift.ui.SwipeToAction;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class CardsActivity extends AppCompatActivity {

    private SwipeToAction swipeToAction;
    private ImageButton backButton, addButton;
    private TextView bogieNumber;
    private SharedData sharedData;
    private Helper helper;
    private CardsAdapter cardsAdapter;
    private ArrayList<IndexEntryEntity> indexEntryEntities;
    private Intent intent;
    private IdUtility idUtility;
    private RecyclerView details;

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

        swipeToAction = new SwipeToAction(details, new SwipeToAction.SwipeListener<IndexEntryEntity>() {
            @Override
            public boolean swipeLeft(IndexEntryEntity itemData) {
                return false;
            }

            @Override
            public boolean swipeRight(IndexEntryEntity itemData) {
                return false;
            }

            @Override
            public void onClick(IndexEntryEntity itemData) {
                //TODO: set itemData in sharedData to access in CardDetails
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
        addButton = (ImageButton) findViewById(R.id.add_button);
        bogieNumber = (TextView) findViewById(R.id.bogey_number);

        idUtility = new IdUtility(new ProblemReferenceEntity(sharedData.getBogie(), sharedData.getType()), new IdListener() {
            @Override
            public void onIdListChanged(ArrayList<IndexEntryEntity> idList) {
                indexEntryEntities = idList;
                System.out.println("indexEntryEntities: "+indexEntryEntities.size());
                cardsAdapter = new CardsAdapter(indexEntryEntities, CardsActivity.this);
                details.setAdapter(cardsAdapter);
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
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
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
}
