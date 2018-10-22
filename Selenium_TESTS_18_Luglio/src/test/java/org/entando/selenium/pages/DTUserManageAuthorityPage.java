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

import java.util.List;
import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represent the User Manage Authorization page
 * 
 * @version 1.01
 */
public class DTUserManageAuthorityPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[@class='UserAuthorityTable']//label/span[text()='User Group']")
    private WebElement groupLabel;
    
    @FindBy(xpath = "//*[@class='UserAuthorityTable']//label/span[text()='User Role']")
    private WebElement roleLabel;
    
    @FindBy(xpath = "//*[@class='UserAuthorityTable']//select[@class='form-control']")
    private List<WebElement> authorizationControls;
    Select userGroup;
    Select userRole;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button[@type='button']/span[text()[contains(.,'Add')]]")
    private WebElement addButton;
         
    @FindBy(xpath = "//button[@type='submit']/span[text()[contains(.,'Save')]]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//table[contains(@class, 'table')]")
    private WebElement table;
    
    @FindBy(xpath = "//div[contains(@class, 'alert-danger')]")
    private WebElement errorMessage;
    
    @FindBy(xpath = "//div[contains(@class, 'alert-info')]")
    private WebElement infoMessage;
    
    By buttonTag = By.tagName("button");
    
    public final By spinnerTag = By.xpath("//div[contains(@class, 'spinner-md')]");
    

    public List<WebElement> getAuthorizationControls() {
        return authorizationControls;
    }

    public WebElement getAddButton() {
        return addButton;
    }
    
    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    public Select getUserGroup(){
        if(userGroup == null){
            userGroup = new Select(authorizationControls.get(0));
        }
        return userGroup;
    }
    
    public Select getUserRole(){
        if(userRole == null){
            userRole = new Select(authorizationControls.get(1));
        }
        return userRole;
    }
    
    public WebElement getRoleLabel() {
        return roleLabel;
    }

    public WebElement getGroupLabel() {
        return groupLabel;
    }
    
    public SimpleTable getTable(){
        return new SimpleTable(table);
    }
    
    public WebElement getTableBody(){
        return table;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }
    
    public WebElement getErrorMessageButton() {
        return errorMessage.findElement(buttonTag);
    }

    public WebElement getInfoMessage() {
        return infoMessage;
    }
    
    
    
    public DTUserManageAuthorityPage(WebDriver driver) {
        super(driver);
    }
    
}
