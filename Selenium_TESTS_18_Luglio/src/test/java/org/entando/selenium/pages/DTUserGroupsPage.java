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

import org.entando.selenium.utils.BrowsableTablePageInterface;
import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the User Group page
 * 
 * @version 1.01
 */
public class DTUserGroupsPage extends PageObject implements BrowsableTablePageInterface{
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(., 'Add')]")
    private WebElement addButton;
    
    @FindBy(xpath = "//table[contains(@class, 'GroupListTable__table')]")
    private WebElement groupTable;
    
    @FindBy(css = "table > thead")
    private WebElement tableHeader;

    @FindBy(css = "table > tbody")
    private WebElement tableBody;
    
    @FindBy(xpath ="//table[contains(@class, 'GroupListTable__table')]")
    private WebElement table;
    
    @FindBy(css ="span.pagination-pf-pages")
    private WebElement numberOfTablePages;

    @FindBy(css ="input.pagination-pf-page")
    private WebElement actualPageNumber;
    
    @FindBy(css ="span.pagination-pf-items-total")
    private WebElement numberOfTotalItems;

    @FindBy(xpath = "//a[contains(@title, 'Next Page')]")
    private WebElement nextPageButton;
    
    @FindBy(css ="div.dropup")
    private WebElement paginationButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//div[contains(@class, 'spinner-md')]")
    private WebElement spinnerLoad;
    
    @FindBy(xpath = "//div[contains(@class, 'modal-body')]")
    private WebElement modalBody;
    
    @FindBy(xpath = "//button[contains(@id, 'DeleteGroupModal__button-delete')]")
    private WebElement deleteModalButton;
    
    public static final By modalWindowTag = By.xpath("//div[contains(@class, 'modal-content')]");
    
    public final By spinnerTag = By.xpath("//div[contains(@class, 'spinner-md')]");
    
    
    
    public DTUserGroupsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    public WebElement getAddButton(){
        return addButton;
    }

    public WebElement getGroupsTable() {
        return groupTable;
    }


    public WebElement getTableHeader() {
        return tableHeader;
    }

    public WebElement getTableBody() {
        return tableBody;
    }
        
    @Override
    public SimpleTable getTable() {
        return (new SimpleTable(table));
    }

    @Override
    public int getNumberOfTablePages() {
        return Integer.parseInt(numberOfTablePages.getText());
    }

    @Override
    public int getActualPageNumber() {
        return Integer.parseInt(actualPageNumber.getAttribute("value"));
    }
    
    @Override
    public int getNumberOfTotalItems() {
        return Integer.parseInt(numberOfTotalItems.getText());
    }

    @Override
    public WebElement getNextPageButton() {
        return nextPageButton;
    }

    @Override
    public WebElement getPaginationButton() {
        return paginationButton;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }
    
    @Override
    public int getItemPerPage() {
        return Integer.parseInt(paginationButton.getText());
    }

    @Override
    public Kebab getPaginationKebab() {
        return (new Kebab(paginationButton,paginationButton));
    }

    public WebElement getModalBody() {
        return modalBody;
    }

    public WebElement getDeleteModalButton() {
        return deleteModalButton;
    }
}
