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
package org.entando.selenium.tests;

import org.junit.Assert;
import org.entando.selenium.utils.*;

import org.entando.selenium.pages.DTWidgetEditPage;
import org.entando.selenium.pages.DTLoginPage;

import org.entando.selenium.pages.DTDashboardPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DTWidgetEditTest extends FunctionalTest {

    @Test
    public void EditWidget() {

        DTLoginPage dtLoginPage = new DTLoginPage(driver);
        dtLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());

        DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
        dtDashboardPage.SelectSecondOrderLink("UX Pattern", "Widgets");

        Utils util = new Utils();

        Assert.assertTrue(util.checkButtonPresenceByName(driver, "New"));

        driver.manage().window().maximize(); //added to resolve issue on non adaptivity of the application

        String xPathLinks = "//a[contains(@href,'/widget/edit')]";
        WebElement link = driver.findElement(By.xpath(xPathLinks));

        link.click();

        String pageTitle = "Edit Widget";

        DTWidgetEditPage dtWidgetEditPage = new DTWidgetEditPage(driver);

        //Asserts that the page title is the expected one
        Assert.assertEquals(pageTitle, dtWidgetEditPage.getPageTitle().getText());

        dtWidgetEditPage.setEnTitle("English Title changed by Selenium");
        dtWidgetEditPage.setItTitle("Titolo Italiano modificato da Selenium");
        dtWidgetEditPage.setCustomUI("<p>Custom UI changed and set by Selenium</p>");

        util.selectSetByValue(dtWidgetEditPage.getGroup(), "Customers");

        dtWidgetEditPage.save();

    }
}
