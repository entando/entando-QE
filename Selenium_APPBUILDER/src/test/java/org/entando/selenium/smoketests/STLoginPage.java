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

import org.apache.commons.io.FileUtils;
import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

//import org.openqa.selenium.WebElement;

public class STLoginPage extends PageObject {

    @FindBy(id = "username")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement passWord;

    @FindBy(xpath = "//button[contains(@*, 'submit')]")
    private WebElement submitButton;

    public void logIn(String userName, String passWord) {
        try {
            this.userName.clear();
            this.userName.sendKeys(userName);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("test1.png"));

            this.passWord.clear();
            this.passWord.sendKeys(passWord);
            src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("test2.png"));

            this.submitButton.click();
            src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("test3.png"));
            System.out.println("CLicked submitButton on " + super.driver.getCurrentUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public STLoginPage(WebDriver driver) {
        super(driver);
    }


}
