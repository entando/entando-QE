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
import org.entando.selenium.utils.pageParts.Breadcrumb;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class prepresent the File browser page
 * 
 * @version 1.01
 */
public class DTFileBrowserPage extends PageObject{
    
    @FindBy(xpath = "//h1")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.fa-question-circle-o")
    private WebElement help;
    
    @FindBy(xpath ="//table[contains(@class, 'table')]")
    private WebElement table;

    @FindBy(css = "table > thead")
    private WebElement tableHeader;

    @FindBy(css = "table > tbody")
    private WebElement tableBody;

    @FindBy(xpath = "//a[contains(., 'Upload')]")
    private WebElement uploadButton;
    
    @FindBy(xpath = "//a[contains(., 'Create folder')]")
    private WebElement createFolderButton;
    
    @FindBy(xpath = "//a[contains(., 'Create text')]")
    private WebElement createTextFileButton;
    
    @FindBy(xpath = "//a[contains(., 'up ..')]")
    private WebElement upButton;
    
    @FindBy(xpath = "//div[@id = 'main']//child::ol")
    private WebElement breadcrumb;
    
    @FindBy(xpath = "//h1//../ol")
    private WebElement pageBreadcrumb;
    
    @FindBy(xpath = "//p[@class = 'esclamation-underline-text']")
    private WebElement deleteMessage;
    
    @FindBy(xpath = "//button[contains(.,'Delete')]")
    private WebElement deleteButton;
    
    
    
    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    public WebElement getTooltip() {
        return tooltip;
    }
    
    public WebElement getHelp() {
        return help;
    }

    public WebElement getUploadButton() {
        return uploadButton;
    }
    
    public WebElement getCreateFolderButton() {
        return createFolderButton;
    }

    public WebElement getCreateTextFileButton() {
        return createTextFileButton;
    }
    
    public WebElement getUpButton() {
        return upButton;
    }
    
    public SimpleTable getTable() {
        return (new SimpleTable(table));
    }

    public WebElement getTableHeader() {
        return tableHeader;
    }

    public WebElement getTableBody() {
        return tableBody;
    }

    public Breadcrumb getBreadcrumb() {
        return (new Breadcrumb(breadcrumb));
    }

    public WebElement getDeleteMessage() {
        return deleteMessage;
    }

    public WebElement getDeleteButton() {
        return deleteButton;
    }

    public Breadcrumb getPageBreadcrumb() {
        return (new Breadcrumb(pageBreadcrumb));
    }
    
    
    
    
    public DTFileBrowserPage(WebDriver driver) {
        super(driver);
    }
}
