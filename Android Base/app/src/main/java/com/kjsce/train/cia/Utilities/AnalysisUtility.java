package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.AnalysisEntity;
import com.kjsce.train.cia.Entities.BogeyAnalysisEntity;
import com.kjsce.train.cia.Listener.GetBogeyAnalysisListener;

import java.util.Iterator;

public class AnalysisUtility
{
    //ChildEventListener childEventListener;
    ValueEventListener valueEventListener;

    public void getBogeyAnalysis(String bogeyNumber, GetBogeyAnalysisListener listener){


        DatabaseReference analysisReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("Bogeys")
                .child(bogeyNumber)
                .child("Analysis");

        /*childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                bogeyAnalysisEntity.addProblemAnalysis(dataSnapshot.getKey(),dataSnapshot.getValue(AnalysisEntity.class));
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
        };*/

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> problemIterator = dataSnapshot.getChildren().iterator();
                BogeyAnalysisEntity bogeyAnalysisEntity = new BogeyAnalysisEntity(bogeyNumber);
                while(problemIterator.hasNext()){
                    DataSnapshot problemSnapshot = problemIterator.next();
                    String problem = problemSnapshot.getKey();
                    AnalysisEntity problemAnalysis = problemSnapshot.getValue(AnalysisEntity.class);
                    bogeyAnalysisEntity.addProblemAnalysis(problem,problemAnalysis);
                }

                listener.onCompleteTask(bogeyAnalysisEntity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        //analysisReference.addChildEventListener(childEventListener);
        analysisReference.addValueEventListener(valueEventListener);
    }

    public void detachListener(String bogeyNumber){
        DatabaseReference analysisReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("Bogeys")
                .child(bogeyNumber)
                .child("Analysis");

        //analysisReference.removeEventListener(childEventListener);
        analysisReference.removeEventListener(valueEventListener);
    }
}
