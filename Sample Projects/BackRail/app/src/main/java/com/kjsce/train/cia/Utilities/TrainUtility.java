package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.BogeyEntity;
import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Listener.AddBogeyListener;
import com.kjsce.train.cia.Listener.AddTrainListener;
import com.kjsce.train.cia.Listener.DeleteBogeyListener;
import com.kjsce.train.cia.Listener.GetBogeyListListener;
import com.kjsce.train.cia.Listener.GetTrainListListener;

import java.util.ArrayList;
import java.util.List;

public class TrainUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mTrainDatabaseReference;

    public void addTrain(String trainNo, AddTrainListener addTrainListener){
        List<String> bogeyList = new ArrayList<>();
        bogeyList.add("null");
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setBogeyList(bogeyList);
        mTrainDatabaseReference = mFirebaseDatabase.getInstance().getReference().child("Trains").child(trainNo);
        mTrainDatabaseReference.setValue(trainEntity);
        addTrainListener.onCompleteTask("Done Add Train");
    }

    public void addBogey(final String bogeyNo, String trainNo, final AddBogeyListener addBogeyListener){
        mTrainDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("Trains").child(trainNo);
        mTrainDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> bogeyList;
                TrainEntity trainEntity = dataSnapshot.getValue(TrainEntity.class);
                bogeyList = trainEntity.getBogeyList();
                bogeyList.remove("null");
                if(!bogeyList.contains(bogeyNo))
                    bogeyList.add(bogeyNo);
                trainEntity.setBogeyList(bogeyList);
                mTrainDatabaseReference.setValue(trainEntity);
                addBogeyListener.onCompleteTask("Done Bogey Add");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getBogeyList(String trainNo, final GetBogeyListListener getBogeyListListener){
        mTrainDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("Trains").child(trainNo);
        mTrainDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TrainEntity trainEntity = dataSnapshot.getValue(TrainEntity.class);
                getBogeyListListener.onCompleteTask(trainEntity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void deleteBogey(final String bogeyNo, String trainNo, final DeleteBogeyListener deleteBogeyListener){
        mTrainDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("Trains").child(trainNo);
        mTrainDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> bogeyList;
                TrainEntity trainEntity = dataSnapshot.getValue(TrainEntity.class);
                bogeyList = trainEntity.getBogeyList();
                bogeyList.remove(bogeyNo);
                trainEntity.setBogeyList(bogeyList);
                mTrainDatabaseReference.setValue(trainEntity);
                deleteBogeyListener.onCompleteTask("Done Delete Bogey");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getTrainList(final GetTrainListListener getTrainListListener){
        final List<String> trainList = new ArrayList<>();

        ValueEventListener tripListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getTrainListListener.onCompleteTask(trainList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mTrainDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("Trains");
        mTrainDatabaseReference.addValueEventListener(tripListner);
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                trainList.add(dataSnapshot.getKey());
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
        mTrainDatabaseReference.addChildEventListener(childEventListener);



    }
}
