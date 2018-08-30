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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represent the Add User Profile Type page
 * 
 * @version 1.01
 */
public class DTUserProfileTypeAddPage extends PageObject {
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//input[contains(@name,'name')]")
    private WebElement nameField;
    
    @FindBy(xpath = "//input[contains(@name,'name')]/../span/span")
    private WebElement nameFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'code')]")
    private WebElement codeField;
    
    @FindBy(xpath = "//input[contains(@name,'code')]/../span/span")
    private WebElement codeFieldError;
    
    @FindBy(xpath = "//select[contains(@name,'type')]")
    private WebElement type;
    
    @FindBy(xpath = "//button[contains(.,'Add')]")
    private WebElement addButton;
    

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

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getNameFieldError() {
        return nameFieldError;
    }

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getCodeFieldError() {
        return codeFieldError;
    }

    public void setNameField(String name) {
        this.nameField.clear();
        this.nameField.sendKeys(name);
    }

    public void setCodeField(String code) {
        this.codeField.clear();
        this.codeField.sendKeys(code);
    }

    public Select getType() {
        return new Select(type);
    }

    public WebElement getAddButton() {
        return addButton;
    }
    
    
    
    public DTUserProfileTypeAddPage(WebDriver driver) {
        super(driver);
    }
}
