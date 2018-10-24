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

/**
 * This class represent the Add Fragment page
 * 
 * @version 1.01
 */
public class DTFragmentNewPage extends PageObject {

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(name = "code")
    private WebElement fragmentCode;

    @FindBy(css = "#basic-tabs-pane-1 > div > div > textarea")
    private WebElement GUICode;

    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;

    
    public WebElement getPageTitle() {
        return this.pageTitle;
    }

    public WebElement getCode() {
        return this.fragmentCode;
    }

    public WebElement getCustomUI() {
        return this.GUICode;
    }

    public WebElement getSaveButton() {
        return this.saveButton;
    }
    
    public void setCode(String customCode) {
        this.fragmentCode.click();
        this.fragmentCode.clear();
        this.fragmentCode.sendKeys(customCode);
    }

    public void setGUICode(String customGUICode) {
        this.GUICode.click();
        this.GUICode.clear();
        this.GUICode.sendKeys(customGUICode);
    }

    public void save() {
        this.saveButton.click();
    }
    
    public WebElement getTooltip() {
        return tooltip;
    }
    
    public WebElement getHelp() {
        return help;
    }

    public DTFragmentNewPage(WebDriver driver) {
        super(driver);
    }

}
