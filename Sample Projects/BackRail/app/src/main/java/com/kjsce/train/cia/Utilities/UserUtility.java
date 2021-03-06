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
import com.kjsce.train.cia.Listener.OnTrainListChangeListener;
import com.kjsce.train.cia.Listener.OnUserListChangeListener;
import com.kjsce.train.cia.Listener.SetUserListener;

import java.util.ArrayList;
import java.util.List;

public class UserUtility {

    private List<String> userList;
    private OnUserListChangeListener onUserListChangeListener;
    private DatabaseReference mUserListDatabaseReference;
    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;

    public UserUtility(){
        userList = new ArrayList<>();
        mUserListDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(onUserListChangeListener!=null)
                    onUserListChangeListener.OnDataChanged(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                userList.add(dataSnapshot.getKey());  //getValue(userentity.class)
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                userList.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mUserListDatabaseReference.addValueEventListener(valueEventListener);
        mUserListDatabaseReference.addChildEventListener(childEventListener);
    }

    public UserUtility(OnUserListChangeListener onUserListChangeListener) {
        this();
        this.onUserListChangeListener = onUserListChangeListener;
    }

    public OnUserListChangeListener getOnUserListChangeListener() {
        return onUserListChangeListener;
    }

    public void setOnUserListChangeListener(OnUserListChangeListener onUserListChangeListener) {
        this.onUserListChangeListener = onUserListChangeListener;
    }

    public void removeUpdating(){
        mUserListDatabaseReference.removeEventListener(valueEventListener);
        mUserListDatabaseReference.removeEventListener(childEventListener);
    }

    public void startUpdating(){
        mUserListDatabaseReference.addValueEventListener(valueEventListener);
        mUserListDatabaseReference.addChildEventListener(childEventListener);
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public void addUser(UserEntity userEntity){
        mUserListDatabaseReference.child(userEntity.getMobileNumber()).setValue(userEntity);
    }

    public void deleteUser(String mobileNumber){
        mUserListDatabaseReference.child(mobileNumber).setValue(null);
    }

    public void getUser(String mobileNumber, final GetUserListener getUserListener) {
        mUserListDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mobileNumber);

        mUserListDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
}

