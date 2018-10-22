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
package org.entando.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class prepresent the Receipt DT Login Page
 * 
 * @version 1.00
 */

public class ReceiptDTLoginPage extends PageObject {

    @FindBy(xpath = "//li[contains(@class, 'UserDropdown')]")
    private WebElement header;
    
    @FindBy(css = "table > tbody")
    private WebElement tableBody;

    public ReceiptDTLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        Utils.waitUntilIsVisible(driver, tableBody); //header
        return tableBody.isDisplayed();
    }

    public String confirmationHeader() {
        return header.getText();
    }

}
