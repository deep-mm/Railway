package com.kjsce.train.cia.Utilities.Backend;


import com.kjsce.train.cia.Entity.Card.GeneralCard;
import com.kjsce.train.cia.Entity.Report.GeneralReport;
import com.kjsce.train.cia.Listeners.AddGeneralCardListener;
import com.kjsce.train.cia.Listeners.AddGeneralReportListener;
import com.kjsce.train.cia.Listeners.GetGeneralReportListener;
import com.kjsce.train.cia.Listeners.RemoveGeneralReportListener;

public class GeneralCardUtility {

    public void addGeneralCard(final GeneralReport generalReport, final GeneralCard generalCard, final AddGeneralCardListener listener){
        final GeneralReportUtility generalReportUtility=new GeneralReportUtility();
        generalReportUtility.getGeneralReport(generalReport.getTrainNumber(),generalReport.getDateTime(), new GetGeneralReportListener() {
            @Override
            public void onCompleteTask(GeneralReport gr) {
                if(gr==null)
                {
                    generalReport.addCard(generalCard);
                    generalReportUtility.addGeneralReport(generalReport, new AddGeneralReportListener() {
                        @Override
                        public void onCompleteTask(String result) {
                            listener.onCompleteTask(true);
                        }
                    });
                }
                else
                {
                    generalReportUtility.removeGeneralReport(gr, new RemoveGeneralReportListener() {
                        @Override
                        public void onCompleteTask(String result) {

                        }
                    });
                    gr.addCard(generalCard);
                    generalReportUtility.addGeneralReport(gr, new AddGeneralReportListener() {
                        @Override
                        public void onCompleteTask(String result) {
                            listener.onCompleteTask(true);
                        }
                    });
                    final GeneralReport newReport=gr;

                }
            }
        });

    }

}
