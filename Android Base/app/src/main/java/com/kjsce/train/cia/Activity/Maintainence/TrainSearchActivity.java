package com.kjsce.train.cia.Activity.Maintainence;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import com.google.firebase.auth.FirebaseAuth;
import com.kjsce.train.cia.Activity.LoginActivity;
import com.kjsce.train.cia.Adapter.TrainSearchListAdapter;
import com.kjsce.train.cia.Entity.TrainSearchListItem;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.List;

public class TrainSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener , NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<TrainSearchListItem> trainSearchListItems;
     private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    TextView name,udesignation,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);


        //navigation drawer

        mDrawerLayout =findViewById(R.id.drawer);
        mToggle= new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        android.support.design.widget.NavigationView navigationView =findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        name=(TextView) headerView.findViewById(R.id.name);
        name.setPaintFlags(name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        name.setText("Aman Agarwal");


        udesignation=(TextView) headerView.findViewById(R.id.udesignation);
        udesignation.setPaintFlags(udesignation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        udesignation.setText("Inspector");

        uid=(TextView) headerView.findViewById(R.id.uid);
        uid.setText("MH12345");

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        trainSearchListItems =new ArrayList<>();

        //List of Trains

        trainSearchListItems.add(new TrainSearchListItem("11019","CSTM BBS"));
        trainSearchListItems.add(new TrainSearchListItem("11093","CSTM BBS"));
        trainSearchListItems.add(new TrainSearchListItem("12105","CSTM GONDIA"));
        trainSearchListItems.add(new TrainSearchListItem("12137","CSTM FZR"));
        trainSearchListItems.add(new TrainSearchListItem("11057","CSTM ASR"));
        trainSearchListItems.add(new TrainSearchListItem("12123","CSTM PUNE"));
        trainSearchListItems.add(new TrainSearchListItem("51027","CSTM PVR"));
        trainSearchListItems.add(new TrainSearchListItem("51029","CSTM BJP"));
        trainSearchListItems.add(new TrainSearchListItem("51033","CSTM SNSI"));
        trainSearchListItems.add(new TrainSearchListItem("11027","CSTM MAS"));
        trainSearchListItems.add(new TrainSearchListItem("11041","CSTM MAS"));
        trainSearchListItems.add(new TrainSearchListItem("22105","CSTM PUNE"));
        trainSearchListItems.add(new TrainSearchListItem("22107","CSTM LUR"));
        trainSearchListItems.add(new TrainSearchListItem("22147","CSTM SNSI"));
        trainSearchListItems.add(new TrainSearchListItem("11007","CSTM PUNE"));


        adapter=new TrainSearchListAdapter(trainSearchListItems,this);
        recyclerView.setAdapter(adapter);

        EditText searchText=findViewById(R.id.search_field);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void filter(String text)
    {
        List<TrainSearchListItem> filterList=new ArrayList<>();
        for(TrainSearchListItem item: trainSearchListItems)
        {
            if((item.gettNo().toLowerCase().contains(text.toLowerCase())) || (item.gettName().toLowerCase().contains(text.toLowerCase())) )
            {
                filterList.add(item);
            }
        }
        adapter=new TrainSearchListAdapter(filterList,this);
        recyclerView.setAdapter(adapter);
    }

     @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id =item.getItemId();

        if(id==R.id.total_inspection)
        {
            Toast.makeText(this,"Total inspection",Toast.LENGTH_SHORT).show();

        }
        if(id==R.id.help)
        {
            Toast.makeText(this,"help selected",Toast.LENGTH_SHORT).show();

        }
        if(id==R.id.logout)
        {
          Toast.makeText(this,"logout selected",Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
          }

        return true;
    }

}

