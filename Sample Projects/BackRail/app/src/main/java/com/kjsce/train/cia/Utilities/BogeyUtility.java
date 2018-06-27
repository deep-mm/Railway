package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.BogeyEntity;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Listener.AddAudioListener;
import com.kjsce.train.cia.Listener.AddImageListener;
import com.kjsce.train.cia.Listener.OnCardListChangeListener;

import java.util.List;

public class BogeyUtility
{
    /*private BogeyEntity bogeyEntity;                                //Contains all the cards
    private String position;                                        //Contains where the child is changed
    private int action;                                             //Contains the action

    private String bogeyNumber;                                     //Used for reference
    private OnCardListChangeListener onCardListChangeListener;      //Used for the frontend team to update spontaneously

    private DatabaseReference mTrainDatabaseReference;              //Reference
    private ValueEventListener valueEventListener;                  //When the value is changed
    private ChildEventListener childEventListener;                  //When a child is changed

    private ImageUtility imageUtility = new ImageUtility();         //Uploading images
    private AudioUtility audioUtility = new AudioUtility();
    //-------------------------------------------------------Constructors------------------------------------------------------------------------------

    public BogeyUtility(final String bogeyNumber){
        this.bogeyNumber = bogeyNumber;
        position = "";
        bogeyEntity = new BogeyEntity(bogeyNumber);
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys").child(bogeyNumber);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(onCardListChangeListener != null)
                    onCardListChangeListener.onDataChanged(bogeyEntity,position,action);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                action = 1;
                position = cardEntity.getProblem() + "/" + cardEntity.getId() + "/" + cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber();
                bogeyEntity.addProblem(dataSnapshot.getValue(CardEntity.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                action = 2;
                position = cardEntity.getProblem() + "/" + cardEntity.getId() + "/" + cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber();
                bogeyEntity.updateProblem(dataSnapshot.getValue(CardEntity.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                action = 3;
                position = cardEntity.getProblem() + "/" + cardEntity.getId() + "/" + cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber();
                bogeyEntity.removeProblem(dataSnapshot.getValue(CardEntity.class));
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
        this.bogeyEntity = new BogeyEntity(bogeyNumber);
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

    public BogeyEntity getBogeyEntity(){
        return bogeyEntity;
    }

    public void addCard(final CardEntity cardEntity){
        if(cardEntity.getImage().size() > 0){
            imageUtility.uploadImage(cardEntity.getImage(), cardEntity.getBogeyNumber(), new AddImageListener() {
                @Override
                public void onCompleteTask(List<String> imageS) {
                    cardEntity.setImage(imageS);
                    audioUtility.uploadAudio(cardEntity.getAudio(), cardEntity.getBogeyNumber(), new AddAudioListener() {
                        @Override
                        public void onCompleteTask(List<String> audioS) {
                            cardEntity.setAudio(audioS);
                            mTrainDatabaseReference.child(cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber()).setValue(cardEntity);
                        }
                    });
                }
            });
        }
    }

    public void setCard(final CardEntity cardEntity){
        if(cardEntity.getImage().size() > 0){
            imageUtility.uploadImage(cardEntity.getImage(), cardEntity.getBogeyNumber(), new AddImageListener() {
                @Override
                public void onCompleteTask(List<String> imageS) {
                    cardEntity.setImage(imageS);
                    mTrainDatabaseReference.child(cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber()).setValue(cardEntity);
                }
            });
        }
    }

    public void removeCard(String dateTime, String sender, String trainNumber){
        mTrainDatabaseReference.child(dateTime + sender + trainNumber).setValue(null);
    }*/
}
