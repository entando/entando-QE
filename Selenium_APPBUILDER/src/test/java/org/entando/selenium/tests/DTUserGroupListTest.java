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
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Users Group List page
 * 
 * @version 1.03
 */
public class DTUserGroupListTest extends UsersTestBase {
    
    /*
        Pages used on this test
     */    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserGroupsPage dTUserGroupsPage;
    
    /*
        Test
     */
    @Test
    public void test() throws InterruptedException{
               
        /*
            Parameters
         */ 
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Groups";
        
        //Final page title
        String pageTitle = "Groups";
        
        //Buttons name
        String button1 = "Add";
        
        
        /*
            Actions and asserts
         */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTUserGroupsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserGroupsPage.spinnerTag);   
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserGroupsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserGroupsPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getTooltip());
        Assert.assertTrue(dTUserGroupsPage.getTooltip().isDisplayed());

        //Asserts the presence of the BUTTON with displayed name as argument
        Assert.assertTrue(dTUserGroupsPage.getAddButton().getText().equals(button1));

        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(groupsTableHeaderTitles,
                dTUserGroupsPage.getTable().getHeaderTitlesList());
        
        //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue(checkBrowsableTable(dTUserGroupsPage));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/ 
    }
}
