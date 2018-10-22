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

import org.entando.selenium.pages.DTLoginPage;

import org.entando.selenium.pages.DTDashboardPage;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;

import org.entando.selenium.pages.DTUsersPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DTUsersListTest extends FunctionalTestBase {
    
    @Autowired
    public DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUsersPage dTUsersPage;
    
    @Autowired
    public Utils util;

    @Test
    public void runTest() {
        dTLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());

        List<String> expectedHeaderTitles = Arrays.asList("Username", "Full Name", "Email", "Status", "Actions");
        dTDashboardPage.SelectSecondOrderLink("User Management", "Users");
        // dtDashboardPage.selectTab("User Settings");

        dTUsersPage.getPageTitle().getText();

        List<String> fetchedHeaderTitles = Utils.fetchHeaderTitles(dTUsersPage.getTableHeader());

        //Asserts that table column names are the expected ones.
        Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);

        String pageTitle = "Users";
        //Asserts that the page title is the expected one
        Assert.assertEquals(pageTitle, dTUsersPage.getPageTitle().getText());

        //Asserts the presence of the button with displayed name as argument
        Assert.assertTrue(Utils.checkButtonPresenceByName(driver, "Add"));

    }
}
