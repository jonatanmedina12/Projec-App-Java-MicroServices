package com.report.report_ms.repositories;

import com.report.report_ms.beans.LoadBalancerConfiguration;
import com.report.report_ms.models.Company;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "companies-crud", url = "http://localhost:8080/companies-crud")
public interface CompaniesRepository {
    @GetMapping("/company/{name}")
    Optional<Company> getByName(@PathVariable("name") String name);

    @PostMapping(path = "/company/")
    Optional<Company>postByName(@RequestBody Company company);


    @DeleteMapping(path = "/company/{name}")
    void deleteByName(@PathVariable String name );

}