package com.example.amey.loginfirebase.Utilities.Backend;

import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Listener.AddDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListener;
import com.example.amey.loginfirebase.Listener.RemoveDetailedReportListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailedReportUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDetailedReportDatabaseReference;

    public void getDetailedReport(String trainNumber,String dateTime, final GetDetailedReportListener getDetailedReportListener)
    {
        mDetailedReportDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("DetailedReport").child(trainNumber+dateTime);

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

    public void addDetailedReport(DetailedReport detailedReport,AddDetailedReportListener listener)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDetailedReportDatabaseReference = mFirebaseDatabase.getReference().child("DetailedReport").child(detailedReport.getTrainNumber()+detailedReport.getDateTime());
        mDetailedReportDatabaseReference.setValue(detailedReport);
    }
    public void removeDetailedReport(DetailedReport detailedReport,RemoveDetailedReportListener listener)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference().child("DetailedReport").child(detailedReport.getTrainNumber() + detailedReport.getDateTime()).removeValue();

    }
}
