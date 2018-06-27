package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Listener.OnTrainListChangeListener;

import java.util.ArrayList;
import java.util.List;

public class TrainListUtility {
    private List<String> trainList;
    private OnTrainListChangeListener onTrainListChangeListener;
    private DatabaseReference mTrainListDatabaseReference;
    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;

    public TrainListUtility(){
        trainList = new ArrayList<>();
        mTrainListDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Trains");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(onTrainListChangeListener!=null)
                    onTrainListChangeListener.OnDataChenged(trainList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                trainList.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                trainList.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mTrainListDatabaseReference.addValueEventListener(valueEventListener);
        mTrainListDatabaseReference.addChildEventListener(childEventListener);

    }

    public TrainListUtility(OnTrainListChangeListener onTrainListChangeListener) {
        this();
        this.onTrainListChangeListener = onTrainListChangeListener;
    }

    public OnTrainListChangeListener getOnTrainListChangeListener() {
        return onTrainListChangeListener;
    }

    public void setOnTrainListChangeListener(OnTrainListChangeListener onTrainListChangeListener) {
        this.onTrainListChangeListener = onTrainListChangeListener;
    }

    public void removeUpdating(){
        mTrainListDatabaseReference.removeEventListener(valueEventListener);
        mTrainListDatabaseReference.removeEventListener(childEventListener);
    }

    public void startUpdating(){
        mTrainListDatabaseReference.addValueEventListener(valueEventListener);
        mTrainListDatabaseReference.addChildEventListener(childEventListener);
    }

    public List<String> getTrainList() {
        return trainList;
    }

    public void setTrainList(List<String> trainList) {
        this.trainList = trainList;
    }
}
