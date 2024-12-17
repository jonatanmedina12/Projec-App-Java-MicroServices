package com.circuitBreaker.companies_crud_fallback.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Company {
    private Long id;
    private String name;
    private String founder;
    private String logo;
    private LocalDate foundationDate;
    private List<WebSite> webSites;

    // Constructor por defecto
    public Company() {}

    // Constructor con todos los campos
    public Company(Long id, String name, String founder, String logo, LocalDate foundationDate, List<WebSite> webSites) {
        this.id = id;
        this.name = name;
        this.founder = founder;
        this.logo = logo;
        this.foundationDate = foundationDate;
        this.webSites = webSites;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public List<WebSite> getWebSites() {
        return webSites;
    }

    public void setWebSites(List<WebSite> webSites) {
        this.webSites = webSites;
    }

    // Builder estático
    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }

    // Clase Builder interna
    public static class CompanyBuilder {
        private Long id;
        private String name;
        private String founder;
        private String logo;
        private LocalDate foundationDate;
        private List<WebSite> webSites;

        public CompanyBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CompanyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CompanyBuilder founder(String founder) {
            this.founder = founder;
            return this;
        }

        public CompanyBuilder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public CompanyBuilder foundationDate(LocalDate foundationDate) {
            this.foundationDate = foundationDate;
            return this;
        }

        public CompanyBuilder webSites(List<WebSite> webSites) {
            this.webSites = webSites;
            return this;
        }

        public Company build() {
            return new Company(id, name, founder, logo, foundationDate, webSites);
        }
    }
}