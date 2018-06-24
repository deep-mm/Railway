package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.IdEntity;
import com.kjsce.train.cia.Entities.IdReferenceEntity;
import com.kjsce.train.cia.Entities.IndexEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.ProblemReferenceEntity;
import com.kjsce.train.cia.Listener.IdListener;

import java.util.ArrayList;

public class IdUtility
{
    private DatabaseReference mTrainDatabaseReference;
    private ArrayList<IndexEntryEntity> indexEntryEntities;

    public IdUtility(ProblemReferenceEntity problemReferenceEntity, final IdListener listener){
        createReference(problemReferenceEntity);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                IndexEntity indexEntity = dataSnapshot.getValue(IndexEntity.class);
                if(indexEntity != null) {
                    indexEntryEntities = indexEntity.getIndex();
                    listener.onIdListChanged(indexEntity.getIndex());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mTrainDatabaseReference.addValueEventListener(valueEventListener);
    }

    public void createReference(ProblemReferenceEntity problemReferenceEntity){
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys")
                .child(problemReferenceEntity.getBogeyNumber())
                .child(problemReferenceEntity.getProblem())
                .child("Index");
    }

    public ArrayList<IndexEntryEntity> getIdList(){
        return indexEntryEntities;
    }
}
