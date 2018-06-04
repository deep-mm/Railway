package com.kjsce.train.cia.Listeners;

import com.kjsce.train.cia.Entity.Report.DetailedReport;

import java.util.List;

public interface GetDetailedReportListListener {
    void onCompleteTask(List<DetailedReport> detailedReportList);

}
