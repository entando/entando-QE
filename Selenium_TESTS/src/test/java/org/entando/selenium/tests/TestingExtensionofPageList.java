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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.entando.selenium.pages.DTWidgetEditPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestingExtensionofPageList extends DTWidgetsListTest {

    @Autowired
    public DTWidgetEditPage dTWidgetEditPage;
    
    @Test
    public void runTest() {

        super.runTest();
        driver.manage().window().maximize(); //added to resolve issue on non adaptivity of the application

        String xPathLinks = "//a[contains(@href,'/widget/edit')]";
        WebElement link = driver.findElement(By.xpath(xPathLinks));

        link.click();

        String pageTitle = "Edit Widget";

        //Asserts that the page title is the expected one
        Assert.assertEquals(pageTitle, dTWidgetEditPage.getPageTitle().getText());

        dTWidgetEditPage.setEnTitle("English Title changed by Selenium");
        dTWidgetEditPage.setItTitle("Titolo Italiano modificato da Selenium");
        dTWidgetEditPage.setCustomUI("<p>Custom UI changed and set by Selenium</p>");

        util.selectSetByValue(dTWidgetEditPage.getGroup(), "Customers");

        dTWidgetEditPage.save();

    }

}
