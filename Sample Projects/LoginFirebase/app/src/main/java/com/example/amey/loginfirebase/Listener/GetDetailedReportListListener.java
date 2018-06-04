package com.example.amey.loginfirebase.Listener;

import com.example.amey.loginfirebase.Entity.Report.DetailedReport;

import java.util.List;

public interface GetDetailedReportListListener {
    void onCompleteTask(List<DetailedReport> detailedReportList);
}
