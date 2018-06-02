package com.example.amey.loginfirebase.Utilities.Backend;

import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListener;
import com.example.amey.loginfirebase.Listener.RemoveGeneralReportListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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
