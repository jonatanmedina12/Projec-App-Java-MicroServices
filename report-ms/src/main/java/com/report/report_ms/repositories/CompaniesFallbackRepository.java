package com.report.report_ms.repositories;

import com.report.report_ms.models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class CompaniesFallbackRepository {

    private final WebClient webClient;
    private final String fallbackUri;

    public CompaniesFallbackRepository(WebClient webClient,
                                       @Value("${fallback.uri}") String fallbackUri) {
        this.webClient = webClient;
        this.fallbackUri = fallbackUri;
    }

    public Company getCompany(String name) {
        return webClient.get()
                .uri(fallbackUri + "/company/" + name)
                .retrieve()
                .bodyToMono(Company.class)
                .block();
    }


}
