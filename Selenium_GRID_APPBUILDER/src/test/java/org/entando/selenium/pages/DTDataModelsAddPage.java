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
 * This class represent the Add Data Model page
 * 
 * @version 1.01
 */
public class DTDataModelsAddPage extends PageObject{
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Name']")
    private WebElement nameLabel;
    
    @FindBy(xpath = "//input[contains(@name, 'descr')]")
    private WebElement name;
    
    @FindBy(xpath = "//input[contains(@name,'descr')]/../span/span")
    private WebElement nameFieldError;
    
    @FindBy(xpath = "//*[contains(@class, 'FormLabel')]/span[text()='Code']")
    private WebElement codeLabel;
    
    @FindBy(xpath = "//input[contains(@name, 'modelId')]")
    private WebElement code;
    
    @FindBy(xpath = "//input[contains(@name,'modelId')]/../span/span")
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
    
    @FindBy (xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;
   
    @FindBy(xpath = "//select[contains(@name, 'type')]")
    private WebElement typeSelect;
    
    @FindBy(xpath = "//select[contains(@name,'type')]/../span/span")
    private WebElement typeSelectError;
    
    @FindBy(xpath = "//textarea[contains(@name, 'model')]")
    private WebElement model;
    
    @FindBy(xpath = "//input[contains(@name, 'stylesheet')]")
    private WebElement styleSheet;
    
    
    
    
    public DTDataModelsAddPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getNameLabel() {
        return nameLabel;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getNameFieldError() {
        return nameFieldError;
    }

    public WebElement getCodeLabel() {
        return codeLabel;
    }

    public WebElement getCode() {
        return code;
    }

    public WebElement getCodeFieldError() {
        return codeFieldError;
    }

    public WebElement getNameRequiredAsterisk() {
        return nameRequiredAsterisk;
    }

    public WebElement getCodeRequiredAsterisk() {
        return codeRequiredAsterisk;
    }

    public WebElement getInfoNameButton() {
        return infoNameButton;
    }

    public WebElement getInfoCodeButton() {
        return infoCodeButton;
    }

    public WebElement getNameTooltip() {
        return nameTooltip;
    }

    public WebElement getCodeTooltip() {
        return codeTooltip;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public void setName(String name) {
        this.name.clear();
        this.name.sendKeys(name);
    }

    public void setCode(String code) {
        this.code.clear();
        this.code.sendKeys(code);
    }

    public void setModel(String model) {
        this.model.click();
        this.model.clear();
        this.model.sendKeys(model);
    }

    public void setStyleSheet(String styleSheet) {
        this.styleSheet.click();
        this.styleSheet.clear();
        this.styleSheet.sendKeys(styleSheet);
    }

    public void setTypeSelect(String typeSelect) {
        this.getTypeSelect().selectByVisibleText(typeSelect);
    }
    
    

    public Select getTypeSelect() {
        return new Select(typeSelect);
    }
    
    public WebElement getType() {
        return typeSelect;    }

    public WebElement getModel() {
        return model;
    }

    public WebElement getStyleSheet() {
        return styleSheet;
    }

    public WebElement getTypeSelectError() {
        return typeSelectError;
    }
    
    
    
    
}
