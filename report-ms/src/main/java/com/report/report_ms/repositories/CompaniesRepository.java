package com.report.report_ms.repositories;


import com.report.report_ms.beans.LoadBalancerConfiguration;
import com.report.report_ms.models.Company;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "companies-crud")
@LoadBalancerClient(name = "companies-crud",configuration = LoadBalancerConfiguration.class)
public interface CompaniesRepository  {

    @GetMapping(path = "/companies-crud/company/{name}")
    Optional<Company> getByName(@PathVariable String name);






}
