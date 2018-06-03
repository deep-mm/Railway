package com.example.amey.loginfirebase.Utilities.Backend;

import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddGeneralAudioListener;
import com.example.amey.loginfirebase.Listener.AddGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListener;
import com.example.amey.loginfirebase.Listener.RemoveGeneralReportListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GeneralReportUtility {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGeneralReportDatabaseReference;

    public void getGeneralReport(String trainNumber,String dateTime, final GetGeneralReportListener getGeneralReportListener)
    {
        mGeneralReportDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("GeneralReport").child(trainNumber + dateTime);
        mGeneralReportDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GeneralReport generalReport1 = dataSnapshot.getValue(GeneralReport.class);
                System.out.println("General Report:"+generalReport1);
                getGeneralReportListener.onCompleteTask(generalReport1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                getGeneralReportListener.onCompleteTask(null);
            }
        });
    }

    public void addGeneralReport(final GeneralReport generalReport,AddGeneralReportListener listener)
    {
        AudioUtility audioUtility = new AudioUtility();
        audioUtility.uploadGeneralAudio(generalReport.getGeneralCardList(), new AddGeneralAudioListener() {
            @Override
            public void onCompleteTask(List<GeneralCard> generalCardList) {
                generalReport.setGeneralCardList(generalCardList);
                getGeneralReport(generalReport.getTrainNumber(), generalReport.getDateTime(), new GetGeneralReportListener() {
                    @Override
                    public void onCompleteTask(GeneralReport databaseReport) {
                        if(databaseReport==null)
                        {
                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            mGeneralReportDatabaseReference = mFirebaseDatabase.getReference().child("GeneralReport").child(generalReport.getTrainNumber()+generalReport.getDateTime());
                            mGeneralReportDatabaseReference.setValue(generalReport);
                        }
                        else{
                            databaseReport = combineReports(databaseReport,generalReport);
                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            mGeneralReportDatabaseReference = mFirebaseDatabase.getReference().child("GeneralReport").child(databaseReport.getTrainNumber()+databaseReport.getDateTime());
                            mGeneralReportDatabaseReference.setValue(databaseReport);
                        }
                    }
                });
            }
        });
    }

    public GeneralReport combineReports(GeneralReport originalReport,GeneralReport newReport){
        for(int i=0;i<newReport.getGeneralCardList().size();i++){
            originalReport.addCard(newReport.getGeneralCardList().get(i));
        }
        return originalReport;
    }

    public void removeGeneralReport(GeneralReport generalReport, RemoveGeneralReportListener listener)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference().child("GeneralReport").child(generalReport.getTrainNumber() + generalReport.getDateTime()).removeValue();
    }

    public void getGeneralReportList(final GetGeneralReportListListener getGeneralReportListListener){

        final List<GeneralReport> generalReportList = new ArrayList<>();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getGeneralReportListListener.onCompleteTask(generalReportList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mGeneralReportDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("GeneralReport");
        mGeneralReportDatabaseReference.addValueEventListener(valueEventListener);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                GeneralReport generalReport = dataSnapshot.getValue(GeneralReport.class);
                generalReportList.add(generalReport);

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
        mGeneralReportDatabaseReference.addChildEventListener(childEventListener);


    }
}
