package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.kjsce.train.cia.R;

import java.io.File;

public class ImageShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);


        String url = "";
        if(getIntent().hasExtra("path")){
            url = getIntent().getExtras().getString("path");
        }

        ImageView iv=findViewById(R.id.imgv);

        File imgFile = new  File(url);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            iv.setImageBitmap(myBitmap);
        }

    }


}
