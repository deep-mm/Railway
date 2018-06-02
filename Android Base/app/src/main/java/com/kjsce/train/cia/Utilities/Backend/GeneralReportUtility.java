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
        getGeneralReport(generalReport.getTrainNumber(), generalReport.getDateTime(), new GetGeneralReportListener() {
            @Override
            public void onCompleteTask(GeneralReport databaseReport) {
                if(databaseReport==null)
                {
                    mFirebaseDatabase = FirebaseDatabase.getInstance();
                    mGeneralReportDatabaseReference = mFirebaseDatabase.getReference().child("GeneralReport").child(generalReport.getTrainNumber()+generalReport.getDateTime());
                    mGeneralReportDatabaseReference.setValue(generalReport);
                }

            }
        });


    }
    public void removeGeneralReport(GeneralReport generalReport, RemoveGeneralReportListener listener)
    {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference().child("GeneralReport").child(generalReport.getTrainNumber() + generalReport.getDateTime()).removeValue();
    }
}
