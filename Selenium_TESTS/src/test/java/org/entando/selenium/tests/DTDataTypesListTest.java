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

import org.entando.selenium.pages.DTDataTypesPage;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class DTDataTypesListTest extends FunctionalTest {
    
    @Autowired
    public DTDataTypesPage dTDataTypesPage;

    @Autowired
    public Utils util;
    
    @Test
    public void runTest() {
        login();
        goTo("Data", "Data Types");
            
        List<String> expectedHeaderTitles = Arrays.asList("Name", "Code", "Status", "Actions");
        dTDataTypesPage.getPageTitle().getText();

        List<String> fetchedHeaderTitles = util.fetchHeaderTitles(dTDataTypesPage.getTableHeader());

        //Asserts that table column names are the expected ones.
        Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);

        String pageTitle = "Data Type";
        //Asserts that the page title is the expected one
        Assert.assertEquals(pageTitle, dTDataTypesPage.getPageTitle().getText());

        //Asserts the presence of the button with displayed name as argument
        Assert.assertTrue(util.checkButtonPresenceByName(driver, "Add"));

    }
}
