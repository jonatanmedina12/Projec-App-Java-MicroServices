package com.MicroServicios.companies_crud.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenAPIConfig {


    @Bean

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Company API")
                        .version("1.0")
                        .description("API para gestionar compañías y sitios web")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tu@email.com"))
                );
    }
}
