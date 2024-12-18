package com.report.report_ms.service;

import com.report.report_ms.helpers.ReportHelper;
import com.report.report_ms.interfaces.ReportInterface;
import com.report.report_ms.models.Company;
import com.report.report_ms.models.WebSite;
import com.report.report_ms.repositories.CompaniesFallbackRepository;
import com.report.report_ms.repositories.CompaniesRepository;
import com.report.report_ms.streams.ReportPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.stream.Stream;

@Service
public class ReportService implements ReportInterface {
    @Autowired
    private CompaniesRepository companiesRepository;
    @Autowired
    private ReportHelper reportHelper;
    @Autowired
    private CompaniesFallbackRepository  companiesFallbackRepository;


    @Autowired
    private Resilience4JCircuitBreakerFactory resilience4JCircuitBreakerFactory;

    @Autowired
    private ReportPublisher reportPublisher;


    private Logger log = LoggerFactory.getLogger(ReportService.class);


    @Override
    public String makeReport(String name) {
        log.info("Making report for company: {}", name);
        var circuitBreaker = this.resilience4JCircuitBreakerFactory.create("companies-circuitbreaker");

        return circuitBreaker.run(
                () -> {
                    log.info("Attempting to get company data from main service");
                    return this.makeReportMain(name);
                },
                throwable -> {
                    log.error("Error calling main service: {}", throwable.getMessage());
                    return this.makeReportFallBack(name, throwable);
                }
        );
    }
    @Override
    public String saveReport(String nameReport) {
        var format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var placeHolders = this.reportHelper.getPlaceholdersFromTemplate(nameReport);

        var webSites = Stream.of(placeHolders.get(3))
                .map(website -> {
                    WebSite site = new WebSite();
                    site.setName(website);
                    return site;
                })
                .toList();

        Company company = new Company();
        company.setName(placeHolders.get(0));
        company.setLogo("Logo");
        company.setFounder(placeHolders.get(2));

        try {
            company.setFoundationDate(LocalDate.parse(placeHolders.get(1), format));
        } catch (Exception e) {
            // Si falla el parseo con el formato específico, intentar con el formato por defecto
            company.setFoundationDate(LocalDate.parse(placeHolders.get(1)));
        }

        company.setWebSites(webSites);
        this.reportPublisher.publishReport(nameReport);
        this.companiesRepository.postByName(company);

        return "saved";
    }

    @Override
    public void deleteReport(String nameReport) {

        this.companiesRepository.deleteByName(nameReport);

    }



    public String makeReportMain(String name) {
        return reportHelper.readTemplate(
                this.companiesRepository.getByName(name)
                        .blockOptional()
                        .orElseThrow(() -> new RuntimeException("Company not found: " + name))
        );
    }

    public String makeReportFallBack(String name, Throwable error) {
        Company fallbackCompany = this.companiesFallbackRepository.getCompany(name);
        String report = reportHelper.readTemplate(fallbackCompany);
        return report + " [FALLBACK RESPONSE]";
    }
}
