package com.test.camelspringbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.test.camelspringbootrest")
public class CamelSpringBootRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelSpringBootRestApplication.class, args);
    }

}