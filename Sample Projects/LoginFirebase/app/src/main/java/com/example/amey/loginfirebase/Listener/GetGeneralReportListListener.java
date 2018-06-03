package com.example.amey.loginfirebase.Listener;

import com.example.amey.loginfirebase.Entity.Report.GeneralReport;

import java.util.List;

public interface GetGeneralReportListListener {
    void onCompleteTask(List<GeneralReport> generalReportList);
}
