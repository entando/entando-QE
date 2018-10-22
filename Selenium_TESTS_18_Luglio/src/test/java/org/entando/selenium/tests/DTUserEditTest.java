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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserAddPage;
import org.entando.selenium.pages.DTUserEditPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the User Edit page
 * 
 * @version 1.01
 */
public class DTUserEditTest extends UsersTestBase{
    /*
        Pages used on this test
    */   
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUsersPage dTUsersPage;
    
    @Autowired
    public DTUserAddPage dTUserAddPage;
    
    @Autowired
    public DTUserEditPage dTUserEditPage;
    
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
        
        //Final page title
        String pageTitle = "Edit";
        
        //Usernames to set
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String username = "1SLNM_TEST_" + randomNumber;
        
        String kebabAction = "Edit";
        
        //Password to set
        String password = Double.toString(Math.pow(10, minPasswordLength-1) + randomNumber);
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        
        //Create a user to edit
        Assert.assertTrue(addUser(dTUsersPage, dTUserAddPage, username));
        
        
        //Edit the user
        Kebab kebab = dTUsersPage.getTable().getKebabOnTable(username, usersTableHeaderTitles.get(0), usersTableHeaderTitles.get(4));
        Assert.assertFalse(kebab == null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        
        
        
        Utils.waitUntilIsVisible(driver, dTUserEditPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserEditPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserEditPage.getTooltip());
        Assert.assertTrue(dTUserEditPage.getTooltip().isDisplayed());
        
        //Asserts the switch buttons status
        Utils.waitUntilIsVisible(driver, dTUserEditPage.getResetSwitchElement());
        Utils.waitUntilIsVisible(driver, dTUserEditPage.getStatusSwitchElement());
        
        sleep(200);
        
        Assert.assertFalse(dTUserEditPage.getResetSwitch().isOn());
        Assert.assertTrue(dTUserEditPage.getStatusSwitch().isOn());
        
        //Compilation of the page
        dTUserEditPage.setPassword(password);
        dTUserEditPage.setPasswordConfirm(password);
        dTUserEditPage.getResetSwitch().setOn();
        
        //Asserts the fields contains correct text
        Assert.assertFalse(dTUserEditPage.getUsernameField().isEnabled());
        Assert.assertEquals(username, dTUserEditPage.getUsernameField().getAttribute("value"));
     
        dTUserEditPage.getSaveButton().click();
        
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        //Delete the created page
        Assert.assertTrue(deleteUser(dTUsersPage, username));
        
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}
