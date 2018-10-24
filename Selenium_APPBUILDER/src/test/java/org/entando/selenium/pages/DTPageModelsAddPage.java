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

import static java.lang.Thread.sleep;
import java.util.logging.Logger;
import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Add Page Model page
 * 
 * @version 1.01
 */
public class DTPageModelsAddPage extends PageObject {
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
    
    @FindBy(xpath = "//label[* = 'Code']/../..//span[@class = 'help-block']")
    private WebElement codeErrorField;
    
    @FindBy(xpath = "//input[@name = 'descr']")
    private WebElement nameField;
    
    @FindBy(xpath = "//label[* = 'Name']/../..//span[@class = 'help-block']")
    private WebElement nameErrorField;
    
    @FindBy(xpath = "//label[* = 'JSON Configuration']/../div/div/div/div[6]/div/div/div/div/div/div[3]/pre")
    private WebElement jsonConfigurationFieldLine3;
    @FindBy(xpath = "//label[* = 'JSON Configuration']/../div/div/div/div[6]/div/div/div/div/div/div[2]/pre")
    private WebElement jsonConfigurationFieldLine2;
    @FindBy(xpath = "//label[* = 'JSON Configuration']/../div/div/div/div[6]/div/div/div/div/div/div[1]/pre")
    private WebElement jsonConfigurationFieldLine1;
    
    @FindBy(xpath = "//label[* = 'JSON Configuration']/..//div[contains(@class, 'CodeMirror cm-s-default')]")
    private WebElement jsonConfigurationField;
    
    
    @FindBy(xpath = "//label[* = 'Template']/..//div[@class = 'react-codemirror2']")
    private WebElement templateField;
    
    @FindBy(xpath = "//label[* = 'JSON Configuration']/..//div[@class = 'help-block']")
    private WebElement jsonConfigurationErrorField;
    
    @FindBy(xpath = "//label[* = 'Template']/..//div[@class = 'help-block']")
    private WebElement templateErrorField;
    
    

    public DTPageModelsAddPage(WebDriver driver) {
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

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getJsonConfigurationField() {
        return jsonConfigurationField;
    }

    public WebElement getTemplateField() {
        return templateField;
    }

    public void setCodeField(String codeField) {
        this.codeField.clear();
        this.codeField.sendKeys(codeField);
    }

    
    public void setNameField(String nameField) {
        this.nameField.clear();
        this.nameField.sendKeys(nameField);
    }

    
    
    public void setJsonConfigurationField(String jsonConfigurationField) throws InterruptedException {
        Logger.getGlobal().info("setJesonConfig");
        //this.jsonConfigurationField.click();
        //Logger.getGlobal().info("JesonConfig clicked");
        this.jsonConfigurationFieldLine1.click();
        Actions builder = new Actions(driver); 
        builder.sendKeys(jsonConfigurationField).perform();
        Logger.getGlobal().info("JesonConfig compiled");
    }
    
    
    
    public void clearJsonConfigurationField() {
        Actions builder = new Actions(driver); 
        builder.click(this.jsonConfigurationFieldLine3)
                .doubleClick().perform();
        builder.sendKeys(Keys.BACK_SPACE).perform();
        Logger.getGlobal().info("step 1");
        
        builder = new Actions(driver); 
        builder.click(this.jsonConfigurationFieldLine2).perform();
        builder.click(this.jsonConfigurationFieldLine2).perform();
        builder.click(this.jsonConfigurationFieldLine2).perform();
        builder.sendKeys(Keys.BACK_SPACE).perform(); 
        Logger.getGlobal().info("step 2");
        
        builder = new Actions(driver);
        builder.click(this.jsonConfigurationFieldLine1).perform();
        builder.doubleClick().perform();
        builder.sendKeys(Keys.BACK_SPACE).perform(); 
    }
    
    

    public void setTemplateField(String templateField) {
        Actions builder = new Actions(driver);
        builder.click(this.templateField).perform();
        builder.sendKeys(templateField).perform();
        Logger.getGlobal().info("templateField compiled");
    }

    
    
    public WebElement getCodeErrorField() {
        return codeErrorField;
    }

    public WebElement getNameErrorField() {
        return nameErrorField;
    }

    public WebElement getJsonConfigurationErrorField() {
        return jsonConfigurationErrorField;
    }

    public WebElement getTemplateErrorField() {
        return templateErrorField;
    }
    
    
    
}
