package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Listener.OnBogeyListChangeListener;

public class TrainUtility
{
    private TrainEntity trainEntity;
    private String trainNo;

    private OnBogeyListChangeListener onBogeyListChangeListener;

    private DatabaseReference mTrainDatabaseReference;
    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;

    public TrainUtility(final String trainNo){
        this.trainNo=trainNo;
        trainEntity=new TrainEntity(trainNo);

        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Trains").child(trainNo);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(onBogeyListChangeListener!=null)
                    onBogeyListChangeListener.onDataChanged(trainEntity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                trainEntity.getBogeyList().add(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                trainEntity.getBogeyList().remove(dataSnapshot.getValue(String.class));
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

    public TrainUtility(String trainNo, OnBogeyListChangeListener onBogeyListChangeListener){
        this(trainNo);
        this.onBogeyListChangeListener=onBogeyListChangeListener;
    }

    public void addBogey(String bogeyNo){
        mTrainDatabaseReference.child(bogeyNo).setValue(bogeyNo);
    }

    public void deleteBogey(String bogeyNo){
        mTrainDatabaseReference.child(bogeyNo).setValue(null);
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        mTrainDatabaseReference.removeEventListener(valueEventListener);
        mTrainDatabaseReference.removeEventListener(childEventListener);
        this.trainNo = trainNo;
        this.trainEntity=new TrainEntity(trainNo);
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Trains").child(trainNo);
        mTrainDatabaseReference.addChildEventListener(childEventListener);
        mTrainDatabaseReference.addValueEventListener(valueEventListener);

    }

    public OnBogeyListChangeListener getOnBogeyListChangeListener() {
        return onBogeyListChangeListener;
    }

    public void setOnBogeyListChangeListener(OnBogeyListChangeListener onBogeyListChangeListener) {
        this.onBogeyListChangeListener = onBogeyListChangeListener;
    }

    public void removeUpdating(){
        mTrainDatabaseReference.removeEventListener(valueEventListener);
        mTrainDatabaseReference.removeEventListener(childEventListener);
    }

    public void startUpdating(){
        mTrainDatabaseReference.addValueEventListener(valueEventListener);
        mTrainDatabaseReference.addChildEventListener(childEventListener);
    }

    public TrainEntity getTrainEntity() {
        return trainEntity;
    }

    public void detachListner(){
        mTrainDatabaseReference.removeEventListener(childEventListener);
        mTrainDatabaseReference.removeEventListener(valueEventListener);
    }

}
