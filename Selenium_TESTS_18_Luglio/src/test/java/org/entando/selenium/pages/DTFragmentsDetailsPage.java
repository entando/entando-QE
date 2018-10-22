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
 * This class prepresent the Fragment Details page
 * 
 * @version 1.01
 */
public class DTFragmentsDetailsPage extends PageObject {
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(xpath ="//table[contains(@class, 'table-bordered')]")
    private WebElement detailFragmentTable;
    
    @FindBy(xpath ="//table[contains(@class, 'WidgetTypeReferenceTable')]")
    private WebElement widgetTypeReferenceTable;
    
    @FindBy(xpath = "//button[contains(., 'Edit')]")
    private WebElement editButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    
    public DTFragmentsDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public SimpleTable getDetailFragmentTable() {
        return new SimpleTable(detailFragmentTable);
    }

    public SimpleTable getWidgetTypeReferenceTable() {
        return new SimpleTable(widgetTypeReferenceTable);
    }

    public WebElement getEditButton() {
        return editButton;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }
    
    
}
