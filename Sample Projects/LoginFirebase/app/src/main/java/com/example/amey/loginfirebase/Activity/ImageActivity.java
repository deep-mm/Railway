package com.example.amey.loginfirebase.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Card.DetailedCard;
import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddBogeyImageListener;
import com.example.amey.loginfirebase.Listener.AddGeneralImageListener;
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

        Button button = (Button) findViewById(R.id.clickImage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                magicalTakePhoto =  new MagicalTakePhoto(ImageActivity.this,2000);
                magicalTakePhoto.takePhoto("abc");
            }
        });
    }

    public void getImage(View view){

        /*ImageUtility imageUtility = new ImageUtility();
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
        }*/
    }

    public void uploadImage(View view){
        File mainDir= Environment.getExternalStorageDirectory();
        File mainFile=new File(mainDir,"/CIA/Inspection/Images/Sent");

        List<String> imageL = new ArrayList<String>();
        imageL.add(mainFile + "/320180501_194610.jpg");
        imageL.add(mainFile + "/420180501_194600.jpg");
        imageL.add(mainFile + "/820180425_080216.jpg");

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

    public void click(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        magicalTakePhoto.resultPhoto(requestCode, resultCode, data);

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

    public void uploadBogey(View view){

        File mainDir= Environment.getExternalStorageDirectory();
        File mainFile=new File(mainDir,"/CIA/Inspection/Images/Sent");

        List<String> image1 = new ArrayList<String>();
        List<String> image2 = new ArrayList<String>();

        image1.add(mainFile + "/320180501_194610.jpg");
        image1.add(mainFile + "/420180501_194600.jpg");

        image2.add(mainFile + "/820180425_080216.jpg");

        List<DetailedCard> detailedCardList1 = new ArrayList<DetailedCard>();
        detailedCardList1.add(new DetailedCard("Amey","type",image1,null,"comment1",false,null));

        List<DetailedCard> detailedCardList2 = new ArrayList<DetailedCard>();
        detailedCardList2.add(new DetailedCard("Bhavik","type",image2,null,"comment2",false,null));


        BogeyEntity bg1 = new BogeyEntity("123","type",detailedCardList1,null);
        BogeyEntity bg2 = new BogeyEntity("321","epyt",detailedCardList2,null);

        List<BogeyEntity> bogeyEntityList = new ArrayList<BogeyEntity>();
        bogeyEntityList.add(bg1);
        bogeyEntityList.add(bg2);

        ImageUtility imageUtility = new ImageUtility();
        imageUtility.uploadBogeyImage(bogeyEntityList, new AddBogeyImageListener() {
            @Override
            public void onCompleteTask(List<BogeyEntity> bogeyEntityList) {
                System.out.println("Size " + bogeyEntityList.size());
                if(bogeyEntityList != null){
                    for(int i=0;i<bogeyEntityList.size();i++){
                        System.out.println(bogeyEntityList.get(i));
                        System.out.println(i + ": " + bogeyEntityList.get(i).getDetailedCard().size());
                        for(int j=0;j<bogeyEntityList.get(i).getDetailedCard().size();j++){
                            System.out.println(bogeyEntityList.get(i).getDetailedCard().get(j).getImage().size());
                        }
                    }
                }
            }
        });
    }

    public void uploadGeneral(View view){

        File mainDir= Environment.getExternalStorageDirectory();
        File mainFile=new File(mainDir,"/CIA/Inspection/Images/Sent");

        List<String> image1 = new ArrayList<String>();
        List<String> image2 = new ArrayList<String>();


        image1.add(mainFile + "/320180501_194610.jpg");
        image1.add(mainFile + "/420180501_194600.jpg");

        image2.add(mainFile + "/820180425_080216.jpg");


        List<GeneralCard> generalCardList = new ArrayList<GeneralCard>();
        GeneralCard g1 = new GeneralCard("Amey","type",image1,null,"comment1",false);
        GeneralCard g2 = new GeneralCard("Bhavik","type",image2,null,"comment2",false);
        generalCardList.add(g1);
        generalCardList.add(g2);

        ImageUtility imageUtility = new ImageUtility();
        imageUtility.uploadGeneralImage(generalCardList, new AddGeneralImageListener() {
            @Override
            public void onCompleteTask(List<GeneralCard> generalCardList) {
                System.out.println("Completed");
                if(generalCardList != null){
                    for(int i=0;i<generalCardList.size();i++){
                        System.out.println(generalCardList.get(i));
                    }
                }
            }
        });
    }
}
