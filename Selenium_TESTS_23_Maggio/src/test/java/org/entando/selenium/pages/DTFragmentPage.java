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

public class DTFragmentPage extends PageObject implements BrowsableTablePage {

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath ="//table[contains(@class, 'FragmentListTable__table')]")
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
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button[contains(., 'New')]")
    private WebElement newButton;
    

    public WebElement getPageTitle() {
        return this.pageTitle;
    }
    
    @Override
    public WebElement getTable() {
        return this.table;
    }
    
    @Override
    public WebElement getNextPageButton() {
        return this.nextPageButton;
    }
    
    @Override
    public WebElement getNumberOfTablePages() {
        return numberOfTablePages;
    }

    @Override
    public WebElement getActualPageNumber() {
        return actualPageNumber;
    }
    
    @Override
    public WebElement getNumberOfTotalItems() {
        return numberOfTotalItems;
    }
    
    @Override
    public WebElement getPaginationButton() {
        return paginationButton;
    }
   
    public WebElement getTableHeader() {
        return this.tableHeader;
    }
    
    public WebElement getTooltip() {
        return tooltip;
    }
    
    public WebElement getHelp() {
        return help;
    }
    
    public WebElement getNewButton() {
        return newButton;
    }

    public DTFragmentPage(WebDriver driver) {
        super(driver);
    }

}
