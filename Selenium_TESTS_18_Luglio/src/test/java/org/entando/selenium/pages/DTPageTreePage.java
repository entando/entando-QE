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
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the PageTree page
 * 
 * @version 1.01
 */
public class DTPageTreePage extends PageObject implements ExpandableTablePageInterface{

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(css = "table > thead")
    private WebElement tableHeader;

    @FindBy(css = "table > tbody")
    private WebElement tableBody;
    
    @FindBy(css = "table")
    private WebElement simpleTable;
    
    @FindBy(xpath ="//table[contains(@class, 'table-treegrid')]")
    private WebElement table;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button[contains(., 'Add')]")
    private WebElement addButton;
    
    @FindBy(xpath = "//button[contains(@id, 'DeletePageModal__button-delete')]")
    private WebElement deleteModalButton;
    
    @FindBy(xpath = "//button[contains(@id, 'PublishPageModal__button-publish')]")
    private WebElement publishModalButton;
    
    @FindBy(xpath = "//button[contains(@id, 'UnpublishPageModal__button-publish')]")
    private WebElement unpublishModalButton;
    
    @FindBy(xpath = "//div[contains(@class, 'modal-body')]")
    private WebElement modalBody;
        
    @FindBy(xpath = "//input[contains(@name, 'pageCodeToken')]")
    private WebElement searchField;
    
    @FindBy(xpath = "//button[contains(., 'Search')]")
    private WebElement searchButton;
    
    @FindBy(xpath = "//button[contains(., 'Clear result')]")
    private WebElement clearResultButton;
    
    @FindBy(xpath = "//div[contains(@class, 'alert-warning')]")
    private WebElement noPagesFoundAlert;
    
    public static final By modalWindowTag = By.xpath("//div[contains(@class, 'modal-content')]");
    
    public static final By statusIconTag = By.xpath("./i[contains(@class, 'PageStatusIcon')]");
    
    
    public DTPageTreePage(WebDriver driver) {
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
    
    public void setSearchField(String code) {
        this.searchField.clear();
        this.searchField.sendKeys(code);
    }

    public WebElement getSearchButton() {
        return searchButton;
    }
    
    public WebElement getClearResultButton() {
        return clearResultButton;
    }

    public WebElement getNoPagesFoundAlert() {
        return noPagesFoundAlert;
    }

    public SimpleTable getSimpleTable() {
        return (new SimpleTable(simpleTable));
    }

    public WebElement getPublishModalButton() {
        return publishModalButton;
    }

    public WebElement getUnpublishModalButton() {
        return unpublishModalButton;
    }

    public static By getModalWindowTag() {
        return modalWindowTag;
    }

    public static By getStatusIconTag() {
        return statusIconTag;
    }

    public WebElement getSearchField() {
        return searchField;
    }
    
    
    
}
