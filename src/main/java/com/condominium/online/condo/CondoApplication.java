package com.condominium.online.condo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.condominium.online.condo"})
public class CondoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CondoApplication.class, args);
    }
}
