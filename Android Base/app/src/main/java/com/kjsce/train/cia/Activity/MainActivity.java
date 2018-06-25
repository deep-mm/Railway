package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.Adapter.TrainAdapter;
import com.kjsce.train.cia.Entity.TrainEntity;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private SharedData sharedData;
    private Helper helper;
    private UserEntity userEntity;
    private NavigationView navigationView;
    private View headerView;
    private TextView name, designation, mobile;
    private SearchView searchView;
    private ArrayList<String> train_list;
    private ArrayList<String> data1;
    private ArrayList<String> allTrains;
    private ImageButton nextButton;
    private Intent intent;
    private TrainAdapter trainAdapter;
    private Boolean flag = false;
    private String placeOfInspection, searchBoxValue;
    private RecyclerView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initialize();

        setSupportActionBar(toolbar);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.creport);

        name.setText("Deep Mehta");
        designation.setText("CRPF");
        mobile.setText("+91 9004096152");

        //train_list = sharedData.getTrainList();

        allTrains = train_list;
        searchView.setOnQueryTextListener(this);

        train_list.add("123456 Rajdhani");
        train_list.add("654321 Gareebrath");

        details = (RecyclerView) findViewById(R.id.details);
        trainAdapter = new TrainAdapter(train_list, MainActivity.this);
        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        details.setLayoutManager(mlayoutmanager);
        details.setAdapter(trainAdapter);
        details.setItemAnimator(new DefaultItemAnimator());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (train_list.size() == 0) {
                    addNewTrain();
                } else {
                    getInputName();
                }
            }
        });
    }

    public void addNewTrain(){

        new MaterialDialog.Builder(MainActivity.this)
                .title("New Train")
                .content("Are you sure you want to add this train: \n"+searchBoxValue+" ?")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        getInputName();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {

                    }
                })
                .show();
    }
    public void getInputName() {

        new MaterialDialog.Builder(this)
                .title("Place")
                .content("Enter place of Inspection")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Enter Text Here", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        placeOfInspection = input.toString();
                        if (!placeOfInspection.isEmpty()){
                            intent =new Intent(getApplicationContext(),CoachSearch.class);
                            startActivity(intent);
                        }
                    }
                }).show();
    }

    public void initialize(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        userEntity = sharedData.getUserEntity();
        headerView = navigationView.getHeaderView(0);
        name = (TextView) headerView.findViewById(R.id.name_text);
        designation = (TextView) headerView.findViewById(R.id.designation_text);
        mobile = (TextView) headerView.findViewById(R.id.mobile_text);
        searchView = (SearchView)findViewById(R.id.search_bar);
        train_list = new ArrayList<String>();
        nextButton = (ImageButton) findViewById(R.id.button_next);
        data1 = new ArrayList<String>();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new MaterialDialog.Builder(MainActivity.this)
                    .title("Exit")
                    .content("Are you sure you want to exit?")
                    .positiveText("Yes")
                    .negativeText("No")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            finishAffinity();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                        }
                    })
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.creport) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.add_login) {
            intent = new Intent(getApplicationContext(), AddUser.class);
            startActivity(intent);
        }
        else if (id == R.id.notifications){
            intent = new Intent(getApplicationContext(), Notifications.class);
            startActivity(intent);
        }
        else if (id == R.id.help) {
            //Create a help screen

        }
        else if (id == R.id.logout) {
            new MaterialDialog.Builder(MainActivity.this)
                    .title("Confirm")
                    .content("Are you sure you want to Logout?")
                    .positiveText("Yes")
                    .negativeText("No")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            sharedData.clearAll();
                            FirebaseAuth.getInstance().signOut();
                            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                        }
                    })
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        searchBoxValue = newText;
        System.out.println("search: "+searchBoxValue);
        newText = newText.toLowerCase(Locale.getDefault());
        data1.clear();
        int i;

        for(i=0;i< allTrains.size();i++) {
            if (allTrains.get(i).toLowerCase().contains(newText))
                data1.add(allTrains.get(i));
        }

        train_list = data1;
        System.out.println("train_list: "+data1);
        trainAdapter = new TrainAdapter(data1, MainActivity.this);
        details.setAdapter(trainAdapter);
        trainAdapter.notifyDataSetChanged();

        if(newText.length()==0)
        {
            data1.clear();
            train_list = allTrains;
            trainAdapter = new TrainAdapter(train_list, MainActivity.this);
            details.setAdapter(trainAdapter);
            trainAdapter.notifyDataSetChanged();
        }

        return false;

    }
}
