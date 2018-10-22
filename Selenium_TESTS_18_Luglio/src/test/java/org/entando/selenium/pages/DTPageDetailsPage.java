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

/**
 * This class represent the Page Details page
 * 
 * @version 1.01
 */
public class DTPageDetailsPage extends PageObject {
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button/span[text()='Info']/..")
    private WebElement infoButton;
    
    @FindBy(css = "table")
    private WebElement table;
    
    /*@FindBy(xpath ="//table[contains(@class, 'table-treegrid')]")
    private WebElement table;*/
    
    @FindBy(xpath = "//div[contains(@class, 'container')]")
    private WebElement container;
    

    public DTPageDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getInfoButton() {
        return infoButton;
    }
    
    public SimpleTable getTable(){
        return (new SimpleTable(table));
    }

    public WebElement getTableBody() {
        return table;
    }
    
    public List<WebElement> getErrors(){
        return container.findElements(By.xpath("//div[contains(@class, 'alert-info')]"));
    }
    
}
