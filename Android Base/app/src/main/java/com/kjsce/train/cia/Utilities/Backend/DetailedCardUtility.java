package com.kjsce.train.cia.Utilities.Backend;


import com.kjsce.train.cia.Entity.Card.DetailedCard;
import com.kjsce.train.cia.Entity.Report.DetailedReport;
import com.kjsce.train.cia.Listeners.AddDetailedCardListener;
import com.kjsce.train.cia.Listeners.AddDetailedReportListener;
import com.kjsce.train.cia.Listeners.GetDetailedReportListener;
import com.kjsce.train.cia.Listeners.RemoveDetailedReportListener;

public class DetailedCardUtility
{
    public void addDetailedCard(final DetailedReport detailedReport, final DetailedCard card, final String bogeyNumber , final AddDetailedCardListener listener){

        final DetailedReportUtility detailedReportUtility = new DetailedReportUtility();
        detailedReportUtility.getDetailedReport(detailedReport.getTrainNumber(), detailedReport.getDateTime(), new GetDetailedReportListener() {
            @Override
            public void onCompleteTask(DetailedReport dR) {
                if(dR == null){
                    detailedReport.addDetailedCard(card,bogeyNumber);
                    detailedReportUtility.addDetailedReport(detailedReport, new AddDetailedReportListener() {
                        @Override
                        public void onCompleteTask(String result) {
                            listener.onCompleteTask(true);
                        }
                    });
                }
                else
                {
                    detailedReportUtility.removeDetailedReport(dR, new RemoveDetailedReportListener() {
                        @Override
                        public void onCompleteTask(String result) {

                        }
                    });
                    dR.addDetailedCard(card,bogeyNumber);
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
