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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author simone_picciau
 */
public class DTUserEditPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(id = "password")
    private WebElement password;
    
    @FindBy(id = "passwordConfirm")
    private WebElement passwordConfirm;
    
    @FindBy(css = "button.pull-right")
    private WebElement saveButton;
    
    @FindBy(css = "div.bootstrap-switch-off")
    private WebElement resetSwitch;
    
    @FindBy(css = "div.bootstrap-switch-on")
    private WebElement statusSwitch;
    
    /*
    
    */
    public void setPasswordFields(String password) {
        this.password.clear();
        this.password.sendKeys(password);
        this.passwordConfirm.clear();
        this.passwordConfirm.sendKeys(password);
    }
    
    public WebElement getResetSwitch(){
        return this.resetSwitch;
    }
    
    public boolean getIsOnResetSwitch(){
        return resetSwitch.getAttribute("class").contains("switch-on");
    }
    
    public void setOnResetSwitch(){
        if (resetSwitch.getAttribute("class").contains("switch-off"))
        {
            this.resetSwitch.click();
        }
    }
    
    public void setOffResetSwitch(){
        if (resetSwitch.getAttribute("class").contains("switch-on"))
        {
            this.resetSwitch.click();
        }
    }
    
    public WebElement getStatusSwitch(){
        return this.statusSwitch;
    }
    
    public boolean getIsOnStatusSwitch(){
        return statusSwitch.getAttribute("class").contains("switch-on");
    }
    
    public void setOnStatusSwitch(){
        if (statusSwitch.getAttribute("class").contains("switch-off"))
        {
            this.statusSwitch.click();
        }
    }
    
    public void setOffStatusSwitch(){
        if (statusSwitch.getAttribute("class").contains("switch-on"))
        {
            this.statusSwitch.click();
        }
    }

    public WebElement getPageTitle() {
        return this.pageTitle;
    }
    
    public WebElement getSaveButton() {
        return this.saveButton;
    }

    public void save() {
        this.saveButton.click();
    }
    
    public DTUserEditPage(WebDriver driver) {
        super(driver);
    }
    
}
