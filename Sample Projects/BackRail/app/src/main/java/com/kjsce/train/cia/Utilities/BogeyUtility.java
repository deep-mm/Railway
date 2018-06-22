package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Listener.OnCardListChangeListener;

import java.util.ArrayList;
import java.util.List;

public class BogeyUtility
{
    private List<CardEntity> cardEntityList;                        //Contains all the cards

    private String bogeyNumber;                                     //Used for reference
    private OnCardListChangeListener onCardListChangeListener;      //Used for the frontend team to update spontaneously

    private DatabaseReference mTrainDatabaseReference;              //Reference
    private ValueEventListener valueEventListener;                  //When the value is changed
    private ChildEventListener childEventListener;                  //When a child is changed

    //-------------------------------------------------------Constructors------------------------------------------------------------------------------

    public BogeyUtility(String bogeyNumber){
        this.bogeyNumber = bogeyNumber;
        cardEntityList = new ArrayList<CardEntity>();
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys").child(bogeyNumber);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(onCardListChangeListener != null)
                    onCardListChangeListener.onDataChanged(cardEntityList);

                System.out.println(cardEntityList.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                cardEntityList.add(dataSnapshot.getValue(CardEntity.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                for(int i=cardEntityList.size()-1;i>=0;i--){
                    if(cardEntityList.get(i).equals(cardEntity)){
                        cardEntityList.get(i).copy(cardEntity);
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                for(int i=cardEntityList.size()-1;i>=0;i--){
                    if(cardEntityList.get(i).equals(cardEntity)){
                        cardEntityList.remove(i);
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mTrainDatabaseReference.addValueEventListener(valueEventListener);
        mTrainDatabaseReference.addChildEventListener(childEventListener);
    }

    public BogeyUtility(String bogeyNumber,OnCardListChangeListener onCardListChangeListener){
        this(bogeyNumber);
        this.onCardListChangeListener = onCardListChangeListener;
    }

    //-------------------------------------------------------Getter and Setter------------------------------------------------------------------------------


    public String getBogeyNumber() {
        return bogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        mTrainDatabaseReference.removeEventListener(valueEventListener);
        mTrainDatabaseReference.removeEventListener(childEventListener);
        this.bogeyNumber = bogeyNumber;
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys").child(bogeyNumber);
        mTrainDatabaseReference.addChildEventListener(childEventListener);
        mTrainDatabaseReference.addValueEventListener(valueEventListener);

    }

    public OnCardListChangeListener getOnCardListChangeListener() {
        return onCardListChangeListener;
    }

    public void setOnCardListChangeListener(OnCardListChangeListener onCardListChangeListener) {
        this.onCardListChangeListener = onCardListChangeListener;
    }

    public void removeUpdating(){
        mTrainDatabaseReference.removeEventListener(valueEventListener);
        mTrainDatabaseReference.removeEventListener(childEventListener);
    }

    public void startUpdating(){
        mTrainDatabaseReference.addValueEventListener(valueEventListener);
        mTrainDatabaseReference.addChildEventListener(childEventListener);
    }

    //-------------------------------------------------------Main methods------------------------------------------------------------------------------

    public List<CardEntity> getCards(){
        return cardEntityList;
    }

    public void addCard(CardEntity cardEntity){
        mTrainDatabaseReference.child(cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber()).setValue(cardEntity);
    }

    public void setCard(CardEntity cardEntity){
        mTrainDatabaseReference.child(cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber()).setValue(cardEntity);
    }

    public void removeCard(String dateTime, String sender, String trainNumber){
        mTrainDatabaseReference.child(dateTime + sender + trainNumber).setValue(null);
    }
}
