package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kjsce.train.cia.Adapter.DetailsAdapter;
import com.kjsce.train.cia.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ImageShow extends AppCompatActivity {

    private ImageButton backButton;
    private TextView titleText;
    private SharedData sharedData;
    private Helper helper;
    private String url, title;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        initialize();

        if(getIntent().hasExtra("url")){
            url = getIntent().getExtras().getString("url");
            title = getIntent().getExtras().getString("title");
        }

        Picasso.with(getApplicationContext()).load(url).into(imageView);
        titleText.setText(title);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void initialize(){
        backButton = (ImageButton) findViewById(R.id.back_button);
        titleText = (TextView) findViewById(R.id.title_text);
        sharedData = new SharedData(getApplicationContext());
        helper = new Helper(getApplicationContext());
        imageView = (ImageView) findViewById(R.id.imgv);
        url = "";
        title = "";
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }


}
