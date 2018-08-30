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
 * This class represent the User Edit page
 * 
 * @version 1.01
 */
public class DTUserEditPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//input[contains(@name,'username')]")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement password;
    
    @FindBy(id = "passwordConfirm")
    private WebElement passwordConfirm;
    
    @FindBy(xpath = "//label[@for = 'reset']/../div/div[contains(@class, 'bootstrap-switch wrapper')]")
    private WebElement resetSwitch;
    
    @FindBy(xpath = "//label[@for = 'status']/../div/div[contains(@class, 'bootstrap-switch wrapper')]")
    private WebElement statusSwitch;
    
    @FindBy(css = "button.pull-right")
    private WebElement saveButton;
    
    
    public void setPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }
    
    public void setPasswordConfirm(String password) {
        this.passwordConfirm.clear();
        this.passwordConfirm.sendKeys(password);
    }
    
    public WebElement getPageTitle() {
        return this.pageTitle;
    }
    
    public WebElement getSaveButton() {
        return this.saveButton;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getPasswordConfirm() {
        return passwordConfirm;
    }

    public SwitchButton getResetSwitch() {
        return (new SwitchButton(resetSwitch));
    }

    public SwitchButton getStatusSwitch() {
        return (new SwitchButton(statusSwitch));
    }

    public WebElement getResetSwitchElement() {
        return resetSwitch;
    }

    public WebElement getStatusSwitchElement() {
        return statusSwitch;
    }
    
    public DTUserEditPage(WebDriver driver) {
        super(driver);
    }
    
}
