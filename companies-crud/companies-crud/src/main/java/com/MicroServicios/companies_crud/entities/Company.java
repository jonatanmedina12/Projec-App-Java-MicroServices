package com.MicroServicios.companies_crud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "company")


public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String founder;

    private String logo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate foundationDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_company", referencedColumnName = "id")
    private List<WebSite> webSites;

    // Constructor vacío
    public Company() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFounder() {
        return founder;
    }

    public String getLogo() {
        return logo;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public List<WebSite> getWebSites() {
        return webSites;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public void setWebSites(List<WebSite> webSites) {
        this.webSites = webSites;
    }

    // toString method
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", founder='" + founder + '\'' +
                ", logo='" + logo + '\'' +
                ", foundationDate=" + foundationDate +
                ", webSites=" + webSites +
                '}';
    }
}
