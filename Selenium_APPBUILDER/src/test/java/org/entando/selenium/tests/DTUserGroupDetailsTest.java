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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserGroupDetailsPage;
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import static org.entando.selenium.testHelpers.UsersTestBase.groupsTableHeaderTitles;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Group Details page
 * 
 * @version 1.01
 */
public class DTUserGroupDetailsTest extends UsersTestBase {
    /*
        Pages used on this test
     */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserGroupsPage dTUserGroupsPage;
    
    @Autowired
    public DTUserGroupDetailsPage dTUserGroupDetailsPage;

    
    @Test
    public void runTest() throws InterruptedException{
        /*
            Parameters
         */ 
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Groups";
        
        //Final page title
        String pageTitle = "Details";
        
        //Kebab action
        String kebabAction = "Details";
                
        //Group name to visit
        String groupName = "1SeleniumTest_DontTouch";
        
        //Group code
        String groupCode = "1seleniumtest_dontto";
        
        //User in Users Tab
        String username = "1SLNM_DONT_TOUCH";
        
        //Expected groups table header titles
        List<String>usersTabTableHeaderTitles = 
                Arrays.asList("Username", "Last login", "Status", "Actions");

        
        /*
            Actions and asserts
         */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getAddButton());
        
        Kebab kebab = dTUserGroupsPage.getTable().getKebabOnTable(groupName, 
                groupsTableHeaderTitles.get(0), groupsTableHeaderTitles.get(2));
        Assert.assertFalse(kebab == null);
                
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        Utils.waitUntilIsVisible(driver, dTUserGroupDetailsPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserGroupDetailsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserGroupDetailsPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserGroupDetailsPage.getTooltip());
        Assert.assertTrue(dTUserGroupDetailsPage.getTooltip().isDisplayed());
        
        //Wait loading Pages Tab content
        Utils.waitUntilIsPresent(driver, dTUserGroupDetailsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserGroupDetailsPage.spinnerTag);
        
        //Asserts field content are corrected
        Assert.assertEquals(groupName, dTUserGroupDetailsPage.getNameField().getText());
        Assert.assertEquals(groupCode, dTUserGroupDetailsPage.getGroupField().getText());
        
        
        //Exploring Users tab
        dTUserGroupDetailsPage.getUsersTab().click();
        //Wait loading content
        Utils.waitUntilIsPresent(driver, dTUserGroupDetailsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserGroupDetailsPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTUserGroupDetailsPage.getTableBody());
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(usersTabTableHeaderTitles, dTUserGroupDetailsPage.getTable().getHeaderTitlesList());
        
        //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue(checkBrowsableTable(dTUserGroupDetailsPage));
        
        //Assert the presence of the users group in the Users Tab table
        List<WebElement> users = dTUserGroupDetailsPage.getTable()
                .findRowList(username, usersTabTableHeaderTitles.get(0));
        
        Assert.assertTrue(users.size() == 1);
        
        
        /**
         * Complete test with the others Tabs
         */
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/ 
    }
    
}
