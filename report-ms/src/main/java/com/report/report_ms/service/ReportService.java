package com.report.report_ms.service;

import com.netflix.discovery.EurekaClient;
import com.report.report_ms.interfaces.ReportInterface;
import com.report.report_ms.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements ReportInterface {
    @Autowired
    private CompaniesRepository companiesRepository;



    @Override
    public String makeReport(String name) {


        return  this.companiesRepository.getByName(name).orElseThrow().getName();
    }

    @Override
    public String saveReport(String nameReport) {
        return "";
    }

    @Override
    public void deleteReport(String nameReport) {

    }
}
