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
import org.openqa.selenium.support.ui.Select;

/**
 * This class represent the User Add page
 * 
 * @version 1.01
 */
public class DTUserAddPage extends PageObject{
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//input[contains(@name,'username')]")
    private WebElement usernameField;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[@for = 'username']/../..//span[@class = 'help-block']/span")
    private WebElement usernameFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'password')]")
    private WebElement passwordField;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[@for = 'password']/../..//span[@class = 'help-block']/span")
    private WebElement passwordFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'passwordConfirm')]")
    private WebElement passwordConfirmField;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[@for = 'passwordConfirm']/../..//span[@class = 'help-block']/span")
    private WebElement passwordConfirmFieldError;
        
    @FindBy(xpath = "//select[contains(@name,'profileType')]")
    private WebElement profileType;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[@for = 'profileType']/../..//span[@class = 'help-block']/span")
    private WebElement profileTypeError;
    
    @FindBy(xpath = "//div[contains(@class, 'bootstrap-switch wrapper')]")
    private WebElement statusSwitch;
    
    @FindBy(xpath = "//button/span[text()='Save']/..")
    private WebElement saveButton;
    
    
    
    
    public WebElement getPageTitle() {
        return pageTitle;
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

    public WebElement getUsernameFieldError() {
        return usernameFieldError;
    }
    
    public void setUsernameField(String username) {
        this.usernameField.clear();
        this.usernameField.sendKeys(username);
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getPasswordFieldError() {
        return passwordFieldError;
    }
    
    public void setPasswordField(String password) {
        this.passwordField.clear();
        this.passwordField.sendKeys(password);
    }

    public WebElement getPasswordConfirmField() {
        return passwordConfirmField;
    }

    public WebElement getPasswordConfirmFieldError() {
        return passwordConfirmFieldError;
    }
    
    public void setPasswordConfirmField(String password) {
        this.passwordConfirmField.clear();
        this.passwordConfirmField.sendKeys(password);
    }

    public WebElement getProfileTypeError() {
        return profileTypeError;
    }

    public SwitchButton getStatusSwitch() {
        return (new SwitchButton(statusSwitch));
    }
    
    public Select getProfileTypeSelect() {
        return new Select(profileType);
    }
    
    public WebElement getProfileType() {
        return profileType;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }


    
    
    public DTUserAddPage(WebDriver driver) {
        super(driver);
    }
    
}
