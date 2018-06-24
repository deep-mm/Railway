package com.kjsce.train.cia.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kjsce.train.cia.Entities.BogeyEntity;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Entities.CardReferenceEntity;
import com.kjsce.train.cia.Entities.IdReferenceEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.ProblemReferenceEntity;
import com.kjsce.train.cia.Listener.AddImageListener;
import com.kjsce.train.cia.Listener.AddSingleImageUploadListener;
import com.kjsce.train.cia.Listener.CardListener;
import com.kjsce.train.cia.Listener.GetImageListener;
import com.kjsce.train.cia.Listener.IdListener;
import com.kjsce.train.cia.Listener.OnCardListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.BogeyUtility;
import com.kjsce.train.cia.Utilities.CardUtility;
import com.kjsce.train.cia.Utilities.IdUtility;
import com.kjsce.train.cia.Utilities.ImageUtility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BogeyActivity extends AppCompatActivity {

    private CardUtility cardUtility = new CardUtility(new IdReferenceEntity("1511092", "Toilet", "id1"), new CardListener() {
        @Override
        public void onCardAdded(CardEntity cardEntity) {
            System.out.println("Card added:\n" + cardEntity);
        }

        @Override
        public void onCardRemoved(CardEntity cardEntity) {
            System.out.println("Card removed:\n" + cardEntity);
        }

        @Override
        public void onDataChanged(ArrayList<CardEntity> cardEntities) {
            System.out.println("Total cards present: \n");
            for(int i=0;i<cardEntities.size();i++)
                System.out.println(cardEntities.get(i));
        }

        @Override
        public void onCardChanged(CardEntity cardEntity) {
            System.out.println("Card changed:\n" + cardEntity);
        }
    });

    private IdUtility idUtility = new IdUtility(new ProblemReferenceEntity("1511092", "Toilet"), new IdListener() {
        @Override
        public void onIdListChanged(ArrayList<IndexEntryEntity> idList) {
            System.out.println("List changed");
            for(int i=0;i<idList.size();i++)
                System.out.println(idList.get(i));
        }
    });
    /*private BogeyUtility bogeyUtility = new BogeyUtility("1511093", new OnCardListChangeListener() {
        @Override
        public void onDataChanged(BogeyEntity newBogeyEntity,String position,int action) {
            System.out.println(position);
            newBogeyEntity.print();
        }
    });*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey);
    }

    public void addCard(View view){
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy_HH:mm:ss");
        Date date = new Date();
        String dateTime = formatter.format(date);

        cardUtility.uploadCard(new CardEntity(dateTime,"Amey","1511092t","Hello"), new CardReferenceEntity("1511092","Toilet","id2",false));
    }

    public void removeCard(View view){
        cardUtility.removeCard(new CardEntity("24062018_22:25:06","Amey","1511092t"),new CardReferenceEntity("1511092","Toilet","3",false));
    }

    public void getCards(View view){
        ArrayList<CardEntity> cardEntities = cardUtility.getCardsList();
        System.out.println("Total cards present:\n");
        for(int i=0;i<cardEntities.size();i++)
            System.out.println(cardEntities.get(i));

    }

    public void getIdList(View view){
        ArrayList<IndexEntryEntity> idList = idUtility.getIdList();
        System.out.println("Total ids present:\n");
        for(int i=0;i<idList.size();i++){
            System.out.println(idList.get(i));
        }
    }

    /*public void getCards(View view){
        BogeyEntity bogeyEntity = bogeyUtility.getBogeyEntity();
        bogeyEntity.print();
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
        ArrayList<String> audioS = new ArrayList<>();
        audioS.add("");
        audioS.add("");
        audioS.add("");

    }*/

}
