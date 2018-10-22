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

import org.entando.selenium.utils.ExpandableTablePageInterface;
import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Categories Add page
 * 
 * @version 1.01
 */
public class DTCategoriesAddPage extends PageObject implements ExpandableTablePageInterface{
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath ="//table[contains(@class, 'CategoryTreeSelector')]")
    private WebElement table;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(css = "[name='titles.en']")
    private WebElement enNameField;
    
    @FindBy(xpath = "//label[contains(@for,'titles.en')]/*//*[contains(@class,'required-icon')]")
    private WebElement enNameAsterisk;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[contains(@for,'titles.en')]")
    private WebElement enNameFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'titles.it')]")
    private WebElement itNameField;
    
    @FindBy(xpath = "//label[contains(@for,'titles.en')]/*//*[contains(@class,'required-icon')]")
    private WebElement itNameAsterisk;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[contains(@for,'titles.it')]")
    private WebElement itNameFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'code')]")
    private WebElement codeField;
    
    @FindBy(xpath = "//label[contains(@for,'code')]/*//*[contains(@class,'required-icon')]")
    private WebElement codeAsterisk;
    
    @FindBy(xpath = "//label[contains(@*, 'code')]//button")
    private WebElement codeHelpButton;
    
    @FindBy(xpath = "//div[contains(@id, 'popover')]/div[text()='Insert page code']")
    private WebElement codeHelpPopOver;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[contains(@for,'code')]")
    private WebElement codeFieldError;
    
    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;

    
    
    public WebElement getPageTitle() {
        return pageTitle;
    }

    @Override
    public ExpandableTable getTable() {
        return (new ExpandableTable(table));
    }
    
    public WebElement getTableWebElement(){
        return table;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getEnNameField() {
        return enNameField;
    }

    public WebElement getEnNameAsterisk() {
        return enNameAsterisk;
    }

    public WebElement getEnNameFieldError() {
        return enNameFieldError;
    }

    public WebElement getItNameField() {
        return itNameField;
    }

    public WebElement getItNameAsterisk() {
        return itNameAsterisk;
    }

    public WebElement getItNameFieldError() {
        return itNameFieldError;
    }

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getCodeAsterisk() {
        return codeAsterisk;
    }

    public WebElement getCodeHelpButton() {
        return codeHelpButton;
    }

    public WebElement getCodeHelpPopOver() {
        return codeHelpPopOver;
    }

    public WebElement getCodeFieldError() {
        return codeFieldError;
    }

    public DTCategoriesAddPage(WebDriver driver) {
        super(driver);
    }
    
    public void setEnNameField(String enName) {
        this.enNameField.clear();
        this.enNameField.sendKeys(enName);
    }

    public void setItNameField(String itName) {
        this.itNameField.clear();
        this.itNameField.sendKeys(itName);
    }
    
    public void setCodeField(String code) {
        this.codeField.clear();
        this.codeField.sendKeys(code);
    }

    public WebElement getSaveButton() {
        return saveButton;
    }
        
}
