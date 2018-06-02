package com.example.amey.loginfirebase.Utilities.Backend;

import com.example.amey.loginfirebase.Entity.TrainEntity;
import com.example.amey.loginfirebase.Listener.AddTrainListener;
import com.example.amey.loginfirebase.Listener.GetTrainListener;
import com.example.amey.loginfirebase.Listener.RemoveTrainListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TrainUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mTrainDatabaseReference;

    public void getTrain(final TrainEntity trainEntity, final GetTrainListener getTrainListener){
        mTrainDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("Train").child(trainEntity.getTrainNumber());

        mTrainDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TrainEntity trainEntity1 = dataSnapshot.getValue(TrainEntity.class);
                System.out.println("Train:"+trainEntity1);
                getTrainListener.onCompleteTask(trainEntity1);
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
}
