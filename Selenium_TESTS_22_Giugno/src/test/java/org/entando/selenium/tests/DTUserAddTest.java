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
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserAddPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test to add a user
 * 
 * @version 1.01
 */
public class DTUserAddTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUsersPage dTUsersPage;
    
    @Autowired
    public DTUserAddPage dTUserAddPage;
            
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
        String secondLevelLink = "Users";
        
        //Final page title
        String pageTitle = "Add";
        
        //Usernames to set
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String username = "1SLNM_TEST_" + randomNumber;
        String falseUsername = "1SLNM_TEST-*-" + randomNumber;
                
        //Passwords to set
        String password = Double.toString(Math.pow(10, super.minPasswordLength-1) + randomNumber);
        String falsePassword = Integer.toString((int)Math.round(Math.pow(10, super.minPasswordLength-2) + randomNumber));
        String falseConfirmPassword = Integer.toString((int)Math.round(Math.pow(10, super.minPasswordLength-3) + randomNumber));
        
        //Status string
        String statusString = " Active";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        
        dTUsersPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserAddPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserAddPage.getTooltip());
        Assert.assertTrue(dTUserAddPage.getTooltip().isDisplayed());
        
        //Verify "field required" warning
        dTUserAddPage.setUsernameField(falseUsername);
        dTUserAddPage.setPasswordField(falsePassword);
        dTUserAddPage.setPasswordConfirmField(falseConfirmPassword);
        dTUserAddPage.getProfileType().click();
        dTUserAddPage.getUsernameField().click();
        Logger.getGlobal().info(falsePassword);
        Assert.assertTrue(dTUserAddPage.getUsernameFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getPasswordFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getPasswordConfirmFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getProfileTypeError().isDisplayed());
                
        //Verify Switch
        Assert.assertFalse(dTUserAddPage.getStatusSwitch().isOn());
        
        //Verify Save buttons are disable
        Assert.assertFalse(dTUserAddPage.getSaveButton().isEnabled());
        
        
        
        //Compilation of the page
        dTUserAddPage.setUsernameField(username);
        dTUserAddPage.setPasswordField(password);
        dTUserAddPage.setPasswordConfirmField(password);
        dTUserAddPage.getProfileTypeSelect().selectByVisibleText(profileType);
        if(super.status){
            dTUserAddPage.getStatusSwitch().setOn();
        }else{
            dTUserAddPage.getStatusSwitch().setOff();
        }
        
        
        
        //Save and come back to the Users list
        Assert.assertTrue(dTUserAddPage.getSaveButton().isEnabled());
        dTUserAddPage.getSaveButton().click();
        
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        
        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUsersPage.getTable().findRowList(username, super.usersTableHeaderTitles.get(0));
        Assert.assertFalse(createdUser.isEmpty());
        
        //Verify "Status" is "Active"
        WebElement cell = dTUsersPage.getTable().getCell(username, super.usersTableHeaderTitles.get(0), super.usersTableHeaderTitles.get(3));
        Assert.assertEquals(statusString, cell.getText());
        
        //Delete the created user
        Assert.assertTrue(deleteUser(dTUsersPage, username));
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
    }
}//end class
