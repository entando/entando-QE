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
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class prepresent the Data Type page object
 * 
 * @version 1.01
 */
public class DTDataTypesPage extends PageObject implements BrowsableTablePageInterface{

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath ="//table[contains(@class, 'table')]")
    private WebElement table;

    @FindBy(css = "table > thead")
    private WebElement tableHeader;

    @FindBy(css = "table > tbody")
    private WebElement tableBody;
    
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

    @FindBy(xpath = "//button[contains(.,'New')]")
    private WebElement newButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
         
    @FindBy(xpath = "//button[contains(@id, 'DeleteDataTypeModal__button-delete')]")
    private WebElement deleteModalButton;
        
    @FindBy(xpath = "//div[contains(@class, 'modal-body')]")
    private WebElement modalBody;
    
    public static final By modalWindowTag = By.xpath("//div[contains(@class, 'modal-content')]");


        
    public WebElement getNewButton() {
        return newButton;
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

    public WebElement getNewDataTypeButton() {
        return this.newButton;
    }
    
    public WebElement getTooltip() {
        return tooltip;
    }
    
    public WebElement getHelp() {
        return help;
    }

    public DTDataTypesPage(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public SimpleTable getTable() {
        return (new SimpleTable(table));
    }
    
    @Override
    public WebElement getNextPageButton() {
        return this.nextPageButton;
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
    public WebElement getPaginationButton() {
        return paginationButton;
    }
        
    @Override
    public Kebab getPaginationKebab() {
        return (new Kebab(paginationButton,paginationButton));
    }
    
    @Override
    public int getItemPerPage(){
        return Integer.parseInt(paginationButton.getText());
    }
    
    
    public WebElement getDeleteModalButton() {
        return deleteModalButton;
    }

    public WebElement getModalBody() {
        return modalBody;
    }
    
    
}
