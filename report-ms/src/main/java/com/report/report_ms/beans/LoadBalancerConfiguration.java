package com.report.report_ms.beans;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration


public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext configurableApplicationContext){


        return  ServiceInstanceListSupplier
                .builder()
                .withBlockingDiscoveryClient()
                .build(configurableApplicationContext);


    }
}
