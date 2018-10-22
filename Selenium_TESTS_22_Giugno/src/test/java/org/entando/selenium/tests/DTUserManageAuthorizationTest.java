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
import org.entando.selenium.pages.DTUserManageAuthorityPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of Manage Authorization page
 * 
 * @version 1.01
 */
public class DTUserManageAuthorizationTest extends UsersTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUsersPage dTUsersPage;
    
    @Autowired
    public DTUserManageAuthorityPage dTUserManageAuthorityPage;
    
    /*
        Test
     */
    @Test
    public void runTest() throws InterruptedException{
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Users";
        
        String username = "1SLNM_DONT_TOUCH";
        
        //Final page title
        String pageTitle = "Authorizations for " + username;
        
        //Manage Authorization Table header
        List<String> authTableHeaderTitles = Arrays.asList("User Group","User Role","Actions");
        
        String kebabAction = "Manage autorization for: " + username;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        
        //Manage the page
        Kebab kebab = dTUsersPage.getTable().getKebabOnTable(username, usersTableHeaderTitles.get(0), usersTableHeaderTitles.get(4));
        Assert.assertFalse(kebab == null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        
        
        Utils.waitUntilIsVisible(driver, dTUserManageAuthorityPage.getPageTitle());
        
        Utils.waitUntilIsPresent(driver, dTUserManageAuthorityPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserManageAuthorityPage.spinnerTag);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserManageAuthorityPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserManageAuthorityPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserManageAuthorityPage.getTooltip());
        Assert.assertTrue(dTUserManageAuthorityPage.getTooltip().isDisplayed());
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(authTableHeaderTitles, dTUserManageAuthorityPage.getTable().getHeaderTitlesList());
        
        
        //Asserts the presence of the labels
        assertTrue(dTUserManageAuthorityPage.getGroupLabel().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getRoleLabel().isDisplayed());
        
        //Asserts the presence of the buttons
        assertTrue(dTUserManageAuthorityPage.getAddButton().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getSaveButton().isDisplayed());
        
        
        SimpleTable table = dTUserManageAuthorityPage.getTable();
        
        //Asserts the presence of a authorization 
        Assert.assertEquals(1, table.tableSize());
        
        //Delete Authorization
        String itemName = table.getColumnListOfString(authTableHeaderTitles.get(0)).get(0);
        WebElement trashButton = table.getCellButton(itemName, authTableHeaderTitles.get(0), authTableHeaderTitles.get(2));
        trashButton.click();
        Utils.waitUntilIsVisible(driver, dTUserManageAuthorityPage.getInfoMessage());
        
        //Asserts the presence of info message alert
        Assert.assertTrue(dTUserManageAuthorityPage.getInfoMessage().isDisplayed());
        
        //Assert the alert message
        dTUserManageAuthorityPage.getSaveButton().click();
        WebElement errorMessage = dTUserManageAuthorityPage.getErrorMessage();
        Utils.waitUntilIsVisible(driver, errorMessage);
        Assert.assertTrue(errorMessage.isDisplayed());
        
        //Click on 'X' button to delete alert message
        dTUserManageAuthorityPage.getErrorMessageButton().click();
        
        
        //Add a authorization
        dTUserManageAuthorityPage.getUserGroup().selectByVisibleText(userGroup);
        dTUserManageAuthorityPage.getUserRole().selectByVisibleText(userRole);
        dTUserManageAuthorityPage.getAddButton().click();
        
        table = dTUserManageAuthorityPage.getTable();
        
        //Asserts the presence of a authorization 
        Assert.assertEquals(1, table.tableSize());
        
        dTUserManageAuthorityPage.getSaveButton().click();
        
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
