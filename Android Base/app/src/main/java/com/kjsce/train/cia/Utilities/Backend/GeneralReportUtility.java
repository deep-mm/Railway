package com.kjsce.train.cia.Utilities.Backend;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kjsce.train.cia.Entity.Report.GeneralReport;
import com.kjsce.train.cia.Listeners.AddGeneralReportListener;
import com.kjsce.train.cia.Listeners.GetGeneralReportListener;
import com.kjsce.train.cia.Listeners.RemoveGeneralReportListener;


public class GeneralReportUtility {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGeneralReportDatabaseReference;

    public void getGeneralReport(GeneralReport generalReport, final GetGeneralReportListener getGeneralReportListener)
    {
        mGeneralReportDatabaseReference= mFirebaseDatabase.getInstance().getReference().child("GeneralReport").child(generalReport.getTrainNumber()+generalReport.getDateTime());
        mGeneralReportDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              GeneralReport generalReport1 = dataSnapshot.getValue(GeneralReport.class);
                System.out.println("General Report:"+generalReport1);
                getGeneralReportListener.onCompleteTask(generalReport1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addGeneralReport(GeneralReport generalReport,AddGeneralReportListener listener)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGeneralReportDatabaseReference = mFirebaseDatabase.getReference().child("GeneralReport").child("G"+generalReport.getTrainNumber()+generalReport.getDateTime());
        mGeneralReportDatabaseReference.setValue(generalReport);

    }
    public void removeGeneralReport(GeneralReport generalReport,RemoveGeneralReportListener listener)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference().child("GeneralReport").child(generalReport.getTrainNumber() + generalReport.getDateTime()).removeValue();
    }
}