/**
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

/**
 * This class prepresent the Dashboard Page
 * 
 * @version 1.01
 */
package org.entando.selenium.pages;

import static java.lang.Thread.sleep;
import org.entando.selenium.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DTDashboardPage extends PageObject {
    @FindBy(linkText = "Dashboard")
    private WebElement dashBoardTab;
    @FindBy(linkText = "Page Designer")
    private WebElement pageDesignerTab;
    @FindBy(linkText = "UX Patterns")
    private WebElement uxPatternTab;
    @FindBy(linkText = "Integrations")
    private WebElement integrationTab;
    @FindBy(linkText = "Data")
    private WebElement dataTab;
    @FindBy(linkText = "Configuration")
    private WebElement configurationTab;
    @FindBy(linkText = "User Management")
    private WebElement userManagementTab;
    
    
    
    public WebElement getDashboardTab() {
        return dashBoardTab;
    }
    
    public WebElement getPageDesignerTab() {
        return pageDesignerTab;
    }
    
    public WebElement getUxPatternTab() {
        return uxPatternTab;
    }
    
    public WebElement getIntegrationTab() {
        return integrationTab;
    }
    
    public WebElement getDataTab() {
        return dataTab;
    }
    
    public WebElement getConfigurationTab() {
        return configurationTab;
    }
    
    public WebElement getUserManagementTab() {
        return userManagementTab;
    }
    
    
 

    private void selectTab(String tabName) {
        switch (tabName) {
            case "Dashboard": {
                Utils.waitUntilIsVisible(driver, dashBoardTab);
                dashBoardTab.click();
                break;
            }
            case "Page Designer": {
                Utils.waitUntilIsVisible(driver, pageDesignerTab);
                pageDesignerTab.click();
                break;
            }
            case "UX Patterns": {
                Utils.waitUntilIsVisible(driver, uxPatternTab);
                uxPatternTab.click();
                break;
            }
            case "Integrations": {
                Utils.waitUntilIsVisible(driver, integrationTab);
                integrationTab.click();
                break;
            }
            case "Data": {
                Utils.waitUntilIsVisible(driver, dataTab);
                dataTab.click();
                break;
            }
            case "Configuration": {
                Utils.waitUntilIsVisible(driver, configurationTab);
                configurationTab.click();
                break;
            }
            case "User Management": {
                Utils.waitUntilIsVisible(driver, userManagementTab);
                userManagementTab.click();
                break;
            }
        }
    }

    public void SelectSecondOrderLink(String TabName, String Link){
        this.selectTab(TabName);
        Utils.waitUntilIsVisible(driver, driver.findElement(By.linkText(Link)));
        
        WebElement secondOrderLink = driver.findElement(By.linkText(Link));
        
        Utils.waitUntilIsVisible(driver, secondOrderLink);
        secondOrderLink.click();
    }
    
    public void SelectSecondOrderLinkWithSleep(String TabName, String Link) throws InterruptedException {
        this.selectTab(TabName);
        sleep(500);
        WebElement secondOrderLink = driver.findElement(By.linkText(Link));
        secondOrderLink.click();
    }

    public DTDashboardPage(WebDriver driver) {
        super(driver);
    }
}
