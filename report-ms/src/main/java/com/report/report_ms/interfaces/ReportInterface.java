package com.report.report_ms.interfaces;

public interface ReportInterface {

    String makeReport (String name);

    String saveReport(String nameReport);
    void deleteReport(String nameReport);

}
