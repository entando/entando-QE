/*
Copyright 2015-Present Entando Inc. (http://www.entando.com) All rights reserved.
This library is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 2.1 of the License, or (at your option)
any later version.
This library is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.
 */
package org.entando.selenium.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.entando.selenium.pages.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 
 * 
 * @version 1.01
 */
@Configuration
public class AppConfig {
    
    private static final boolean HEADLESS = false;

    /**
     * browserstack credentials
     */

    private static final String USERNAME = "";
    private static final String AUTOMATE_KEY = "";
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    
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
    public WebDriver webDriver() throws MalformedURLException{
        //WebDriver driver;
        /*
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(Platform.ANY);
        WebDriver driver =  new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        return driver;
        */
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "70.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x1024");
        caps.setCapability("project", "appbuilder-5.1.0");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("browserstack.networkLogs", "true");
        caps.setCapability("browserstack.appiumLogs", "false");
        caps.setCapability("browserstack.selenium_version", "2.52.0");

        if (HEADLESS)
        {
            //WebDriver driver = new ChromeDriver();
            //WebDriver driver = new FirefoxDriver();
            ChromeOptions options = new ChromeOptions();
            //FirefoxOptions options = new FirefoxOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
            WebDriver driver = new ChromeDriver(options);
            return driver;
        }
        else
        {
            //WebDriver driver = new ChromeDriver();
            //WebDriver driver = new FirefoxDriver();
            WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
            return driver;
        }
    } 
    
    @Bean
    @Scope("prototype")
    public DTCategoriesPage dTCategoriesPage(WebDriver driver){
        return new DTCategoriesPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTCategoriesAddPage dTCategoriesAddPage(WebDriver driver){
        return new DTCategoriesAddPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTCategoriesDetailsPage dTCategoriesDetailsPage(WebDriver driver){
        return new DTCategoriesDetailsPage(driver);
    } 
    
    @Bean
    @Scope("prototype")
    public DTDashboardPage dTDashboardPage(WebDriver driver){
        return new DTDashboardPage(driver);
    }
            
    @Bean
    @Scope("prototype")
    public DTDatabasePage dTDatabasePage(WebDriver driver){
        return new DTDatabasePage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTDataModelsPage dTDataModelsPage(WebDriver driver){
        return new DTDataModelsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTDataModelsAddPage dTDataModelsAddPage(WebDriver driver){
        return new DTDataModelsAddPage(driver);
    }    
    
    @Bean
    @Scope("prototype")
    public DTDataTypesPage dTDataTypesPage(WebDriver driver){
        return new DTDataTypesPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTDataTypesAddPage dTDataTypeAddPage(WebDriver driver){
        return new DTDataTypesAddPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFileBrowserCreateFolderPage dTFileBrowserCreateFolderPage(WebDriver driver){
        return new DTFileBrowserCreateFolderPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFileBrowserCreateTextFilePage dTFileBrowserCreateTextFilePage(WebDriver driver){
        return new DTFileBrowserCreateTextFilePage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFileBrowserPage dTFileBrowserPage(WebDriver driver){
        return new DTFileBrowserPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFileBrowserUploadPage dTFileBrowserUploadPage(WebDriver driver){
        return new DTFileBrowserUploadPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFragmentsDetailsPage dTFragmentsDetailsPage(WebDriver driver){
        return new DTFragmentsDetailsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFragmentEditPage dTFragmentEditPage(WebDriver driver){
        return new DTFragmentEditPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFragmentNewPage dTFragmentNewPage(WebDriver driver){
        return new DTFragmentNewPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTFragmentPage dTFragmentPage(WebDriver driver){
        return new DTFragmentPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTUserGroupAddPage dTGroupAddPage(WebDriver driver){
        return new DTUserGroupAddPage(driver);
    }

    
    @Bean
    @Scope("prototype")
    public DTUserGroupDetailsPage dTGroupDetailsPage(WebDriver driver){
        return new DTUserGroupDetailsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTUserGroupEditPage dTGroupEditPage(WebDriver driver){
        return new DTUserGroupEditPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTUserGroupsPage dTGroupsPage(WebDriver driver){
        return new DTUserGroupsPage(driver);
    }
    
            
    @Bean
    @Scope("prototype")
    public DTLabelsAndLanguagesPage dTLabelsAndLanguagesPage(WebDriver driver){
        return new DTLabelsAndLanguagesPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTLoginPage dTLoginPage(WebDriver driver){
        return new DTLoginPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTPageModelsPage dTPageModelsPage(WebDriver driver){
        return new DTPageModelsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTPageModelsAddPage dTPageModelsAddPage(WebDriver driver){
        return new DTPageModelsAddPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTPageModelsDetailsPage dTPageModelsDetailsPage(WebDriver driver){
        return new DTPageModelsDetailsPage(driver);
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
    public DTPageAddPage dTPageAddPage(WebDriver driver){
        return new DTPageAddPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTPageClonePage dTPageClonePage(WebDriver driver){
        return new DTPageClonePage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTPageDetailsPage dTPageDetailsPage(WebDriver driver){
        return new DTPageDetailsPage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTPageSettingsPage dTPageSettingsPage(WebDriver driver){
        return new DTPageSettingsPage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTPageConfigurePage dTPageConfigurePage(WebDriver driver){
        return new DTPageConfigurePage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTReloadConfigurationPage dTReloadConfigurationPage(WebDriver driver){
        return new DTReloadConfigurationPage(driver);
    }        
    
    @Bean
    @Scope("prototype")
    public DTSystemLabelsPage dTSystemLabelsPage(WebDriver driver){
        return new DTSystemLabelsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTSystemLabelsAddPage dTSystemLabelsAddPage(WebDriver driver){
        return new DTSystemLabelsAddPage(driver);
    }
         
    @Bean
    @Scope("prototype")
    public DTUserAddPage dTUserAddPage(WebDriver driver){
        return new DTUserAddPage(driver);
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
    public DTUserRolesPage dTUserRolesPage(WebDriver driver){
        return new DTUserRolesPage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTUserRoleAddPage dTUserRoleAddPage(WebDriver driver){
        return new DTUserRoleAddPage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTUserRoleEditPage dTUserRoleEditPage(WebDriver driver){
        return new DTUserRoleEditPage(driver);
    }
    
        
    @Bean
    @Scope("prototype")
    public DTUserRoleDetailsPage dTUserRoleDetailsPage(WebDriver driver){
        return new DTUserRoleDetailsPage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTUserProfileTypePage dTUserProfileTypePage(WebDriver driver){
        return new DTUserProfileTypePage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTUserProfileTypeAddPage dTUserProfileTypeAddPage(WebDriver driver){
        return new DTUserProfileTypeAddPage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTUserProfileTypeEditPage dTUserProfileTypeEditPage(WebDriver driver){
        return new DTUserProfileTypeEditPage(driver);
    }
    
    
    @Bean
    @Scope("prototype")
    public DTUserRestrictionsPage dTUserRestrictionsPage(WebDriver driver){
        return new DTUserRestrictionsPage(driver);
    }
    
    @Bean
    @Scope("prototype")
    public DTWidgetAddPage dTWidgetAddPage(WebDriver driver){
        return new DTWidgetAddPage(driver);
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
