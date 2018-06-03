package com.kjsce.train.cia.Listeners;

import com.kjsce.train.cia.Entity.Report.GeneralReport;

import java.util.List;

public interface GetGeneralReportListListener {
    void onCompleteTask(List<GeneralReport> generalReportList);

}
