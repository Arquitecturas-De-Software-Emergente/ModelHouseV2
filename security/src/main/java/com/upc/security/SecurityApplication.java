package com.upc.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SecurityScheme(name = "acme", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition
@EnableJpaAuditing
@SpringBootApplication

@EntityScan(basePackages = {"com.upc.coreentities.Security", "com.upc.coreentities.ServiceManagement", "com.upc.coreentities.SubscriptionAndPayment"})
@EnableJpaRepositories(basePackages = {"com.upc.coreservice.Repository.Security","com.upc.coreservice.Repository.ServiceManagement","com.upc.coreservice.Repository.SubscriptionAndPayment", "com.upc.coreservice.Service.Interfaces"})
@ComponentScan(basePackages = {"com.upc.coreservice", "com.upc.coreentities", "com.upc.security"})

public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
