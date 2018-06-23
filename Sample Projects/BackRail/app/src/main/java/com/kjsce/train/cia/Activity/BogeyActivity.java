package com.kjsce.train.cia.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kjsce.train.cia.Entities.BogeyEntity;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Listener.OnCardListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.BogeyUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BogeyActivity extends AppCompatActivity {

    private BogeyUtility bogeyUtility = new BogeyUtility("1511092", new OnCardListChangeListener() {
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss");
        Date date = new Date();
        bogeyUtility.setCard(new CardEntity("Amey", "1511092t", "Ghar", "Hello", null, null, formatter.format(date), null, null));
    }

    public void removeCard(View view){
        bogeyUtility.removeCard("231097", "Amey", "1511092t");
    }

}
