package com.report.report_ms.models;


import java.io.Serializable;


public class WebSite implements Serializable {


    private Long id;

    private String name;


    private Category category;

    private String description;

    // Constructor vacío
    public WebSite() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getdescription() {
        return description;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    // toString method
    @Override
    public String toString() {
        return "WebSite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}