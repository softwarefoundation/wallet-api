package com.softwarefoundation.walletapi.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigWalletApi {

    @Value(value = "${spring.h2.console.enabled}")
    private String isConsoleH2Enabled;

    @Bean
    public Boolean isConsoleH2Enabled() {
        return Boolean.parseBoolean(isConsoleH2Enabled);
    }

}
