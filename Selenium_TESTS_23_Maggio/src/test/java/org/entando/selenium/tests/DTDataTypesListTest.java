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
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTDataTypesPage;
import org.entando.selenium.pages.DTDashboardPage;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Data Type page
 * 
 * @version 1.01
 */

public class DTDataTypesListTest extends FunctionalTest {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTDataTypesPage dTDataTypesPage;

    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Data";
        String secondLevelLink = "Data Types";
        
        //Final page title
        String pageTitle = "Data Type";
        
        //Expected table header titles
        List<String> expectedHeaderTitles = Arrays.asList("Name", "Code", "Status", "Actions");
        
        //Buttons name
        String button1 = "New";
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Asserts table column names are the expected ones.
        List<String> fetchedHeaderTitles = Utils.fetchHeaderTitles(dTDataTypesPage.getTableHeader());
        Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);

        //Asserts the page title is the expected one
        Assert.assertEquals(pageTitle, dTDataTypesPage.getPageTitle().getText());
        
        //Asserts the presence of the help button
        dTDataTypesPage.getHelp().click();
        Assert.assertTrue(dTDataTypesPage.getTooltip().isDisplayed());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
        //Asserts the presence of the button with displayed name as argument
        Assert.assertTrue(Utils.checkButtonPresenceByName(driver, button1));
    }
}
