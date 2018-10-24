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

import org.entando.selenium.testHelpers.BrowsableTablePageInterface;
import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Details User Group page
 * 
 * @version 1.01
 */
public class DTUserGroupDetailsPage extends PageObject implements BrowsableTablePageInterface{
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//a[@role = 'tab']/span[text() = 'Pages']/..")
    private WebElement pagesTab;
    
    @FindBy(xpath = "//a[@role = 'tab']/span[text() = 'Users']/..")
    private WebElement usersTab;
    
    @FindBy(xpath = "//a[@role = 'tab']/span[text() = 'Widget Types']/..")
    private WebElement widgetTypesTab;
    
    @FindBy(xpath = "//a[@role = 'tab']/span[text() = 'Contents']/..")
    private WebElement contentsTab;
    
    @FindBy(xpath = "//a[@role = 'tab']/span[text() = 'Resources']/..")
    private WebElement resourcesTab;
    
    @FindBy(xpath = "//div[@class = 'form-group'][1]/div/code")
    private WebElement groupField;
    
    @FindBy(xpath = "//div[@class = 'form-group'][2]/div")
    private WebElement nameField;
    
    @FindBy(xpath ="//table[contains(@class, 'GroupDetailTabUsers__table')]")
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
    
    
    public final By spinnerTag = By.xpath("//div[contains(@class, 'spinner-md')]");
    
    
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getPagesTab() {
        return pagesTab;
    }

    public WebElement getUsersTab() {
        return usersTab;
    }

    public WebElement getWidgetTypesTab() {
        return widgetTypesTab;
    }

    public WebElement getContentsTab() {
        return contentsTab;
    }

    public WebElement getResourcesTab() {
        return resourcesTab;
    }

    public WebElement getGroupField() {
        return groupField;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getTableHeader() {
        return tableHeader;
    }

    public WebElement getTableBody() {
        return tableBody;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }
            
    @Override
    public SimpleTable getTable() {
        return (new SimpleTable(table));
    }
    
    @Override
    public WebElement getNextPageButton() {
        return nextPageButton;
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
    
    public DTUserGroupDetailsPage(WebDriver driver) {
        super(driver);
    }
    
}
