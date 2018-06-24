package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.CardEntity;
import com.kjsce.train.cia.Entities.CardReferenceEntity;
import com.kjsce.train.cia.Entities.IdReferenceEntity;
import com.kjsce.train.cia.Entities.IndexEntity;
import com.kjsce.train.cia.Entities.IndexEntryEntity;
import com.kjsce.train.cia.Listener.AddAudioListener;
import com.kjsce.train.cia.Listener.AddImageListener;
import com.kjsce.train.cia.Listener.CardListener;

import java.util.ArrayList;
import java.util.List;

public class CardUtility
{
    private DatabaseReference mTrainDatabaseReference;
    private ImageUtility imageUtility = new ImageUtility();
    private AudioUtility audioUtility = new AudioUtility();

    private ArrayList<CardEntity> cardEntities = new ArrayList<>();

    public CardUtility(IdReferenceEntity idReferenceEntity,final CardListener listener){
        createReference(idReferenceEntity);

        //listener.onDataChanged(cardEntities);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //listener.onDataChanged(cardEntities);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                cardEntities.add(cardEntity);
                listener.onCardAdded(cardEntity);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                for(int i=cardEntities.size() - 1;i>=0;i--){
                    if(cardEntity.equals(cardEntities.get(i))){
                        cardEntities.get(i).copy(cardEntity);
                    }
                }
                listener.onCardChanged(cardEntity);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                CardEntity cardEntity = dataSnapshot.getValue(CardEntity.class);
                cardEntities.remove(cardEntity);
                listener.onCardRemoved(cardEntity);
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

    private void createReference(CardEntity cardEntity,CardReferenceEntity cardReferenceEntity){
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys")
                .child(cardReferenceEntity.getBogeyNumber())
                .child(cardReferenceEntity.getProblem())
                .child(cardReferenceEntity.getId())
                .child(cardEntity.getDateTime() + cardEntity.getSender() + cardEntity.getTrainNumber());
    }

    private void createReference(IdReferenceEntity idReferenceEntity){
        mTrainDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Bogeys")
                .child(idReferenceEntity.getBogeyNumber())
                .child(idReferenceEntity.getProblem())
                .child(idReferenceEntity.getId());

    }

    private void createIdIndex(final CardReferenceEntity cardReferenceEntity){
        final DatabaseReference temp = FirebaseDatabase.getInstance().getReference().child("Bogeys")
                .child(cardReferenceEntity.getBogeyNumber())
                .child(cardReferenceEntity.getProblem())
                .child("Index");

        temp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                IndexEntity index = dataSnapshot.getValue(IndexEntity.class);
                if(index == null){
                    index = new IndexEntity(new ArrayList<IndexEntryEntity>());
                }
                if(index.addIndex(new IndexEntryEntity(cardReferenceEntity.getId(),cardReferenceEntity.isProblemStatus()))) {
                    temp.setValue(index);
                    System.out.println("Index: " + index);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void removeIdIndex(final CardReferenceEntity cardReferenceEntity){
        final DatabaseReference temp = FirebaseDatabase.getInstance().getReference().child("Bogeys")
                .child(cardReferenceEntity.getBogeyNumber())
                .child(cardReferenceEntity.getProblem())
                .child("Index");

        temp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                IndexEntity index = dataSnapshot.getValue(IndexEntity.class);
                if(index == null){
                    index = new IndexEntity(new ArrayList<IndexEntryEntity>());
                }
                if(index.removeIndex(new IndexEntryEntity(cardReferenceEntity.getId(),cardReferenceEntity.isProblemStatus())))
                    temp.setValue(index);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void uploadCard(final CardEntity cardEntity, final CardReferenceEntity cardReferenceEntity){
        createIdIndex(cardReferenceEntity);
        createReference(cardEntity,cardReferenceEntity);
        imageUtility.uploadImage(cardEntity.getImage(), cardReferenceEntity.getBogeyNumber(), new AddImageListener() {
            @Override
            public void onCompleteTask(List<String> imageS) {
                cardEntity.setImage(imageS);
                audioUtility.uploadAudio(cardEntity.getAudio(), cardReferenceEntity.getBogeyNumber(), new AddAudioListener() {
                    @Override
                    public void onCompleteTask(List<String> audioS) {
                        cardEntity.setAudio(audioS);
                        mTrainDatabaseReference.setValue(cardEntity);
                    }
                });
            }
        });

    }

    public void removeCard(CardEntity cardEntity,CardReferenceEntity cardReferenceEntity){
        removeIdIndex(cardReferenceEntity);
        createReference(cardEntity,cardReferenceEntity);
        mTrainDatabaseReference.setValue(null);
    }

    public ArrayList<CardEntity> getCardsList(){
        return cardEntities;
    }
}