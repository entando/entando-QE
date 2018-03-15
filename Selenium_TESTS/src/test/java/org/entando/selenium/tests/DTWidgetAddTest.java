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

import static java.lang.Thread.sleep;
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
import org.openqa.selenium.interactions.Actions;
import org.entando.selenium.pages.DTWidgetPage;

public class DTWidgetAddTest extends FunctionalTest {

    @Test
    public void EditWidget() throws InterruptedException {

        DTLoginPage dtLoginPage = new DTLoginPage(driver);
        dtLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());

        DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
        dtDashboardPage.SelectSecondOrderLink("UX Pattern", "Widgets");

        DTWidgetPage dtWidget = new DTWidgetPage(driver);

        Utils util = new Utils();
        Assert.assertTrue(util.checkButtonPresenceByName(driver, "New"));

        driver.manage().window().maximize();

        dtWidget.getNewWidgetButton().click();

        sleep(300);
        DTWidgetEditPage dtWidgetEdit = new DTWidgetEditPage(driver);

        dtWidgetEdit.getCode().sendKeys("code_set_by_Selenium");
        dtWidgetEdit.setEnTitle("English Title set by Selenium");
        dtWidgetEdit.setItTitle("Titolo Italiano da Selenium");
        dtWidgetEdit.setCustomUI("<p>Custom UI set by Selenium</p>");

        util.selectSetByValue(dtWidgetEdit.getGroup(), "Customers");

        dtWidgetEdit.save();

    }
}
