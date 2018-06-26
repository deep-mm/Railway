package com.kjsce.train.cia.Utilities;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Entities.UserNotificationEntity;
import com.kjsce.train.cia.Listener.OnNewNotificationAddedListener;
import com.kjsce.train.cia.Listener.OnNotificationListChangeListener;
import com.kjsce.train.cia.Listener.OnUserListChangeListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationUtility {

    private String mobileNumber;
    private List<UserNotificationEntity> userNotificationEntityList;
    private OnNewNotificationAddedListener onNewNotificationAddedListener;
    private OnNotificationListChangeListener onNotificationListChangeListener;
    private DatabaseReference mNotificationListDatabaseReference;
    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;

    public NotificationUtility(String mobileNumber,OnNewNotificationAddedListener onNewNotificationAddedListener,OnNotificationListChangeListener onNotificationListChangeListener)
    {
        this(mobileNumber);
        this.onNewNotificationAddedListener=onNewNotificationAddedListener;
        this.onNotificationListChangeListener=onNotificationListChangeListener;
    }
    public NotificationUtility(String mobileNumber,OnNewNotificationAddedListener onNewNotificationAddedListener)
    {
        this.mobileNumber=mobileNumber;
        this.onNewNotificationAddedListener=onNewNotificationAddedListener;

    }
    public NotificationUtility(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        userNotificationEntityList = new ArrayList<>();
        mNotificationListDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Notifications").child(mobileNumber);
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(onNotificationListChangeListener!=null)
                    onNotificationListChangeListener.OnDataChanged(userNotificationEntityList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                userNotificationEntityList.add(dataSnapshot.getValue(UserNotificationEntity.class));
                onNewNotificationAddedListener.onNotificationAdded(dataSnapshot.getValue(UserNotificationEntity.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                userNotificationEntityList.remove(dataSnapshot.getValue(UserNotificationEntity.class));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mNotificationListDatabaseReference.addValueEventListener(valueEventListener);
        mNotificationListDatabaseReference.addChildEventListener(childEventListener);
    }

    public NotificationUtility(String mobileNumber, OnNotificationListChangeListener onNotificationListChangeListener) {
        this(mobileNumber);
        this.onNotificationListChangeListener = onNotificationListChangeListener;
    }

    public OnNotificationListChangeListener getOnNotificationListChangeListener() {
        return onNotificationListChangeListener;
    }

    public void setOnNotificationListChangeListener(OnNotificationListChangeListener onNotificationListChangeListener) {
        this.onNotificationListChangeListener = onNotificationListChangeListener;
    }

    public void removeUpdating(){
        mNotificationListDatabaseReference.removeEventListener(valueEventListener);
        mNotificationListDatabaseReference.removeEventListener(childEventListener);
    }

    public void startUpdating(){
        mNotificationListDatabaseReference.addValueEventListener(valueEventListener);
        mNotificationListDatabaseReference.addChildEventListener(childEventListener);
    }

    public List<UserNotificationEntity> getNotificationList() {
        return userNotificationEntityList;
    }

    public void setNotificationList(List<UserNotificationEntity> userNotificationEntityList) {
        this.userNotificationEntityList = userNotificationEntityList;
    }

    public void sendNotification(String receiverMobileNumber,UserNotificationEntity userNotificationEntity){
        mNotificationListDatabaseReference.getParent().child(receiverMobileNumber).child(userNotificationEntity.getDateTime()+userNotificationEntity.getSender()).setValue(userNotificationEntity);
    }
    public void clearNotification()
    {
        userNotificationEntityList.clear();
        mNotificationListDatabaseReference.setValue(null);
    }
}

