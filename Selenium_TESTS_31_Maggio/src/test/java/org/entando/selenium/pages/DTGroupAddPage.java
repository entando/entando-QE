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
 *  
 */
public class DTGroupAddPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Name']")
    private WebElement nameLabel;
    
    @FindBy(xpath = "//input[contains(@name, 'name')]")
    private WebElement name;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Code']")
    private WebElement codeLabel;
    
    @FindBy(xpath = "//input[contains(@name, 'code')]")
    private WebElement code;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Name']/../sup/i")
    private WebElement nameRequired;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Code']/../sup/i")
    private WebElement codeRequired;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Name']/../button")
    private WebElement infoName;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Code']/../button")
    private WebElement infoCode;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy (xpath = "//*[contains(@type, 'submit')]")
    private WebElement submit;
    
    public DTGroupAddPage(WebDriver driver) {
        super(driver);
    }
    
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

    public WebElement getNameRequired() {
        return nameRequired;
    }

    public WebElement getCodeRequired() {
        return codeRequired;
    }

    public WebElement getInfoName() {
        return infoName;
    }

    public WebElement getInfoCode() {
        return infoCode;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getSubmit() {
        return submit;
    }

    public void setName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public void setCode(String code) {
        this.code.clear();
        this.code.sendKeys(code);
    }
    
}
