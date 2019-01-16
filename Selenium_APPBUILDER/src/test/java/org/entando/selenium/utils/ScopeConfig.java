package org.entando.selenium.utils;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ScopeConfig {
    @Bean
    public TestScope testScope() {
        return new TestScope();
    }
    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        System.out.println("Init customScopeConfigurer");
        CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("test", testScope());
        scopeConfigurer.setScopes(scopes);
        return scopeConfigurer;
    }

}
