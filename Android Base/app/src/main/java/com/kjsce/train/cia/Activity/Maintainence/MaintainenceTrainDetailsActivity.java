package com.kjsce.train.cia.Activity.Maintainence;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kjsce.train.cia.Adapter.ItemArrayAdapter;
import com.kjsce.train.cia.Entity.Item;
import com.kjsce.train.cia.R;

import java.util.ArrayList;

public class MaintainenceTrainDetailsActivity extends AppCompatActivity {

    TextView snoval,typeval,descriptionval,imgurl;
RecyclerView recimg;
Context c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintainence_train_details);





        snoval=findViewById(R.id.snoval);
        typeval=findViewById(R.id.typeval);
        descriptionval=findViewById(R.id.descriptionval);

        snoval.setText("12");
        typeval.setText("Technical");
        descriptionval.setText("Fan not working");

        Intent i=new Intent(MaintainenceTrainDetailsActivity.this,Image.class);

        ArrayList<Item> itemList = new ArrayList<Item>();

        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(R.layout.list_item, itemList,MaintainenceTrainDetailsActivity.this);

        recimg = (RecyclerView) findViewById(R.id.img);
        recimg.setLayoutManager(new LinearLayoutManager(this));
        recimg.setItemAnimator(new DefaultItemAnimator());
        recimg.setAdapter(itemArrayAdapter);

        itemList.add(0,new Item("first"));
        itemList.add(1,new Item("second"));
        itemList.add(2,new Item("third"));
        itemArrayAdapter.notifyDataSetChanged();

    }


    public static void showImage(CharSequence text) {


    }
}
