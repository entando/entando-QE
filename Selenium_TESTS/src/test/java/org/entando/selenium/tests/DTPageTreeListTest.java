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

import org.entando.selenium.utils.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

import org.entando.selenium.pages.DTPageTreePage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DTPageTreeListTest extends FunctionalTest {
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
    
    @Autowired
    public Utils util;

    @Test
    public void runTest() throws InterruptedException {
        login();
        goTo("Page Creator", "Page Tree");

        List<String> expectedHeaderTitles = Arrays.asList("Page tree", "Status", "Displayed in menu", "Actions");

        List<String> fetchedHeaderTitles = util.fetchHeaderTitles(dTPageTreePage.getTableHeader());

        //Asserts that table column names are the expected ones.
        Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);

        String pageTitle = "Page Tree";
        //Asserts that the page title is the  expected one.
        Assert.assertEquals(pageTitle, dTPageTreePage.getPageTitle().getText());

        //Asserts the presence of the button with displayed name as argument
        Assert.assertTrue(util.checkButtonPresenceByName(driver, "Add"));

    }
}
