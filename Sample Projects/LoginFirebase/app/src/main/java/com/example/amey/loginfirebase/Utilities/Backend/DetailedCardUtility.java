package com.example.amey.loginfirebase.Utilities.Backend;

import com.example.amey.loginfirebase.Entity.Card.DetailedCard;
import com.example.amey.loginfirebase.Entity.Report.DetailedReport;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddDetailedCardListener;
import com.example.amey.loginfirebase.Listener.AddDetailedReportListener;
import com.example.amey.loginfirebase.Listener.GetDetailedReportListener;
import com.example.amey.loginfirebase.Listener.RemoveDetailedReportListener;

public class DetailedCardUtility
{
    public void addDetailedCard(final DetailedReport detailedReport, final DetailedCard card,final String bogeyNumber ,final AddDetailedCardListener listener) {

        final DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
        detailedReportUtility.getDetailedReport(detailedReport.getTrainNumber(), detailedReport.getDateTime(), new GetDetailedReportListener() {
            @Override
            public void onCompleteTask(DetailedReport dR) {
                if (dR == null) {
                    detailedReport.addDetailedCard(card, bogeyNumber);
                    detailedReportUtility.addDetailedReport(detailedReport, new AddDetailedReportListener() {
                        @Override
                        public void onCompleteTask(String result) {
                            listener.onCompleteTask(true);
                        }
                    });
                } else {
                    detailedReportUtility.removeDetailedReport(dR, new RemoveDetailedReportListener() {
                        @Override
                        public void onCompleteTask(String result) {

                        }
                    });
                    dR.addDetailedCard(card, bogeyNumber);
                    detailedReportUtility.addDetailedReport(dR, new AddDetailedReportListener() {
                        @Override
                        public void onCompleteTask(String result) {
                            listener.onCompleteTask(true);
                        }
                    });
                }
            }
        });
    }
}
