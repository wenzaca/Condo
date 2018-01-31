package com.condominium.online.condo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(scanBasePackages = {"com.condominium.online.condo"})
@EnableEurekaServer
public class CondoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CondoApplication.class, args);
    }
}
