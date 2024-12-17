package com.report.report_ms.repositories;

import com.report.report_ms.models.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class CompaniesRepository {
    private final WebClient webClient;

    public CompaniesRepository(@Value("${companies.base.url}") String companiesBaseUrl) {
        this.webClient = WebClient.builder().baseUrl(companiesBaseUrl).build();
    }

    public Mono<Company> getByName(String name) {
        return webClient.get()
                .uri("/company/{name}", name)
                .retrieve()
                .bodyToMono(Company.class);
    }

    public Mono<Company> postByName(Company company) {
        return webClient.post()
                .uri("/company/")
                .bodyValue(company)
                .retrieve()
                .bodyToMono(Company.class);
    }

    public Mono<Void> deleteByName(String name) {
        return webClient.delete()
                .uri("/company/{name}", name)
                .retrieve()
                .bodyToMono(Void.class);
    }
}