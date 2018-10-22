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
package org.entando.selenium.pages;

import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.pageParts.SwitchButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the User Restriction page
 * 
 * @version 1.01
 */
public class DTUserRestrictionsPage extends PageObject {
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//label[@for = 'passwordAlwaysActive']/../..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement passwordAlwaysActiveSwitch;
    
    @FindBy(xpath = "//label[@for = 'enableGravatarIntegration']/../..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement enableGravatarIntegrationSwitch;
    
    @FindBy(xpath = "//input[@name= 'maxMonthsPasswordValid']")
    private WebElement maxMonthsPasswordValid;
            
    @FindBy(xpath = "//input[@name= 'lastAccessPasswordExpirationMonths']")
    private WebElement lastAccessPasswordExpirationMonths;
    
    

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getPasswordAlwaysActiveSwitch() {
        return passwordAlwaysActiveSwitch;
    }

    public WebElement getEnableGravatarIntegrationSwitch() {
        return enableGravatarIntegrationSwitch;
    }

    public WebElement getMaxMonthsPasswordValid() {
        return maxMonthsPasswordValid;
    }

    public WebElement getLastAccessPasswordExpirationMonths() {
        return lastAccessPasswordExpirationMonths;
    }
    
    public SwitchButton getPasswordAlwaysActiveSwitchButton() {
        return new SwitchButton(passwordAlwaysActiveSwitch);
    }

    public SwitchButton getEnableGravatarIntegrationSwitchButton() {
        return new SwitchButton(enableGravatarIntegrationSwitch);
    }
    
    
    public DTUserRestrictionsPage(WebDriver driver) {
        super(driver);
    }
    
}
