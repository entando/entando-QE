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

/**
 * This class represent the Edit User Group page
 * 
 * @version 1.01
 */
public class DTUserGroupEditPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Name']")
    private WebElement nameLabel;
    
    @FindBy(xpath = "//input[contains(@name, 'name')]")
    private WebElement name;
    
    @FindBy(xpath = "//input[contains(@name,'name')]/../span/span")
    private WebElement nameFieldError;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Code']")
    private WebElement codeLabel;
    
    @FindBy(xpath = "//input[contains(@name, 'code')]")
    private WebElement code;
    
    @FindBy(xpath = "//input[contains(@name,'code')]/../span/span")
    private WebElement codeFieldError;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Name']/../sup/i")
    private WebElement nameRequiredAsterisk;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Code']/../sup/i")
    private WebElement codeRequiredAsterisk;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Name']/../button")
    private WebElement infoNameButton;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Code']/../button")
    private WebElement infoCodeButton;
    
    @FindBy(xpath = "//div[contains(., 'You can insert max 50')]")
    private WebElement nameTooltip;
    
    @FindBy(xpath = "//div[contains(., 'You can insert max 20')]")
    private WebElement codeTooltip;
    
    @FindBy (xpath = "//button[contains(.,'Add')]")
    private WebElement saveButton;

    
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getNameLabel() {
        return nameLabel;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getCodeLabel() {
        return codeLabel;
    }

    public WebElement getCode() {
        return code;
    }

    public WebElement getNameTooltip() {
        return nameTooltip;
    }
    
    public WebElement getCodeTooltip() {
        return codeTooltip;
    }

    public void setName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getNameRequiredAsterisk() {
        return nameRequiredAsterisk;
    }

    public WebElement getCodeRequiredAsterisk() {
        return codeRequiredAsterisk;
    }

    public WebElement getNameFieldError() {
        return nameFieldError;
    }

    public WebElement getCodeFieldError() {
        return codeFieldError;
    }
    
    public WebElement getInfoNameButton() {
        return infoNameButton;
    }

    public WebElement getInfoCodeButton() {
        return infoCodeButton;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }
    
    public DTUserGroupEditPage(WebDriver driver) {
        super(driver);
    }
    
}
