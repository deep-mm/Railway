package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Adapter.CheckBoxAdapter;
import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.Adapter.TrainAdapter;
import com.kjsce.train.cia.Entities.UserCheckBox;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.UserUtility;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static com.kjsce.train.cia.Activity.SplashActivity.userUtility;

public class NotifyContacts extends AppCompatActivity {

    private SharedData sharedData;
    private Helper helper;
    private ImageButton sendButton, backButton;
    public static ImageButton removeButton, addButton;
    private TextView title_text;
    private CheckBoxAdapter checkBoxAdapter;
    private List<UserEntity> userEntities, checkedList;
    private List<UserCheckBox> userCheckBoxes, data1, allUserCheckBoxes;
    private MaterialDialog materialDialog;
    private Intent intent;
    private MaterialSearchView searchView;
    public static String searchBoxValue, from;
    private RecyclerView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_contacts);

        initialize();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        userEntities = userUtility.getUserList();
        checkedList = sharedData.getUserEntityList();

        Collections.sort(userEntities, new Comparator<UserEntity>() {
            @Override
            public int compare(UserEntity userEntity, UserEntity t1) {
                return userEntity.getName().compareToIgnoreCase(t1.getName());
            }
        });

        for(int i=0;i<userEntities.size();i++){
            //checkedList.add(false);
            UserCheckBox userCheckBox = new UserCheckBox(userEntities.get(i),false);
            userCheckBoxes.add(userCheckBox);
        }
        allUserCheckBoxes = userCheckBoxes;
        //sharedData.setCheckedList(checkedList);

        details = (RecyclerView) findViewById(R.id.details);
        checkBoxAdapter = new CheckBoxAdapter(userCheckBoxes, NotifyContacts.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(checkBoxAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        if(getIntent().hasExtra("from")){
            from = getIntent().getExtras().getString("from");
            if (from.equalsIgnoreCase("notification")){
                addButton.setVisibility(View.GONE);
                removeButton.setVisibility(View.GONE);
                sendButton.setVisibility(View.VISIBLE);
            }
            else{
                addButton.setVisibility(View.VISIBLE);
                removeButton.setVisibility(View.INVISIBLE);
                sendButton.setVisibility(View.GONE);
            }
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),AddUser.class);
                startActivity(intent);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedList = sharedData.getUserEntityList();
                new MaterialDialog.Builder(NotifyContacts.this)
                        .title("Confirm")
                        .content("Are you sure you want to remove the selected users?")
                        .positiveText("Yes")
                        .negativeText("No")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                //Run to remove users from database
                                for(int i=0;i<checkedList.size();i++)
                                    SplashActivity.userUtility.deleteUser(checkedList.get(i).getMobileNumber());

                                intent = new Intent(getApplicationContext(),NotifyContacts.class);
                                intent.putExtra("from",from);
                                startActivity(intent);
                                checkedList.clear();
                                sharedData.setUserEntityList(checkedList);
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

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkedList = sharedData.getUserEntityList();
                String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
                String train = sharedData.getTrain(), sender = sharedData.getUserEntity().getName();
                for(int i=0;i<checkedList.size();i++){
                        System.out.println("UserEntity: "+checkedList.get(i));
                        UserNotificationEntity userNotificationEntity = new UserNotificationEntity(train, timeStamp, sender);
                        BackgroundService.notificationUtility.sendNotification(checkedList.get(i).getMobileNumber(), userNotificationEntity);
                }
                CoachSearch.trainUtility.detachListner();
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                checkedList = sharedData.getUserEntityList();
                searchBoxValue = newText;
                System.out.println("search: " + searchBoxValue);
                newText = newText.toLowerCase(Locale.getDefault());
                data1.clear();
                int i;

                for (i = 0; i < allUserCheckBoxes.size(); i++) {
                    UserEntity userEntity = allUserCheckBoxes.get(i).getUserEntity();
                    if (userEntity.getName().toLowerCase().contains(newText) || userEntity.getDesignation().toLowerCase().contains(newText))
                        data1.add(allUserCheckBoxes.get(i));
                }

                userCheckBoxes = data1;
                System.out.println("train_list: " + data1);
                checkBoxAdapter = new CheckBoxAdapter(userCheckBoxes, NotifyContacts.this);
                details.setAdapter(checkBoxAdapter);
                checkBoxAdapter.notifyDataSetChanged();

                if (newText.length() == 0) {
                    data1.clear();
                    userCheckBoxes = allUserCheckBoxes;
                    checkBoxAdapter = new CheckBoxAdapter(userCheckBoxes, NotifyContacts.this);
                    details.setAdapter(checkBoxAdapter);
                    checkBoxAdapter.notifyDataSetChanged();
                }


                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                title_text.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                title_text.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);
            }
        });
    }

    public void initialize(){
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        sendButton = (ImageButton) findViewById(R.id.send_button);
        backButton = (ImageButton) findViewById(R.id.back_button);
        addButton = (ImageButton) findViewById(R.id.add_button);
        removeButton = (ImageButton) findViewById(R.id.remove_button);
        title_text = (TextView) findViewById(R.id.title_text);
        userEntities = new ArrayList<UserEntity>();
        checkedList = new ArrayList<UserEntity>();
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        userCheckBoxes = new ArrayList<UserCheckBox>();
        allUserCheckBoxes = new ArrayList<UserCheckBox>();
        data1 = new ArrayList<UserCheckBox>();
    }

    public void onProgressStart(){

        materialDialog = new MaterialDialog.Builder(NotifyContacts.this)
                .title("Syncing Data")
                .content("Please Wait")
                .progress(true, 0)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .show();
    }

    public void onProgressStop(){
        materialDialog.hide();
    }

    @Override
    public void onBackPressed(){
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }else {
            if(from.equalsIgnoreCase("notification"))
                super.onBackPressed();

            else {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        checkInternetConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    public void checkInternetConnection(){
        if(!helper.isNetworkConnected()){
            new MaterialDialog.Builder(NotifyContacts.this)
                    .title("No Internet Connection")
                    .content("You need active internet connection")
                    .positiveText("Retry")
                    .negativeText("Exit")
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
                            finishAffinity();
                        }
                    })
                    .canceledOnTouchOutside(false)
                    .cancelable(false)
                    .show();
        }
    }
}
