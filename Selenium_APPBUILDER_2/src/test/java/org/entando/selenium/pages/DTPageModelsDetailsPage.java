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

import org.entando.selenium.utils.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Page Model Details page
 * 
 * @version 1.01
 */
public class DTPageModelsDetailsPage extends PageObject {

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button[contains(., 'Edit')]")
    private WebElement editButton;
    
    @FindBy(xpath = "//th[contains(., 'Name')]/../td")
    private WebElement nameField;
    
    @FindBy(xpath = "//th[contains(., 'Code')]/../td")
    private WebElement codeField;
    
    @FindBy(xpath = "//th[contains(., 'Plugin code')]/../td")
    private WebElement pluginCodeField;
    
    @FindBy(xpath = "//th[contains(., 'JSON Configuration')]/../td")
    private WebElement jsonConfigurationField;
    
    @FindBy(xpath = "//th[. = 'Template']/../td")
    private WebElement templateField;
    
    @FindBy(xpath = "//th[. = 'Template Preview']/../td")
    private WebElement templatePreviewField;
    
    
    
    
    public WebElement getPageTitle() {
        return this.pageTitle;
    }
    
    public WebElement getTooltip() {
        return tooltip;
    }
    
    public WebElement getHelp() {
        return help;
    }

    public WebElement getEditButton() {
        return editButton;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getPluginCodeField() {
        return pluginCodeField;
    }

    public WebElement getJsonConfigurationField() {
        return jsonConfigurationField;
    }

    public WebElement getTemplateField() {
        return templateField;
    }

    public WebElement getTemplatePreviewField() {
        return templatePreviewField;
    }
    
    

    public DTPageModelsDetailsPage(WebDriver driver) {
        super(driver);
    }

}
