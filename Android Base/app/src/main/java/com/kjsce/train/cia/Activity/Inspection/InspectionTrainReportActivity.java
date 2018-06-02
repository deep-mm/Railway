package com.kjsce.train.cia.Activity.Inspection;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.Activity.LoginActivity;
import com.kjsce.train.cia.Activity.SharedData;
import com.kjsce.train.cia.R;

import java.util.ArrayList;
import java.util.Locale;

import java.util.ArrayList;
import java.util.Locale;

public class InspectionTrainReportActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SearchView.OnQueryTextListener, AdapterView.OnItemClickListener  {

    TextView name,udesignation,uid;
    ListView lv1;
    SearchView sv1;
    ArrayAdapter<String> adapter;
    ArrayList<String> data;
    ArrayList<String> data1=new ArrayList<>();
    Spinner sp1,sp2;
    Button stins;
    SharedData sd;
    String typeOfInspection="";
    String selectedStation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_train_report);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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

        lv1=(ListView)findViewById(R.id.lv1);

        lv1.setVisibility(View.GONE);
        sv1=(SearchView)findViewById(R.id.sv1);
        sp1=(Spinner)findViewById(R.id.sp1);
        sp2=(Spinner)findViewById(R.id.sp2);
        stins=findViewById(R.id.stins);
        sd = new SharedData(getApplicationContext());

        final String[] station={"Enter station","Source","Destination","Mid-way"};
        String[] inspection={"Type of Inspection","General","Detailed"};


        data=new ArrayList<>();
        data.add("123    Abc");
        data.add("101    Sbc");
        data.add("323    Xyz");
        data.add("501    Abcxyz");
        data.add("301    tst");
        data.add("787    ttt");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");
        data.add("101    Sbc");


        // adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        sv1.setOnQueryTextListener(this);
        lv1.setOnItemClickListener(this);

        stins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeOfInspection.equalsIgnoreCase("") || selectedStation.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(),"Fields cannot be left empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), InspectionBogeyActivity.class);
                    startActivity(i);
                }
            }
        });

        // ArrayAdapter ad1=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,station);
        //ArrayAdapter ad2=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,inspection);

        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,station){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item);
        sp1.setAdapter(spinnerArrayAdapter1);



        // sp1.setAdapter(ad1);
        // sp2.setAdapter(ad2);


        final ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,inspection){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        sp2.setAdapter(spinnerArrayAdapter2);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    selectedStation = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    typeOfInspection = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        String x=data.get(id);
        Toast.makeText(this,"Item "+x,Toast.LENGTH_SHORT);
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.creport) {
            Intent i = new Intent(getApplicationContext(), InspectionTrainReportActivity.class);
            startActivity(i);
        } else if (id == R.id.totinspection) {

            //Total Inspection screen

        } else if (id == R.id.help) {

            //Create a help screen

        } else if (id == R.id.logout) {
            new MaterialDialog.Builder(InspectionTrainReportActivity.this)
                    .title("Confirm")
                    .content("Are you sure you want to Logout?")
                    .positiveText("Yes")
                    .negativeText("No")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            sd.isLoggedIn(false);
                            sd.clearAll();
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

        lv1.setVisibility(View.VISIBLE);
        newText = newText.toLowerCase(Locale.getDefault());
        data1.clear();
        int i=0;

        for(i=0;i< data.size();i++) {
            if (data.get(i).toLowerCase().contains(newText))
                data1.add(data.get(i));
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
            lv1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        if(newText.length()==0)
        {
            data1.clear();
            lv1.setVisibility(View.GONE);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
            lv1.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        return false;

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String x=data1.get(position);
        Toast.makeText(this,"Item "+x,Toast.LENGTH_SHORT).show();
        sv1.setQuery(x,true);
        lv1.setVisibility(View.GONE);

        //sd.setTrain(x);
    }
}
