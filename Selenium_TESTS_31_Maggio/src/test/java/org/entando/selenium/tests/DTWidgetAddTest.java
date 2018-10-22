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
import static org.junit.Assert.assertTrue;

import org.entando.selenium.pages.DTWidgetPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DTWidgetAddTest extends FunctionalTestBase {

    @Autowired
    public DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTWidgetPage dTWidgetPage;
    
    @Autowired
    public DTWidgetEditPage dTWidgetEditPage;
    
    @Autowired
    public Utils util;
    
    @Test
    public void EditWidget() throws InterruptedException {
        dTLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());

        dTDashboardPage.SelectSecondOrderLink("UX Patterns", "Widgets");

        Assert.assertTrue(Utils.checkButtonPresenceByName(driver, "New"));

        driver.manage().window().maximize();

        dTWidgetPage.getNewWidgetButton().click();

        sleep(300);
        dTWidgetEditPage.getCode().sendKeys("code_set_by_Selenium");
        dTWidgetEditPage.setEnTitle("English Title set by Selenium");
        dTWidgetEditPage.setItTitle("Titolo Italiano da Selenium");
        dTWidgetEditPage.setCustomUI("<p>Custom UI set by Selenium</p>");

        Utils.selectSetByValue(dTWidgetEditPage.getGroup(), "TestGroup1-modified");

        dTWidgetEditPage.save();

    }
}
