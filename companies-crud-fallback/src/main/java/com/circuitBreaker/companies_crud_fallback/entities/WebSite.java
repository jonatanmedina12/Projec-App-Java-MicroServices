package com.circuitBreaker.companies_crud_fallback.entities;

public class WebSite {
    private Long id;
    private String name;

    public WebSite() {}

    public WebSite(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}