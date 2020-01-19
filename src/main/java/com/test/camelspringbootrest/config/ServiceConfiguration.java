package com.test.camelspringbootrest.config;

import com.test.camelspringbootrest.service.VATService;
import com.test.camelspringbootrest.service.VATServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public VATService getVATService() {
        return new VATServiceImpl();
    }

}

