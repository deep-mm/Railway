package com.kjsce.train.cia.Utilities.Backend;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entity.LatestEntity;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Entity.Report.GeneralReport;
import com.kjsce.train.cia.Listeners.GetLatestDetailedReportListener;
import com.kjsce.train.cia.Listeners.GetLatestGeneralReportListener;
import com.kjsce.train.cia.Listeners.SetLatestDetailedReportListener;
import com.kjsce.train.cia.Listeners.SetLatestGeneralReportListener;

public class LatestUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mLatestDatabaseReference;

    public void setLatestDetailedReport(DetailedReport detailedReport, SetLatestDetailedReportListener setLatestDetailedReportListener){
        LatestEntity latestEntity = new LatestEntity();
        latestEntity.setLatestTimestamp(detailedReport.getDateTime());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mLatestDatabaseReference = mFirebaseDatabase.getReference().child("Latest").child("DetailedReport").child(detailedReport.getTrainNumber());
        mLatestDatabaseReference.setValue(latestEntity);
    }

    public void getLatestDetailedReport(String trainNumber, final GetLatestDetailedReportListener getLatestDetailedReportListener){
        mLatestDatabaseReference = mFirebaseDatabase.getInstance().getReference().child("Latest").child("DetailedReport").child(trainNumber);
        mLatestDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LatestEntity latestEntity = dataSnapshot.getValue(LatestEntity.class);
                getLatestDetailedReportListener.onCompleteTask(latestEntity.getLatestTimestamp());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void setLatestGeneralReport(GeneralReport generalReport, SetLatestGeneralReportListener setLatestGeneralReportListener){
        LatestEntity latestEntity = new LatestEntity();
        latestEntity.setLatestTimestamp(generalReport.getDateTime());


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mLatestDatabaseReference = mFirebaseDatabase.getReference().child("Latest").child("GeneralReport").child(generalReport.getTrainNumber());
        mLatestDatabaseReference.setValue(latestEntity);
    }

    public void getLatestGeneralReport(String trainNumber, final GetLatestGeneralReportListener getLatestGeneralReportListener){
        mLatestDatabaseReference = mFirebaseDatabase.getInstance().getReference().child("Latest").child("GeneralReport").child(trainNumber);
        mLatestDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LatestEntity latestEntity = dataSnapshot.getValue(LatestEntity.class);
                getLatestGeneralReportListener.onCompleteTask(latestEntity.getLatestTimestamp());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}