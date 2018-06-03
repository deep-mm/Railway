package com.example.amey.loginfirebase.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.amey.loginfirebase.Listener.AddImageListener;
import com.example.amey.loginfirebase.Listener.GetImageListener;
import com.example.amey.loginfirebase.R;
import com.example.amey.loginfirebase.Utilities.Backend.ImageUtility;
import com.frosquivel.magicaltakephoto.MagicalTakePhoto;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageActivity extends AppCompatActivity {

    MagicalTakePhoto magicalTakePhoto;
    private String timeStamp;
    private String mFileName = null;
    private List<String> imageL = new ArrayList<String>();
    private List<String> imageS = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_new);

    }

    public void getImage(View view){

        ImageUtility imageUtility = new ImageUtility();
        try {
            imageUtility.retrieveImage(imageS, new GetImageListener() {
                @Override
                public void onCompleteTask(List<String> image) {
                    imageL = image;
                    for(int i=0;i<image.size();i++){
                        System.out.println(image.get(i));
                    }
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void uploadImage(View view){
        File mainDir= Environment.getExternalStorageDirectory();
        File mainFile=new File(mainDir,"/CIA/Inspection/Images/Sent");

        ImageUtility imageUtility = new ImageUtility();
        imageUtility.uploadImage(imageL, new AddImageListener() {
            @Override
            public void onCompleteTask(List<String> image) {
                if(image != null){
                    for(int i=0;i<image.size();i++){
                        System.out.println(image.get(i));
                    }
                }
                imageS = image;
            }
        });
    }

    public void clickImage(View view){
        magicalTakePhoto =  new MagicalTakePhoto(this,2000);
        magicalTakePhoto.takePhoto("abc");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        magicalTakePhoto.resultPhoto(requestCode, resultCode, data);


        // example to get photo
        // imageView.setImageBitmap(magicalTakePhoto.getMyPhoto());
        Bitmap bitmap = magicalTakePhoto.getMyPhoto();

        File mainDir= Environment.getExternalStorageDirectory();
        File mainFile=new File(mainDir,"/CIA/Inspection/Images/Sent");
        mainFile.mkdirs();

        timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        mFileName = mainFile + "/" + new Random().nextInt(9) + timeStamp + ".jpg";
        imageL.add(mFileName);

        try {
            FileOutputStream fOut = new FileOutputStream(mFileName);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
