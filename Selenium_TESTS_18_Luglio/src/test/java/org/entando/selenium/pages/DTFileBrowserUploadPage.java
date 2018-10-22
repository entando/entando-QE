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
 * This class prepresent the Upload File (on File browser) page
 * 
 * @version 1.01
 */
public class DTFileBrowserUploadPage extends PageObject{

    @FindBy(xpath = "//h1")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.fa-question-circle-o")
    private WebElement help;
    
    @FindBy(xpath = "//div[@id = 'main']//child::ol")
    private WebElement breadcrumb;
    
    @FindBy(xpath = "//input[@id = 'file-upload']")
    private WebElement uploadButton;
    
    @FindBy(xpath = "//button[contains(.,'Cancel')]")
    private WebElement cancelButton;
    
    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getBreadcrumb() {
        return breadcrumb;
    }

    public WebElement getUploadButton() {
        return uploadButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public void setFilePath(String filePath) {
        this.uploadButton.sendKeys(filePath);
    }
    
    
    public DTFileBrowserUploadPage(WebDriver driver) {
        super(driver);
    }
    
}
