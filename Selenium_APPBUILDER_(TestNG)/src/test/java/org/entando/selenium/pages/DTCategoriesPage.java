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
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Categories page
 * 
 * @version 1.01
 */
public class DTCategoriesPage extends PageObject implements ExpandableTablePageInterface{
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(css = "table > thead")
    private WebElement tableHeader;

    @FindBy(css = "table > tbody")
    private WebElement tableBody;
    
    @FindBy(css = "table")
    private WebElement simpleTable;
    
    @FindBy(xpath ="//table[contains(@class, 'CategoryTree__table')]")
    private WebElement table;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button[contains(., 'Add')]")
    private WebElement addButton;
    
    @FindBy(xpath = "//button[contains(@id, 'DeleteCategoryModal__button-delete')]")
    private WebElement deleteModalButton;
    
    @FindBy(xpath = "//div[contains(@class, 'modal-body')]")
    private WebElement modalBody;
    
    public static final By modalWindowTag = By.xpath("//div[contains(@class, 'modal-content')]");
    
    public DTCategoriesPage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getAddButton() {
        return addButton;
    }
    
    public WebElement getPageTitle() {
        return this.pageTitle;
    }

    public WebElement getTableHeader() {
        return this.tableHeader;
    }

    public WebElement getTableBody() {
        return this.tableBody;
    }
    
    @Override
    public ExpandableTable getTable() {
        return (new ExpandableTable(table));
    }

    public WebElement getTooltip() {
        return tooltip;
    }
    
    public WebElement getHelp() {
        return help;
    }

    public WebElement getDeleteModalButton() {
        return deleteModalButton;
    }

    public WebElement getModalBody() {
        return modalBody;
    }
    
    public SimpleTable getSimpleTable() {
        return (new SimpleTable(simpleTable));
    }
    
}
