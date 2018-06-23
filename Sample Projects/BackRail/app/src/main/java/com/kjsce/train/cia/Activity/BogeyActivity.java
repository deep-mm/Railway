package com.kjsce.train.cia.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kjsce.train.cia.Entities.BogeyEntity;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Listener.AddImageListener;
import com.kjsce.train.cia.Listener.AddSingleImageUploadListener;
import com.kjsce.train.cia.Listener.GetImageListener;
import com.kjsce.train.cia.Listener.OnCardListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.BogeyUtility;
import com.kjsce.train.cia.Utilities.ImageUtility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BogeyActivity extends AppCompatActivity {

    private BogeyUtility bogeyUtility = new BogeyUtility("1511093", new OnCardListChangeListener() {
        @Override
        public void onDataChanged(BogeyEntity newBogeyEntity,String position,int action) {
            System.out.println(position);
            newBogeyEntity.print();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey);
    }

    public void getCards(View view){
        BogeyEntity bogeyEntity = bogeyUtility.getBogeyEntity();
        bogeyEntity.print();
    }

    public void addCard(View view){
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HH:mm:ss");
        Date date = new Date();
        String dateTime = formatter.format(date);
        bogeyUtility.addCard(new CardEntity("Amey" + new Random().nextInt(10), "1511092t", dateTime,"subtype+count" + new Random().nextInt(10),"2"));
    }

    public void setCard(View view){
        ArrayList<String> imageL = new ArrayList<>();

        File file = Environment.getExternalStorageDirectory();
        File mainFile = new File(file,"CIA/Inspection/Images/Sent");

        imageL.add(mainFile.getAbsolutePath() + "/20161115_194018.jpg");
        imageL.add(mainFile.getAbsolutePath() + "/20161122_104354.jpg");
        imageL.add(mainFile.getAbsolutePath() + "/20161220_115120.jpg");

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HH:mm:ss");
        Date date = new Date();
        CardEntity cardEntity = new CardEntity("Amey", "1511092t", "Ghar", "Hello", imageL, null, formatter.format(date), "3", "newSubtype");
        cardEntity.setBogeyNumber("1511093");
        bogeyUtility.setCard(cardEntity);
    }

    public void removeCard(View view){
        bogeyUtility.removeCard("231097", "Amey", "1511092t");
    }

    public void uploadImage(View view){
        ArrayList<String> imageS = new ArrayList<>();
        imageS.add("https://firebasestorage.googleapis.com/v0/b/ciaproject-9588d.appspot.com/o/Image%2F1511092%2F20161115_194018.jpg?alt=media&token=64442376-4ec2-4af3-bcb2-eb1c741371f4");
        imageS.add("https://firebasestorage.googleapis.com/v0/b/ciaproject-9588d.appspot.com/o/Image%2F1511092%2F20161122_104354.jpg?alt=media&token=800bf6ce-799e-43a6-97ec-86f1ad1b783f");
        imageS.add("https://firebasestorage.googleapis.com/v0/b/ciaproject-9588d.appspot.com/o/Image%2F1511092%2F20161220_115120.jpg?alt=media&token=308248cb-dc02-4f14-a57c-76065b638d16");

        ImageUtility imageUtility = new ImageUtility();
        try {
            imageUtility.retrieveImage(imageS, new GetImageListener() {
                @Override
                public void onCompleteTask(List<String> imageL) {
                    for(int i=0;i<imageL.size();i++){
                        System.out.println(imageL.get(i));
                    }
                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void uploadAudio(View view){

    }

}
