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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


public class STAppBuilderLoginPage extends PageObject {

    private final String appBuilderUrl;
    @FindBy(id = "username")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement passWord;

    @FindBy(xpath = "//button[contains(@*, 'submit')]")
    private WebElement submitButton;

    public void logIn(SmokeTestUser user) {
        this.driver.get(appBuilderUrl);
        this.userName.clear();
        this.userName.sendKeys(user.getUsername());
        this.passWord.clear();
        this.passWord.sendKeys(user.getPassword());
        this.submitButton.click();
        ScreenPrintSaver.save(driver);
        WaitUntil.urlEndingWith(driver, "/dashboard");
        System.out.println(driver.getPageSource());
        WebElement table = driver.findElement(By.xpath("//div[@class='PagesList']/table"));
        WaitUntil.isVisible(driver, table);
        new FluentWait<>(table).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).until(
                webElement ->  table.findElements(By.xpath("tbody/tr")).size()==5);
    }

    public STAppBuilderLoginPage(WebDriver driver, String appBuilderUrl) {
        super(driver);
        this.appBuilderUrl = appBuilderUrl;
    }


}
