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
 * This class performs a test to delete a user in the Users List Page
 * 
 * @version 1.01
 */
public class DTUserDeleteTest extends UsersTestBase{
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
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String username = "1SLNM_TEST_" + randomNumber;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        
        //Create a page to delete
        Assert.assertTrue(addUser(dTUsersPage, dTUserAddPage, username));
        
        //Delete the created page
        Assert.assertTrue(deleteUser(dTUsersPage, username));
        
        //Assert the element is not present in the table
        /*Debug code*/ Logger.getGlobal().log(Level.INFO, "Username: {0}", username);
        List<WebElement> foundedPages = dTUsersPage.getTable()
                .findRowList(username, usersTableHeaderTitles.get(0));
        Assert.assertTrue(foundedPages.isEmpty());
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}
