package com.kjsce.train.cia.Utilities.Backend;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.Listeners.AddUserListener;
import com.kjsce.train.cia.Listeners.GetUserListener;

public class UserUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserDatabaseReference;
    public void getUser(String userId, final GetUserListener getUserListener){
        mUserDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("User").child(userId);

        mUserDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserEntity userEntity = dataSnapshot.getValue(UserEntity.class);
                System.out.println("User:"+userEntity);
                getUserListener.onCompleteTask(userEntity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void addUser(UserEntity userEntity, AddUserListener listener){

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUserDatabaseReference = mFirebaseDatabase.getReference().child("User").child(userEntity.getUserId());
        mUserDatabaseReference.setValue(userEntity);
    }
}
