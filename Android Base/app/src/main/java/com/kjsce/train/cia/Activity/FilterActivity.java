package com.kjsce.train.cia.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kjsce.train.cia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FilterActivity extends AppCompatActivity {

    private TextView dateOption, problemStatusOption, problemTypeOption,cancelOption,filterOption;
    private Calendar myCalendar1, myCalendar2;
    private EditText edittext1, edittext2;
    private DatePickerDialog.OnDateSetListener date1, date2;
    private LinearLayout linearLayout;
    private ImageButton backButton;
    private View view = null;
    private SharedData sharedData;
    private Helper helper;
    private List<String> date_list;
    private List<Boolean> status_list,type_list;
    private Intent intent;
    private Date startDate,endDate;

    public static final String DATES = "dates";
    public static final String OPTIONS = "options";
    public static final String STATUS = "status";

    public static String fromDate = "fromDate";
    public static String toDate = "toDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initialize();

        dateOption.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        problemStatusOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
        problemTypeOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
        showDateFilter();

        if(getIntent().hasExtra("from")){
            if(getIntent().getExtras().getString("from").equals("CardsActivity")){
                problemTypeOption.setVisibility(View.GONE);
            }
            else{
                problemStatusOption.setVisibility(View.GONE);
            }
        }
        else{
            problemStatusOption.setVisibility(View.GONE);
        }

        dateOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateOption.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                problemStatusOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
                problemTypeOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
                showDateFilter();
            }
        });

        problemTypeOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                problemTypeOption.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                problemStatusOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
                dateOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
                showSubOptions();
            }
        });

        problemStatusOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                problemStatusOption.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                problemTypeOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
                dateOption.setBackgroundColor(getResources().getColor(R.color.greyBackground));
                showStatus();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cancelOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        filterOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Put validations
                sharedData.setStatusCheckedList(status_list);
                sharedData.setTypeCheckedList(type_list);
                sharedData.setDateList(date_list);

                if(getIntent().getExtras().getString("from").equals("CardsActivity")){
                    FilterActivity.super.onBackPressed();
                }
                else{
                    intent = new Intent(getApplicationContext(),AnalysisActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void showStatus() {

        linearLayout.removeAllViews();
        String options[] = {"Solved", "Unsolved"};
        CheckBox checkBox;
        for (int i = 0; i < options.length; i++) {
            TableRow row = new TableRow(this);
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=8;
            int topMargin=8;
            int rightMargin=8;
            int bottomMargin=0;

            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            row.setLayoutParams(tableRowParams);
            checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(options[i]);
            row.addView(checkBox);
            linearLayout.addView(row);
            checkBox.setChecked(status_list.get(i));

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int index = compoundButton.getId();
                    status_list.set(index,b);
                }
            });
        }

    }

    private void showSubOptions() {

        linearLayout.removeAllViews();

        final String options[] = {"Toilets", "Coach Interior Amenities", "Coach Interior Cleanliness", "Coach Exterior",
                "Undergear", "Electricals", "Windows", "Others"};

        CheckBox checkBox;
        for (int i = 0; i < options.length; i++) {
            TableRow row = new TableRow(this);
            TableLayout.LayoutParams tableRowParams=
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin=8;
            int topMargin=8;
            int rightMargin=8;
            int bottomMargin=0;

            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(options[i]);
            row.addView(checkBox);
            linearLayout.addView(row);
            checkBox.setChecked(type_list.get(i));

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int index = compoundButton.getId();
                    type_list.set(index,b);
                }
            });


        }
    }

    private void showDateFilter() {

        myCalendar1 = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();


        date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, monthOfYear);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel1(myCalendar1);

            }
        };


        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2(myCalendar2);
            }

        };


        LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout.removeAllViews();
        view = li.inflate(R.layout.fragment_dates, null);

        edittext1 = view.findViewById(R.id.etFromDate);
        edittext2 = view.findViewById(R.id.etToDate);

        edittext1.setText(date_list.get(0));
        edittext2.setText(date_list.get(1));

        edittext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FilterActivity.this, date1, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edittext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(FilterActivity.this, date2, myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                        myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        if (view != null) {
            linearLayout.addView(view);
        }

    }

    private void updateLabel2(Calendar myCalendar2) {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDate = myCalendar2.getTime();
        if (startDate == null) {
            edittext2.setText(sdf.format(myCalendar2.getTime()));
            date_list = Arrays.asList("dd/MM/yyyy","dd/MM/yyyy");
        }
        else if(endDate.before(startDate)){
            endDate = null;
            Toast.makeText(getApplicationContext(),"End date cannot be before Start date",Toast.LENGTH_LONG).show();
            edittext2.setText("");
        }
        else{
            edittext2.setText(sdf.format(myCalendar2.getTime()));
            date_list.set(1,edittext2.getText().toString());
            date_list.set(0,sdf.format(startDate));
        }

    }

    private void updateLabel1(Calendar myCalendar1) {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDate = myCalendar1.getTime();
        if(endDate==null) {
            edittext1.setText(sdf.format(myCalendar1.getTime()));
            date_list = Arrays.asList("dd/MM/yyyy","dd/MM/yyyy");
        }
        else if(startDate.after(endDate)){
            startDate = null;
            Toast.makeText(getApplicationContext(),"Start date cannot be after End date",Toast.LENGTH_LONG).show();
            edittext1.setText("");
        }
        else{
            edittext1.setText(sdf.format(myCalendar1.getTime()));
            date_list.set(0,edittext1.getText().toString());
            date_list.set(1,sdf.format(endDate));
        }
    }

    private void initialize() {

        dateOption = findViewById(R.id.date_option);
        problemTypeOption = findViewById(R.id.problemType_option);
        problemStatusOption = findViewById(R.id.problemStatus_option);
        cancelOption=findViewById(R.id.cancel);
        filterOption=findViewById(R.id.applyfilter);

        backButton=(ImageButton) findViewById(R.id.back_button);

        linearLayout = findViewById(R.id.linearLayout);
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());

        date_list = sharedData.getDateList();
        status_list = sharedData.getStatusCheckedList();
        type_list = sharedData.getTypeCheckedList();

        if(date_list.get(0).equalsIgnoreCase("dd/MM/yyyy") || date_list.get(1).equalsIgnoreCase("dd/MM/yyyy")){
            startDate = null;
            endDate = null;
        }
        else{
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            try {
                startDate = sdf.parse(date_list.get(0));
                endDate = sdf.parse(date_list.get(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed(){
        new MaterialDialog.Builder(FilterActivity.this)
                .title("Confirm")
                .content("Are you sure you want to cancel, all changes will be lost")
                .positiveText("Yes")
                .negativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        FilterActivity.super.onBackPressed();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                    }
                })
                .canceledOnTouchOutside(false)
                .show();
    }
}
