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

import org.entando.selenium.testHelpers.UsersTestBase;
import static java.lang.Thread.sleep;
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTDashboardPage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.entando.selenium.pages.DTUsersPage;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * This class perform a test of the Users page
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTUsersListTest extends UsersTestBase {
    
    public DTUsersListTest(String browserName, String platformName) {
        super(browserName, platformName);
    }
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Pages used on this test
        */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTUsersPage dTUsersPage = new DTUsersPage(driver);
    
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Users";
        
        //Final page title
        String pageTitle = "Users";
                
        //Buttons name
        String button1 = "Add";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);   
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUsersPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUsersPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUsersPage.getTooltip());
        Assert.assertTrue(dTUsersPage.getTooltip().isDisplayed());

        //Asserts the presence of the BUTTON with displayed name as argument
        Assert.assertTrue(dTUsersPage.getAddButton().getText().equals(button1));

        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(usersTableHeaderTitles, dTUsersPage.getTable().getHeaderTitlesList());
        
        //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue("Some problems with Browsable Table (See logger messages)", checkBrowsableTable(dTUsersPage));
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
