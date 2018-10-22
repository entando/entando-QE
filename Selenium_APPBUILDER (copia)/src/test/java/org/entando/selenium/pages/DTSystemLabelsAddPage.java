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
 * This class perform a test to add Label
 * 
 * @version 1.01
 */
public class DTSystemLabelsAddPage extends PageObject{
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(name = "key")
    private WebElement codeField;
    
    @FindBy(name = "titles.en")
    private WebElement enNameField;
    
    @FindBy(name = "titles.it")
    private WebElement itNameField;
    
    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    
    public DTSystemLabelsAddPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getEnNameField() {
        return enNameField;
    }

    public WebElement getItNameField() {
        return itNameField;
    }

    public void setCodeField(String code) {
        this.codeField.clear();
        this.codeField.sendKeys(code);
    }

    public void setEnNameField(String enName) {
        this.enNameField.clear();
        this.enNameField.sendKeys(enName);
    }

    public void setItNameField(String itName) {
        this.itNameField.clear();
        this.itNameField.sendKeys(itName);
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
    
    
    
}
