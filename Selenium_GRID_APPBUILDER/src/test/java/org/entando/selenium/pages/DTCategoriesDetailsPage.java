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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Category Details page
 * 
 * @version 1.01
 */
public class DTCategoriesDetailsPage extends PageObject{
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button/span[text()='Info']/..")
    private WebElement infoButton;
    
    @FindBy(xpath = "//span[text()='Code']/../..//div[contains(@class, 'DetailCategory__detail-item')]")
    private WebElement codeField;
    
    @FindBy(xpath = "//span[text()='Title']/../..//span[text()='en']/..")
    private WebElement enField;
    
    @FindBy(xpath = "//span[text()='Title']/../..//span[text()='it']/..")
    private WebElement itField;
    
    
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

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getEnField() {
        return enField;
    }

    public WebElement getItField() {
        return itField;
    }
    
    
    
    public DTCategoriesDetailsPage(WebDriver driver) {
        super(driver);
    }
    
    
}
