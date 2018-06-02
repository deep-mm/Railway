package com.example.amey.loginfirebase.Utilities.Backend;

import com.example.amey.loginfirebase.Entity.Card.GeneralCard;
import com.example.amey.loginfirebase.Entity.Report.GeneralReport;
import com.example.amey.loginfirebase.Listener.AddGeneralCardListener;
import com.example.amey.loginfirebase.Listener.AddGeneralReportListener;
import com.example.amey.loginfirebase.Listener.GetGeneralReportListener;
import com.example.amey.loginfirebase.Listener.RemoveGeneralReportListener;

public class GeneralCardUtility {

    public void addGeneralCard(final GeneralReport generalReport, final GeneralCard generalCard,final AddGeneralCardListener listener){
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
