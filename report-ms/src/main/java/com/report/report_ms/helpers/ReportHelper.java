package com.report.report_ms.helpers;

import com.report.report_ms.models.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportHelper {

    @Value("${report.template}")
    private String reportTemplate;

    public String readTemplate(Company company) {

        return  this.reportTemplate
                .replace("{company}",company.getName())
                .replace("{foundation_date}",company.getFoundationDate().toString())
                .replace("{founder}",company.getWebSites().toString());

    }
    public List<String>getPlaceholdersFromTemplate(String template){
        var split = template.split("\\{");


        return Arrays.stream(split)
                .filter(line ->!line.isEmpty())
                .map(line ->{
                    var index = line.indexOf("}");
                    return line.substring(0,index);
                } )
                .collect(Collectors.toList());


    }

}