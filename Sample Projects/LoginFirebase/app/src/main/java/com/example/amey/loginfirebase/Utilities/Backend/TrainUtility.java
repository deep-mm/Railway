package com.example.amey.loginfirebase.Utilities.Backend;

import com.example.amey.loginfirebase.Entity.TrainEntity;
import com.example.amey.loginfirebase.Listener.AddTrainListener;
import com.example.amey.loginfirebase.Listener.GetTrainListListener;
import com.example.amey.loginfirebase.Listener.GetTrainListener;
import com.example.amey.loginfirebase.Listener.RemoveTrainListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrainUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mTrainDatabaseReference;

    public void getTrain(String trainNumber, final GetTrainListener getTrainListener){
        mTrainDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("Train").child(trainNumber);


        mTrainDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TrainEntity trainEntity = dataSnapshot.getValue(TrainEntity.class);
                System.out.println("Train:"+trainEntity);
                getTrainListener.onCompleteTask(trainEntity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addTrain(TrainEntity trainEntity, AddTrainListener addTrainListener){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mTrainDatabaseReference = mFirebaseDatabase.getReference().child("Train").child(trainEntity.getTrainNumber());
        mTrainDatabaseReference.setValue(trainEntity);
    }

    public void removeTrain(TrainEntity trainEntity, RemoveTrainListener removeTrainListener){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference().child("Train").child(trainEntity.getTrainNumber()).removeValue();
    }

    public void getTrainList(final GetTrainListListener getTrainListListener){

        final List<TrainEntity> trainEntityList = new ArrayList<TrainEntity>();

        ValueEventListener tripListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getTrainListListener.onComplete(trainEntityList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


        mTrainDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("Train");
        mTrainDatabaseReference.addValueEventListener(tripListner);
        ChildEventListener mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TrainEntity trainEntity = dataSnapshot.getValue(TrainEntity.class);
                trainEntityList.add(trainEntity);
                System.out.println("zzzzz"+trainEntity);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mTrainDatabaseReference.addChildEventListener(mChildEventListener);


    }
}
