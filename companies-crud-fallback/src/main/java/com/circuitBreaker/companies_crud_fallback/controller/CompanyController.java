package com.circuitBreaker.companies_crud_fallback.controller;

import com.circuitBreaker.companies_crud_fallback.entities.Company;
import com.circuitBreaker.companies_crud_fallback.entities.WebSite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    private static final Company DEFAULT_COMPANY = Company.builder()
            .id(0L)
            .founder("Fallback Service")
            .name("Temporary Response")
            .logo("http://default-logo.com")
            .foundationDate(LocalDate.now())
            .webSites(List.of(new WebSite(1L, "No websites available")))
            .build();

    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        // Podríamos personalizar la respuesta basada en el nombre
        Company response = DEFAULT_COMPANY;
        response.setName("Company " + name + " (Fallback)");
        return ResponseEntity.ok(response);
    }
}