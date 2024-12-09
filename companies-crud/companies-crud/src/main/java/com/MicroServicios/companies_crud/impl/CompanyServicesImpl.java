package com.MicroServicios.companies_crud.impl;

import com.MicroServicios.companies_crud.entities.Category;
import com.MicroServicios.companies_crud.entities.Company;
import com.MicroServicios.companies_crud.repositories.CompanyRepository;
import com.MicroServicios.companies_crud.services.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class CompanyServicesImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    // Usar constructor injection en lugar de @Autowired
    public CompanyServicesImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company readByName(String name) {
        return this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company create(Company company) {
        // Asegurarse que el ID sea null para una nueva entidad
        company.setId(null);

        company.getWebSites().forEach(webSite -> {
            // Asegurarse que el ID de los websites también sea null
            webSite.setId(null);
            if(Objects.isNull(webSite.getCategory())) {
                webSite.setCategory(Category.NONE);
            }
        });

        return this.companyRepository.save(company);
    }

    @Override
    public Company update(Company company, String name) {
        var companyToUpdate = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        companyToUpdate.setFounder(company.getFounder());

        // Si hay websites para actualizar
        if (company.getWebSites() != null && !company.getWebSites().isEmpty()) {
            companyToUpdate.getWebSites().clear();
            company.getWebSites().forEach(webSite -> {
                webSite.setId(null); // Para nuevos websites
                if(Objects.isNull(webSite.getCategory())) {
                    webSite.setCategory(Category.NONE);
                }
            });
            companyToUpdate.getWebSites().addAll(company.getWebSites());
        }

        return this.companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        var companyToDelete = this.companyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        this.companyRepository.delete(companyToDelete);
    }
}