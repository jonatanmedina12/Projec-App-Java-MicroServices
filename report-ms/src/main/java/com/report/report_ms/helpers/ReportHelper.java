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

    private static final String TEMPLATE = "%s was founded in %s by %s. The websites are: %s";

    public String readTemplate(Company company) {
        String websitesList = company.getWebSites() != null ?
                company.getWebSites().stream()
                        .map(site -> site.getName())
                        .collect(Collectors.joining(", ")) :
                "No websites available";

        return String.format(TEMPLATE,
                company.getName(),
                company.getFoundationDate().toString(),
                company.getFounder(),
                websitesList
        );
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