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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represent the Add Widgets page
 * 
 * @version 1.01
 */
public class DTWidgetAddPage extends PageObject {
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//input[@name = 'code']")
    private WebElement codeField;
    
    @FindBy(xpath = "//input[@name = 'code']/../span/span")
    private WebElement codeFieldError;
    
    @FindBy(xpath = "//input[@name = 'titles.en']")
    private WebElement enTitleField;
    
    @FindBy(xpath = "//input[@name = 'titles.en']/../span/span")
    private WebElement enTitleFieldError;
    
    @FindBy(xpath = "//input[@name = 'titles.it']")
    private WebElement itTitleField;
    
    @FindBy(xpath = "//input[@name = 'titles.it']/../span/span")
    private WebElement itTitleFieldError;
    
    @FindBy(xpath = "//select[@name = 'group']")
    private WebElement groupSelect;
    
    @FindBy(xpath = "//select[@name = 'group']/../span/span")
    private WebElement groupSelectError;
    
    @FindBy(css = "#basic-tabs-pane-1 > textarea")
    private WebElement customUI;
    
    @FindBy(xpath = "//div[contains(@class, 'alert')]")
    private WebElement alertMessage;
    
    
    public DTWidgetAddPage(WebDriver driver) {
        super(driver);
    }

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

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getEnTitleField() {
        return enTitleField;
    }

    public WebElement getItTitleField() {
        return itTitleField;
    }

    public Select getGroupSelect() {
        return new Select(groupSelect);
    }
    
    public WebElement getGroup() {
        return groupSelect;
    }
    
    
    public WebElement getCustomUI() {
        return customUI;
    }

    public void setCodeField(String code) {
        this.codeField.clear();
        this.codeField.sendKeys(code);
    }

    public void setEnTitleField(String enTitle) {
        this.enTitleField.clear();
        this.enTitleField.sendKeys(enTitle);
    }

    public void setItTitleField(String itTitle) {
        this.itTitleField.clear();
        this.itTitleField.sendKeys(itTitle);
    }
    
    public void setCustomUI(String customUI) {
        this.customUI.clear();
        this.customUI.sendKeys(customUI);
    }

    public WebElement getCodeFieldError() {
        return codeFieldError;
    }

    public WebElement getEnTitleFieldError() {
        return enTitleFieldError;
    }

    public WebElement getItTitleFieldError() {
        return itTitleFieldError;
    }

    public WebElement getGroupSelectError() {
        return groupSelectError;
    }

    public WebElement getAlertMessage() {
        return alertMessage;
    }
    
    public WebElement getCloseAlertMessageButton(){
        return alertMessage.findElement(By.xpath(".//button"));
    }
    
    public String getAlertMessageContent(){
        return alertMessage.findElement(By.xpath(".//li")).getText();
    }

}
