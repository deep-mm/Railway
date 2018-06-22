package com.kjsce.train.cia.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Listener.OnCardListChangeListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.BogeyUtility;

import java.util.List;

public class BogeyActivity extends AppCompatActivity {

    private BogeyUtility bogeyUtility = new BogeyUtility("1511092", new OnCardListChangeListener() {
        @Override
        public void onDataChanged(List<CardEntity> newCardList) {
            for(int i=0;i<newCardList.size();i++)
                System.out.println(newCardList.get(i));
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bogey);

    }

    public void getCards(View view){
        List<CardEntity> cardEntityList = bogeyUtility.getCards();
        for(int i=0;i<cardEntityList.size();i++){
            System.out.println(cardEntityList.get(i));
        }
    }

    public void addCard(View view){
        bogeyUtility.addCard(new CardEntity("Amey", "1511092t", "231097"));
    }

    public void setCard(View view){
        bogeyUtility.setCard(new CardEntity("Amey", "1511092t", "Ghar", "Hello", null, null, "231097", null, null));
    }

    public void removeCard(View view){
        bogeyUtility.removeCard("231097", "Amey", "1511092t");
    }

}
