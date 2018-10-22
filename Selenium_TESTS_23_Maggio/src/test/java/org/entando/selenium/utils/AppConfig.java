/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.utils;

import java.util.HashMap;
import java.util.Map;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTDataTypesPage;
import org.entando.selenium.pages.DTFragmentEditPage;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.pages.DTGroupAddPage;
import org.entando.selenium.pages.DTGroupDeletePage;
import org.entando.selenium.pages.DTGroupDetailsPage;
import org.entando.selenium.pages.DTGroupEditPage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.pages.DTPageEditPage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.pages.DTUserDetailsPage;
import org.entando.selenium.pages.DTUserManageAuthorityPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.pages.DTUserEditPage;
import org.entando.selenium.pages.DTWidgetEditPage;
import org.entando.selenium.pages.DTWidgetPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author leobel
 */
@Configuration
public class AppConfig {
    
    @Bean
    public TestScope testScope() {
        return new TestScope();
    }
    
    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("test", testScope());
        scopeConfigurer.setScopes(scopes);
        return scopeConfigurer;
    }
    
    @Bean
    @Scope("test")
    public WebDriver webDriver(){
        return new ChromeDriver();
    } 
    
    @Bean
    @Scope("prototype")
    public DTDashboardPage dTDashboardPage(WebDriver driver){
        return new DTDashboardPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTDataTypesPage dTDataTypesPage(WebDriver driver){
        return new DTDataTypesPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFragmentEditPage dTFragmentEditPage(WebDriver driver){
        return new DTFragmentEditPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFragmentPage dTFragmentPage(WebDriver driver){
        return new DTFragmentPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTGroupAddPage dTGroupAddPage(WebDriver driver){
        return new DTGroupAddPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTGroupDeletePage dTGroupDeletePage(WebDriver driver){
        return new DTGroupDeletePage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTGroupDetailsPage dTGroupDetailsPage(WebDriver driver){
        return new DTGroupDetailsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTGroupEditPage dTGroupEditPage(WebDriver driver){
        return new DTGroupEditPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTGroupsPage dTGroupsPage(WebDriver driver){
        return new DTGroupsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTLoginPage dTLoginPage(WebDriver driver){
        return new DTLoginPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTPageEditPage dTPageEditPage(WebDriver driver){
        return new DTPageEditPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTPageTreePage dTPageTreePage(WebDriver driver){
        return new DTPageTreePage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTUserDetailsPage dTUserDetailsPage(WebDriver driver){
        return new DTUserDetailsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTUserEditPage dTUserEditPage(WebDriver driver){
        return new DTUserEditPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTUserManageAuthorityPage dTUserManageAuthorityPage(WebDriver driver){
        return new DTUserManageAuthorityPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTUsersPage dTUsersPage(WebDriver driver){
        return new DTUsersPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTWidgetEditPage dTWidgetEditPage(WebDriver driver){
        return new DTWidgetEditPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTWidgetPage dTWidgetPage(WebDriver driver){
        return new DTWidgetPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public Utils utils(){
        return new Utils();
    }
    
}
