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
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test to search a user in the Users List Page
 * 
 * @version 1.01
 */
public class DTUserSearchTest  extends UsersTestBase {
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
        
        //Usernames to set
        String baseUsername = "1SLNM_TEST_";
        Random generator = new Random();
        int randomNumber1 = generator.nextInt(899999) + 100000;
        String username1 = baseUsername + randomNumber1;
        
        int randomNumber2 = generator.nextInt(899999) + 100000;
        String username2 = baseUsername + randomNumber2;
        
        int randomNumber3 = generator.nextInt(899999) + 100000;
        String fakeUsername = baseUsername + randomNumber3;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        
        //Create a pages to search
        Assert.assertTrue(addUser(dTUsersPage, dTUserAddPage, username1));
        Assert.assertTrue(addUser(dTUsersPage, dTUserAddPage, username2));
        
        
        dTUsersPage.getAllButton().click();
        
        //Search first user
        dTUsersPage.getSearchField().sendKeys(username1);
        dTUsersPage.getSearchButton().click();
        //Wait loading results
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        Assert.assertEquals(1, dTUsersPage.getTable().tableSize());
        
        
        //Search second user
        dTUsersPage.getSearchField().clear();
        dTUsersPage.getSearchField().sendKeys(username2);
        dTUsersPage.getSearchButton().click();
        //Wait loading results
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        Assert.assertEquals(1, dTUsersPage.getTable().tableSize());
        
        
        //Search username prefix
        dTUsersPage.getSearchField().clear();
        dTUsersPage.getSearchField().sendKeys(baseUsername);
        dTUsersPage.getSearchButton().click();
        //Wait loading results
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        Assert.assertEquals(2, dTUsersPage.getTable().tableSize());
        
        
        //Search a fake username
        dTUsersPage.getSearchField().clear();
        dTUsersPage.getSearchField().sendKeys(fakeUsername);
        dTUsersPage.getSearchButton().click();
        //Wait loading results
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        Assert.assertTrue(dTUsersPage.getNotFoundMessage().isDisplayed());
        
        
        //Clear result
        dTUsersPage.getSearchField().clear();
        dTUsersPage.getAllButton().click();
        dTUsersPage.getSearchButton().click();
        
        //Wait loading table
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        
        //Delete the created page
        Assert.assertTrue(deleteUser(dTUsersPage, username1));
        
        Assert.assertTrue(deleteUser(dTUsersPage, username2));
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
