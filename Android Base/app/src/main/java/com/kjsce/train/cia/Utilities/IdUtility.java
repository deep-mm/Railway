package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.IdEntity;
import com.kjsce.train.cia.Entities.IdReferenceEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Entities.ProblemReferenceEntity;
import com.kjsce.train.cia.Listener.IdListener;

import java.util.ArrayList;

public class IdUtility
{
    private DatabaseReference mTrainDatabaseReference;
    private ArrayList<IndexEntryEntity> indexEntryEntities = new ArrayList<IndexEntryEntity>();

    public IdUtility(ProblemReferenceEntity problemReferenceEntity, final IdListener listener){
        createReference(problemReferenceEntity);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onIdListChanged(indexEntryEntities);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                IdEntity idEntity = dataSnapshot.getValue(IdEntity.class);
                String id = dataSnapshot.getKey();
                IndexEntryEntity indexEntryEntity = new IndexEntryEntity(id,idEntity.isProblemStatus(),idEntity.getNumberOfCards(),idEntity.getSubtype());
                indexEntryEntities.add(indexEntryEntity);
                listener.onIdAdded(indexEntryEntity);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                IdEntity idEntity = dataSnapshot.getValue(IdEntity.class);
                String id = dataSnapshot.getKey();
                IndexEntryEntity indexEntryEntity = new IndexEntryEntity(id,idEntity.isProblemStatus(),idEntity.getNumberOfCards(),idEntity.getSubtype());
                if(!indexEntryEntities.contains(indexEntryEntity))
                    indexEntryEntities.add(indexEntryEntity);
                else{
                    int i = indexEntryEntities.indexOf(indexEntryEntity);
                    indexEntryEntities.get(i).setNumberOfCards(indexEntryEntity.getNumberOfCards());
                    indexEntryEntities.get(i).setProblemStatus(indexEntryEntity.isProblemStatus());
                }
                listener.onIdChanged(indexEntryEntity);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                IdEntity idEntity = dataSnapshot.getValue(IdEntity.class);
                String id = dataSnapshot.getKey();
                IndexEntryEntity indexEntryEntity = new IndexEntryEntity(id,idEntity.isProblemStatus(),idEntity.getNumberOfCards(),idEntity.getSubtype());
                indexEntryEntities.remove(indexEntryEntity);
                listener.onIdRemoved(indexEntryEntity);
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

    public void createReference(ProblemReferenceEntity problemReferenceEntity){
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys")
                .child(problemReferenceEntity.getBogeyNumber())
                .child(problemReferenceEntity.getProblem())
                .child("Index");
    }

    public void createReference(IdReferenceEntity idReferenceEntity){
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys")
                .child(idReferenceEntity.getBogeyNumber())
                .child(idReferenceEntity.getProblem())
                .child("Index")
                .child(idReferenceEntity.getId());
    }

    public ArrayList<IndexEntryEntity> getIdList(){
        return indexEntryEntities;
    }

    public void changeProblemStatus(IdReferenceEntity idReferenceEntity,boolean problemStatus){
        createReference(idReferenceEntity);
        mTrainDatabaseReference.child("problemStatus").setValue(problemStatus);
    }
}
