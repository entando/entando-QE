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
package org.entando.selenium.smoketests;

import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class STKeycloakBasedLoginPage extends PageObject {

    private final String loginUrl;
    @FindBy(id = "username")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement passWord;
    @FindBy(id = "password-new")
    private WebElement passWordNew;
    @FindBy(id = "password-confirm")
    private WebElement passWordConfirm;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public STKeycloakBasedLoginPage(WebDriver driver, String loginUrl) {
        super(driver);
        this.loginUrl = loginUrl;
    }

    public void logIn(SmokeTestUser user) {
        try {
            this.driver. manage().deleteAllCookies();
            this.driver.get(loginUrl);
            WaitUntil.urlContaining(driver, "/auth/realms/entando/protocol/openid-connect/auth");
            ScreenPrintSaver.save(driver);
            this.userName.clear();
            this.userName.sendKeys(user.getUsername());
            this.passWord.clear();
            this.passWord.sendKeys(user.getPassword());
            this.submitButton.click();
            if (this.driver.getCurrentUrl().contains("execution=UPDATE_PASSWORD")) {
                ScreenPrintSaver.save(driver);
                this.passWordNew.sendKeys(user.getPassword());
                this.passWordConfirm.sendKeys(user.getPassword());
                this.submitButton.click();
            }
            waitForReturnPage();
            ScreenPrintSaver.save(driver);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected abstract void waitForReturnPage();

}
