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
import org.openqa.selenium.support.ui.Select;

public class DTWidgetEditPage extends PageObject {

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(name = "code")
    private WebElement widgetCode;

    @FindBy(name = "titles.en")
    private WebElement enTitle;

    @FindBy(name = "titles.it")
    private WebElement itTitle;

    @FindBy(xpath = "//select")
    private WebElement selectGroup;
    Select group;

    @FindBy(css = "#basic-tabs-pane-1 > textarea")
    private WebElement customUI;

    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;

    public WebElement getPageTitle() {
        return this.pageTitle;
    }

    public WebElement getCode() {
        return this.widgetCode;
    }

    public WebElement getEnTitle() {
        return this.enTitle;
    }

    public WebElement getItTitle() {
        return this.itTitle;
    }

    public WebElement getCustomUI() {
        return this.customUI;
    }

    public WebElement getGroup() {
        return this.selectGroup;
    }

    public WebElement getsaveButton() {
        return this.saveButton;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle.clear();
        this.enTitle.sendKeys(enTitle);

    }

    public void setItTitle(String itTitle) {
        this.itTitle.clear();
        this.itTitle.sendKeys(itTitle);

    }

    public void setGroup(int numOrder) {
        getSelectGroup().getOptions().get(numOrder).click();
    }

    public void setCustomUI(String customUIContent) {
        this.customUI.click();
        this.customUI.clear();
        this.customUI.sendKeys(customUIContent);
    }

    public void save() {
        this.saveButton.click();
    }

    public DTWidgetEditPage(WebDriver driver) {
        super(driver);
    }
    
    private Select getSelectGroup(){
        if(group == null){
            group = new Select(selectGroup);
        }
        return group;
    }

}
