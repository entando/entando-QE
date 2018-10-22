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
 * This class represent the Page Clone page
 * 
 * @version 1.01
 */
public class DTPageClonePage extends PageObject implements ExpandableTablePageInterface{
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath ="//table[contains(@class, 'table-treegrid')]")
    private WebElement expTable;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(css = "table > tbody")
    private WebElement tableBody;
    
    @FindBy(xpath = "//button/span[text()='Save']/..")
    private WebElement saveButton;
    
    @FindBy(xpath = "//button[contains(.,'Save and Configure')]")
    private WebElement saveAndConfigureButton;
    
    @FindBy(css = "[name='code']")
    private WebElement codeField;
    
    @FindBy(css = "[name='titles.en']")
    private WebElement enTitleField;
    
    @FindBy(xpath = "//input[contains(@name,'titles.it')]")
    private WebElement itTitleField;
    
    @FindBy(xpath = "//input[contains(@name,'titles.es')]")
    private WebElement esTitleField;
    
    
    public DTPageClonePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    @Override
    public ExpandableTable getTable() {
        return (new ExpandableTable(expTable));
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getTableBody() {
        return tableBody;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getSaveAndConfigureButton() {
        return saveAndConfigureButton;
    }

    public WebElement getCodeField() {
        return codeField;
    }

    public void setCodeField(String code) {
        this.codeField.clear();
        this.codeField.sendKeys(code);
    }

    public void setEnTitleField(String enTitleField) {
        this.enTitleField.clear();
        this.enTitleField.sendKeys(enTitleField);
    }

    public void setItTitleField(String itTitleField) {
        this.itTitleField.clear();
        this.itTitleField.sendKeys(itTitleField);
    }

    public void setEsTitleField(String esTitleField) {
        this.esTitleField.clear();
        this.esTitleField.sendKeys(esTitleField);
    }
    
    
    
}
