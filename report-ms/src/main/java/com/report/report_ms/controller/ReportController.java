package com.report.report_ms.controller;


import com.report.report_ms.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "report")
public class ReportController {

    @Autowired
    private ReportService reportService;



    @GetMapping(path = "{name}")
    public ResponseEntity<Map<String,String>>getReport(@PathVariable String name){

        var report =Map.of("report",this.reportService.makeReport(name));

        return ResponseEntity.ok(report);

    }


}
