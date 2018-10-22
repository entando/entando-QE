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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Details User Role page
 * 
 * @version 1.01
 */
public class DTUserRoleDetailsPage extends PageObject {
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//*[contains(@class, 'DetailRole__detail-item')][1]")
    private WebElement code;
    
    @FindBy(xpath = "//*[contains(@class, 'DetailRole__detail-item')][2]")
    private WebElement name;
    
    @FindBy(xpath = "//*[contains(@class, 'DetailRole__detail-item')][3]")
    private WebElement permission;
    
    @FindBy(xpath = "//table")
    private WebElement table;
    
    
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getCode() {
        return code;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getPermission() {
        return permission;
    }

    public WebElement getTable() {
        return table;
    }
    
    public SimpleTable getSimpleTable() {
        return new SimpleTable(table);
    }
    
    public DTUserRoleDetailsPage(WebDriver driver) {
        super(driver);
    }
    
}
