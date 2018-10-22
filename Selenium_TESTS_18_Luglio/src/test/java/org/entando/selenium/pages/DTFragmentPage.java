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
import org.entando.selenium.utils.pageParts.SwitchButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * This class prepresent the Fragment page
 * 
 * @version 1.01
 */
public class DTFragmentPage extends PageObject implements BrowsableTablePageInterface{

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
    
    @FindBy(xpath = "//button[contains(., 'Search')]")
    private WebElement searchButton;
    
    @FindBy(xpath = "//span[text() = 'List']/../..")
    private WebElement listButton;
    
    @FindBy(xpath = "//span[text() = 'Settings']/../..")
    private WebElement settingsButton;
    
    @FindBy(xpath = "//button[contains(., 'Save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//div[contains(@class, 'bootstrap-switch wrapper')]")
    private WebElement enableEditingSwitch;
    
    @FindBy(xpath = "//div[contains(@class, 'alert')]")
    private WebElement alertMessage;
    
    @FindBy(xpath = "//input[@name = 'code']")
    private WebElement codeSearchField;
    
    @FindBy(xpath = "//select[@name = 'widgetType']")
    private WebElement widgetTypeSelect;
    
    @FindBy(xpath = "//select[@name = 'plugin']")
    private WebElement pluginSelect;
    
    
    public WebElement getPageTitle() {
        return pageTitle;
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
    
    public WebElement getTableHeader() {
        return tableHeader;
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
    
    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getListButton() {
        return listButton;
    }

    public WebElement getSettingsButton() {
        return settingsButton;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public SwitchButton getEnableEditingSwitch() {
        return new SwitchButton(enableEditingSwitch);
    }

    public WebElement getAlertMessage() {
        return alertMessage;
    }
    
    public WebElement getCloseMessageButton(){
        return alertMessage.findElement(By.xpath(".//button"));
    }

    public WebElement getCodeSearchField() {
        return codeSearchField;
    }

    public void setCodeSearchField(String searchCode) {
        this.codeSearchField.clear();
        this.codeSearchField.sendKeys(searchCode);
    }
    
    

    public Select getWidgetTypeSelect() {
        return new Select(widgetTypeSelect);
    }

    public Select getPluginSelect() {
        return new Select(pluginSelect);
    }
    
    
}
