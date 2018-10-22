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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserProfileTypePage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Profile Type page
 * 
 * @version 1.01
 */
public class DTUserProfileTypeTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserProfileTypePage dTUserProfileTypePage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Profile types";
        
        //Final page title
        String pageTitle = "Profile Types";
                
        //Buttons name
        String button1 = "Add";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
                
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserProfileTypePage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserProfileTypePage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getTooltip());
        Assert.assertTrue(dTUserProfileTypePage.getTooltip().isDisplayed());

        //Asserts the presence of the BUTTON with displayed name as argument
        Assert.assertTrue(dTUserProfileTypePage.getAddButton().getText().equals(button1));

        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(profileTypeTableHeaderTitles, dTUserProfileTypePage.getTable().getHeaderTitlesList());
        
        //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue(checkBrowsableTable(dTUserProfileTypePage));
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
