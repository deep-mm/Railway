package com.example.amey.loginfirebase.Utilities.Backend;

import android.view.View;

import com.example.amey.loginfirebase.Entity.BogeyEntity;
import com.example.amey.loginfirebase.Entity.Card.DetailedCard;
import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddBogeyAudioListener;
import com.example.amey.loginfirebase.Listener.AddDetailedCardListener;
import com.example.amey.loginfirebase.Listener.AddDetailedReportListener;
import com.example.amey.loginfirebase.Listener.AddGeneralCardListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetLatestDetailedReportListener;
import com.example.amey.loginfirebase.Listener.RemoveDetailedReportListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailedReportUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDetailedReportDatabaseReference;

    public void getDetailedReport(final String trainNumber, final GetDetailedReportListener getDetailedReportListener)
    {
        LatestUtility latestUtility = new LatestUtility();
        latestUtility.getLatestDetailedReport(trainNumber, new GetLatestDetailedReportListener() {
            @Override
            public void onCompleteTask(String timestamp) {
                mDetailedReportDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("DetailedReport").child(trainNumber).child(timestamp);

                mDetailedReportDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DetailedReport detailedReport1 = dataSnapshot.getValue(DetailedReport.class);
                        System.out.println("Detailed Report:"+detailedReport1);
                        getDetailedReportListener.onCompleteTask(detailedReport1);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        getDetailedReportListener.onCompleteTask(null);
                    }
                });

            }
        });


    }

    public void addDetailedReport(final DetailedReport detailedReport,AddDetailedReportListener listener)
    {
       /* AudioUtility audioUtility = new AudioUtility();
        audioUtility.uploadBogeyAudio(detailedReport.getBogeyEntityList(), new AddBogeyAudioListener() {
            @Override
            public void onCompleteTask(List<BogeyEntity> bogeyEntityList) {
                detailedReport.setBogeyEntityList(bogeyEntityList);
                getDetailedReport(detailedReport.getTrainNumber(), detailedReport.getDateTime(), new GetDetailedReportListener() {
                    @Override
                    public void onCompleteTask(DetailedReport dR) {
                        if(dR == null){
                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            mDetailedReportDatabaseReference = mFirebaseDatabase.getReference().child("DetailedReport").child(detailedReport.getTrainNumber()).child(detailedReport.getDateTime());
                            mDetailedReportDatabaseReference.setValue(detailedReport);
                        }
                        else{
                            dR = combineReports(dR,detailedReport);
                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            mDetailedReportDatabaseReference = mFirebaseDatabase.getReference().child("DetailedReport").child(dR.getTrainNumber()).child(dR.getDateTime());
                            mDetailedReportDatabaseReference.setValue(dR);
                        }
                    }
                });
            }
        });*/


    }

    public DetailedReport combineReports(DetailedReport originalReport,DetailedReport newReport){
        for(int i=0;i<newReport.getBogeyEntityList().size();i++){
            boolean flag = false;
            for(int j=0;j<originalReport.getBogeyEntityList().size();j++){
                if(newReport.getBogeyEntityList().get(i).getBogeyNumber().equals(originalReport.getBogeyEntityList().get(j).getBogeyNumber())){
                    flag = true;
                    for(int k=0;k<newReport.getBogeyEntityList().get(i).getDetailedCard().size();k++)
                        originalReport.getBogeyEntityList().get(j).getDetailedCard().add(newReport.getBogeyEntityList().get(i).getDetailedCard().get(k));
                }
            }
            if(flag == false){
                originalReport.getBogeyEntityList().add(newReport.getBogeyEntityList().get(i));
            }
        }
        return originalReport;
    }

    public void removeDetailedReport(DetailedReport detailedReport,RemoveDetailedReportListener listener)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference().child("DetailedReport").child(detailedReport.getTrainNumber() + detailedReport.getDateTime()).removeValue();

    }

    public void getDetailedReportList(final GetDetailedReportListListener getDetailedReportListListener){

        final List<DetailedReport> detailedReportList = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getDetailedReportListListener.onCompleteTask(detailedReportList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDetailedReportDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("DetailedReport");
        mDetailedReportDatabaseReference.addValueEventListener(valueEventListener);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DetailedReport detailedReport = dataSnapshot.getValue(DetailedReport.class);
                detailedReportList.add(detailedReport);

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
        mDetailedReportDatabaseReference.addChildEventListener(childEventListener);


    }
}
