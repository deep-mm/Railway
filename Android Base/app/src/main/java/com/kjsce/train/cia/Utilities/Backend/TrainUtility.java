package com.kjsce.train.cia.Utilities.Backend;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entity.TrainEntity;
import com.kjsce.train.cia.Listeners.AddTrainListener;
import com.kjsce.train.cia.Listeners.GetTrainListener;
import com.kjsce.train.cia.Listeners.RemoveTrainListener;

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
