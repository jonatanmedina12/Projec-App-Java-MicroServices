package com.GateWay.gate_way.beans;

import com.GateWay.gate_way.filters.AuthFilter;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;

@Configuration
public class GateWayBeans {

    private  AuthFilter authFilter;

    public GateWayBeans(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    private CircuitBreakerConfig createCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(20))
                .slidingWindowSize(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .build();
    }

    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/*")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("companiesCircuitBreaker")
                                        .setFallbackUri("forward:/companies-crud-fallback/fallback")))
                        .uri("http://localhost:7777")
                )
                .route(route -> route
                        .path("/report-ms/report/*")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("reportCircuitBreaker")
                                        .setFallbackUri("forward:/report-ms-fallback/fallback")))
                        .uri("http://localhost:7070")
                )
                .build();
    }

    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaOn(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/*")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("companiesCircuitBreaker")
                                        .setFallbackUri("forward:/companies-crud-fallback/fallback")))
                        .uri("lb://companies-crud")
                )
                .route(route -> route
                        .path("/report-ms/report/*")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("reportCircuitBreaker")
                                        .setFallbackUri("forward:/report-ms-fallback/fallback")))
                        .uri("lb://report-ms")
                )
                .build();
    }

    @Bean
    @Profile(value = "oauth2")
    public RouteLocator routeLocatorOauth2(RouteLocatorBuilder builder) {
        return builder
                .routes()
                // Ruta para el auth-server
                .route(route -> route
                        .path("/auth-server/**")
                        .uri("lb://auth-server")
                )
                // Ruta para companies-crud con autenticación
                .route(route -> route
                        .path("/companies-crud/company/*")
                        .filters(f -> f
                                .filter(authFilter)
                                .circuitBreaker(config -> config
                                        .setName("companiesCircuitBreaker")
                                        .setFallbackUri("forward:/companies-crud-fallback/fallback")))
                        .uri("lb://companies-crud")
                )
                // Ruta para report-ms con autenticación
                .route(route -> route
                        .path("/report-ms/report/*")
                        .filters(f -> f
                                .filter(authFilter)
                                .circuitBreaker(config -> config
                                        .setName("reportCircuitBreaker")
                                        .setFallbackUri("forward:/report-ms-fallback/fallback")))
                        .uri("lb://report-ms")
                )
                .build();
    }
}