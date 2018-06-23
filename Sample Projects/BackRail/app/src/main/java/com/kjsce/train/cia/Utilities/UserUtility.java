package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.TrainEntity;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Listener.AddUserListener;
import com.kjsce.train.cia.Listener.DeleteUserListener;
import com.kjsce.train.cia.Listener.GetUserListListener;
import com.kjsce.train.cia.Listener.GetUserListener;
import com.kjsce.train.cia.Listener.SetUserListener;

import java.util.ArrayList;
import java.util.List;

public class UserUtility {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserDatabaseReference;
    private List<String> UserList;

    public void addUser(UserEntity userEntity, String mobileNumber, AddUserListener listener) {
        UserList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUserDatabaseReference = mFirebaseDatabase.getReference().child("User").child(mobileNumber);
        mUserDatabaseReference.setValue(userEntity);
        UserList.add(mobileNumber);
    }

    public void getUser(String mobileNumber, final GetUserListener getUserListener) {
        mUserDatabaseReference = mFirebaseDatabase.getInstance().getReference().child("User").child(mobileNumber);

        mUserDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserEntity userEntity = dataSnapshot.getValue(UserEntity.class);
                System.out.println("User:" + userEntity);
                getUserListener.onCompleteTask(userEntity);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void deleteUser(String mobileNumber, DeleteUserListener deleteUserListener) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference().child("User").child(mobileNumber).removeValue();
    }

    public void setUser(UserEntity userEntity, String mobileNumber, SetUserListener setUserListener) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mUserDatabaseReference = mFirebaseDatabase.getReference().child("User").child(mobileNumber);
        mUserDatabaseReference.setValue(userEntity);
    }

    public void getUserList(final GetUserListListener getUserListListener) {
        final List<String> userList = new ArrayList<>();
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUserListListener.onCompleteTask(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mUserDatabaseReference = mFirebaseDatabase.getInstance().getReference().child("User");
        mUserDatabaseReference.addValueEventListener(userListener);
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                userList.add(dataSnapshot.getKey());
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
        mUserDatabaseReference.addChildEventListener(childEventListener);


    }
}

