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
 * This class prepresent the Create Text File (on File browser) page
 * 
 * @version 1.01
 */
public class DTFileBrowserCreateTextFilePage extends PageObject{
    
    @FindBy(xpath = "//h1")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//div[@class = 'FileBreadcrumb']//child::ol")
    private WebElement breadcrumb;
    
    @FindBy(xpath = "//input[@name = 'name']")
    private WebElement fileName;
    
    @FindBy(xpath = "//input[@name = 'name']/../span/span")
    private WebElement fileNameError;
    
    @FindBy(xpath = "//select[@name = 'extension']")
    private WebElement selectType;
    
    @FindBy(xpath = "//textarea[@name = 'content']")
    private WebElement fileContent;
    
    @FindBy(xpath = "//div[contains(@class, 'RenderTextAreaInput')]/span/span")
    private WebElement fileContentError;
    
    @FindBy(xpath = "//input[@value = 'Cancel']")
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

    public WebElement getFileName() {
        return fileName;
    }

    public Select getSelectType() {
        return (new Select(selectType));
    }

    public WebElement getFileContent() {
        return fileContent;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }
    
    public void setFileName(String folderName) {
        this.fileName.clear();
        this.fileName.sendKeys(folderName);
    }

    public void setFileContent(String fileContent) {
        this.fileContent.clear();
        this.fileContent.sendKeys(fileContent);
    }

    public WebElement getFileNameError() {
        return fileNameError;
    }

    public WebElement getFileContentError() {
        return fileContentError;
    }
    
    
    
    
    public DTFileBrowserCreateTextFilePage(WebDriver driver) {
        super(driver);
    }
    
}
