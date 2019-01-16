package org.entando.selenium.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(
        value={"notfound"},
        ignoreResourceNotFound = true)

public class PropertiesConfig {
    /**
     * Property placeholder configurer needed to process @Value annotations
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        System.out.println("Init propertyConfigurer");
        return new PropertySourcesPlaceholderConfigurer();
    }
}
