package com.kjsce.train.cia.Utilities.Backend;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Listeners.AddDetailedReportListener;
import com.kjsce.train.cia.Listeners.GetDetailedReportListener;
import com.kjsce.train.cia.Listeners.RemoveDetailedReportListener;

public class DetailedReportUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDetailedReportDatabaseReference;

    public void getDetailedReport(DetailedReport detailedReport, final GetDetailedReportListener getDetailedReportListener)
    {
       mDetailedReportDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("DetailedReport").child("D"+detailedReport.getTrainNumber()+detailedReport.getDateTime());

        mDetailedReportDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DetailedReport detailedReport1 = dataSnapshot.getValue(DetailedReport.class);
                System.out.println("Detailed Report:"+detailedReport1);
                getDetailedReportListener.onCompleteTask(detailedReport1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
