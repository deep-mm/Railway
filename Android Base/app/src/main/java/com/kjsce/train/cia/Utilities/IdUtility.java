package com.kjsce.train.cia.Utilities;

import android.provider.ContactsContract;

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
import java.util.Iterator;

public class IdUtility
{
    private DatabaseReference mTrainDatabaseReference;
    private ArrayList<IndexEntryEntity> indexEntryEntities = new ArrayList<IndexEntryEntity>();
    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;

    public IdUtility(ProblemReferenceEntity problemReferenceEntity, final IdListener listener){
        createReference(problemReferenceEntity);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                indexEntryEntities.clear();
                Iterator<DataSnapshot> iEEIterables = dataSnapshot.getChildren().iterator();
                while(iEEIterables.hasNext()){
                    DataSnapshot temp = iEEIterables.next();
                    IndexEntryEntity indexEntryEntity = new IndexEntryEntity(temp.getKey(),temp.getValue(IdEntity.class));
                    indexEntryEntities.add(indexEntryEntity);
                }
                listener.onIdListChanged(indexEntryEntities);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        /*childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                IdEntity idEntity = dataSnapshot.getValue(IdEntity.class);
                String id = dataSnapshot.getKey();
                IndexEntryEntity indexEntryEntity = new IndexEntryEntity(id,idEntity.isProblemStatus(),idEntity.getNumberOfCards(),idEntity.getSubtype(),idEntity.getPriority());
                indexEntryEntities.add(indexEntryEntity);
                listener.onIdAdded(indexEntryEntity);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                IdEntity idEntity = dataSnapshot.getValue(IdEntity.class);
                String id = dataSnapshot.getKey();
                IndexEntryEntity indexEntryEntity = new IndexEntryEntity(id,idEntity.isProblemStatus(),idEntity.getNumberOfCards(),idEntity.getSubtype(),idEntity.getPriority());
                if(!indexEntryEntities.contains(indexEntryEntity))
                    indexEntryEntities.add(indexEntryEntity);
                else{
                    int i = indexEntryEntities.indexOf(indexEntryEntity);
                    indexEntryEntities.get(i).setNumberOfCards(indexEntryEntity.getNumberOfCards());
                    indexEntryEntities.get(i).setProblemStatus(indexEntryEntity.isProblemStatus());
                    indexEntryEntities.get(i).setPriority(indexEntryEntity.getPriority());
                }
                listener.onIdChanged(indexEntryEntity);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                IdEntity idEntity = dataSnapshot.getValue(IdEntity.class);
                String id = dataSnapshot.getKey();
                IndexEntryEntity indexEntryEntity = new IndexEntryEntity(id,idEntity.isProblemStatus(),idEntity.getNumberOfCards(),idEntity.getSubtype(),idEntity.getPriority());
                indexEntryEntities.remove(indexEntryEntity);
                listener.onIdRemoved(indexEntryEntity);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };*/

        mTrainDatabaseReference.keepSynced(true);
        mTrainDatabaseReference.addValueEventListener(valueEventListener);
        //mTrainDatabaseReference.addChildEventListener(childEventListener);
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

        DatabaseReference analysisReference = FirebaseDatabase.getInstance()
                .getReference()
                .child("Bogeys")
                .child(idReferenceEntity.getBogeyNumber())
                .child("Analysis")
                .child(idReferenceEntity.getProblem());

        if(problemStatus){
            analysisReference.child("unsolvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    analysisReference.child("unsolvedProblems").setValue(((long)dataSnapshot.getValue() - 1));
                    analysisReference.child("solvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            analysisReference.child("solvedProblems").setValue((long)dataSnapshot.getValue() + 1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            analysisReference.child("solvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    analysisReference.child("solvedProblems").setValue(((long)dataSnapshot.getValue() - 1));
                    analysisReference.child("unsolvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            analysisReference.child("unsolvedProblems").setValue((long)dataSnapshot.getValue() + 1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void incrementLowProblems(DatabaseReference analysisReference){
        analysisReference.child("lowProblems").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                analysisReference.child("lowProblems").setValue(dataSnapshot.getValue(Integer.class) + 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void incrementMediumProblems(DatabaseReference analysisReference){
        analysisReference.child("mediumProblems").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                analysisReference.child("mediumProblems").setValue(dataSnapshot.getValue(Integer.class) + 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void incrementCriticalProblems(DatabaseReference analysisReference){
        analysisReference.child("criticalProblems").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                analysisReference.child("criticalProblems").setValue(dataSnapshot.getValue(Integer.class) + 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void changePriority(IdReferenceEntity idReferenceEntity,int priority){
        createReference(idReferenceEntity);


        mTrainDatabaseReference.child("priority").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int existingPriority = dataSnapshot.getValue(Integer.class);

                if (existingPriority != priority) {

                    System.out.println("Existing: " + existingPriority + " Priority: " + priority);
                    mTrainDatabaseReference.child("priority").setValue(priority);

                    DatabaseReference analysisReference = FirebaseDatabase.getInstance()
                            .getReference()
                            .child("Bogeys")
                            .child(idReferenceEntity.getBogeyNumber())
                            .child("Analysis")
                            .child(idReferenceEntity.getProblem());

                    switch (existingPriority) {
                        case 0:
                            analysisReference.child("lowProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    analysisReference.child("lowProblems").setValue(dataSnapshot.getValue(Integer.class) - 1);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            switch (priority) {
                                case 1:
                                    incrementMediumProblems(analysisReference);
                                    break;

                                case 2:
                                    incrementCriticalProblems(analysisReference);
                                    break;
                            }
                            break;

                        case 1:
                            analysisReference.child("mediumProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    analysisReference.child("mediumProblems").setValue(dataSnapshot.getValue(Integer.class) - 1);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            switch (priority) {
                                case 0:
                                    incrementLowProblems(analysisReference);
                                    break;

                                case 2:
                                    incrementCriticalProblems(analysisReference);
                                    break;
                            }
                            break;

                        case 2:
                            analysisReference.child("criticalProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    analysisReference.child("criticalProblems").setValue(dataSnapshot.getValue(Integer.class) - 1);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            switch (priority) {
                                case 0:
                                    incrementLowProblems(analysisReference);
                                    break;

                                case 1:
                                    incrementMediumProblems(analysisReference);
                                    break;
                            }
                            break;

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /*if(problemStatus){
            analysisReference.child("unsolvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    analysisReference.child("unsolvedProblems").setValue(((long)dataSnapshot.getValue() - 1));
                    analysisReference.child("solvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            analysisReference.child("solvedProblems").setValue((long)dataSnapshot.getValue() + 1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else{
            analysisReference.child("solvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    analysisReference.child("solvedProblems").setValue(((long)dataSnapshot.getValue() - 1));
                    analysisReference.child("unsolvedProblems").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            analysisReference.child("unsolvedProblems").setValue((long)dataSnapshot.getValue() + 1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } */
    }

    public void detachListner(){
        //mTrainDatabaseReference.removeEventListener(childEventListener);
        mTrainDatabaseReference.removeEventListener(valueEventListener);
    }
}
