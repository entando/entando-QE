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
import org.entando.selenium.pages.DTUserRoleAddPage;
import org.entando.selenium.pages.DTUserRolesPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Delete User Role
 * 
 * @version 1.01
 */
public class DTUserRoleDeleteTest  extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserRolesPage dTUserRolesPage;
    
    @Autowired
    public DTUserRoleAddPage dTUserRoleAddPage;
            
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
        String secondLevelLink = "Roles";
        
        //Role name to set
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String roleName = "1SLNM_TEST_" + randomNumber;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUserRolesPage.getAddButton());
        
        //Create a role to delete
        Assert.assertTrue(addRole(dTUserRolesPage, dTUserRoleAddPage, roleName));
        
        //Delete the created page
        Assert.assertTrue(deleteRole(dTUserRolesPage, roleName));
        
        //Assert the element is not present in the table
        /*Debug code*/ Logger.getGlobal().log(Level.INFO, "Username: {0}", userRole);
        List<WebElement> foundedPages = dTUserRolesPage.getTable()
                .findRowList(userRole, usersTableHeaderTitles.get(0));
        Assert.assertTrue(foundedPages.isEmpty());
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }

}//end class
