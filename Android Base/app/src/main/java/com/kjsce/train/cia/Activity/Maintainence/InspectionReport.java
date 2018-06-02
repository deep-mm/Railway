package com.kjsce.train.cia.Activity.Maintainence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kjsce.train.cia.R;

public class InspectionReport extends AppCompatActivity {

    TextView snoval,typeval,descriptionval,imgurl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_report);



        snoval=findViewById(R.id.snoval);
        typeval=findViewById(R.id.typeval);
        descriptionval=findViewById(R.id.descriptionval);
        imgurl=findViewById(R.id.imgurl);
        snoval.setText("12");
        typeval.setText("Technical");
        descriptionval.setText("Fan not working");
        imgurl.setText("IMG1234");


    }
}
