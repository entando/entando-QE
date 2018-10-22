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
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represent the Labels And LanguagesPage
 * 
 * @version 1.01
 */
public class DTLabelsAndLanguagesPage extends PageObject{
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath ="//table[contains(@class, 'ActiveLangTable__table')]")
    private WebElement table;

    @FindBy(css = "table > thead")
    private WebElement tableHeader;

    @FindBy(css = "table > tbody")
    private WebElement tableBody;
    
    @FindBy(css ="span.pagination-pf-pages")
    private WebElement numberOfTablePages;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//select[@name = 'language']")
    private WebElement languageSelect;
    
    @FindBy(xpath = "//button[contains(., 'Add')]")
    private WebElement addButton;

    @FindBy(xpath = "//span[text() = 'System labels']/../..")
    private WebElement systemLabelsButton;
    
    @FindBy(xpath = "//span[text() = 'Languages']/../..")
    private WebElement languagesButton;
    
    @FindBy(xpath = "//button[contains(@id, 'DeleteLabelAndLanguagesModal__button-delete')]")
    private WebElement deleteModalButton;
    
    @FindBy(xpath = "//div[contains(@class, 'modal-body')]")
    private WebElement modalBody;
    
    public static final By modalWindowTag = By.xpath("//div[contains(@class, 'modal-content')]");
    
    
    
    
    public DTLabelsAndLanguagesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getTable() {
        return table;
    }
    
    public SimpleTable getSimpleTable() {
        return new SimpleTable(table);
    }

    public WebElement getTableHeader() {
        return tableHeader;
    }

    public WebElement getTableBody() {
        return tableBody;
    }

    public WebElement getNumberOfTablePages() {
        return numberOfTablePages;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }
    
    public Select getLanguageSelect(){
        return new Select(languageSelect);
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getDeleteModalButton() {
        return deleteModalButton;
    }

    public WebElement getModalBody() {
        return modalBody;
    }

    public WebElement getSystemLabelsButton() {
        return systemLabelsButton;
    }
    
    
    
    
}
