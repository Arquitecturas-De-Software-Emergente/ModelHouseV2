package com.upc.servicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.upc.coreentities.Security", "com.upc.coreentities.ServiceManagement", "com.upc.coreentities.SubscriptionAndPayment"})
@EnableJpaRepositories(basePackages = {"com.upc.coreservice.Repository.Security","com.upc.coreservice.Repository.ServiceManagement","com.upc.coreservice.Repository.SubscriptionAndPayment", "com.upc.coreservice.Service.Interfaces"})
@ComponentScan(basePackages = {"com.upc.coreservice", "com.upc.coreentities", "com.upc.servicemanagement"})
public class ServiceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceManagementApplication.class, args);
    }

}
