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
import org.entando.selenium.pages.DTUserRoleDetailsPage;
import org.entando.selenium.pages.DTUserRolesPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Details User Role
 * 
 * @version 1.01
 */
public class DTUserRoleDetailsTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserRolesPage dTUserRolesPage;
    
    @Autowired
    public DTUserRoleDetailsPage dTUserRoleDetailsPage;
    
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
                
        //Final page title
        String pageTitle = "Details";
        
        //Kebab action
        String kebabAction = "Details";
        
        //Default role name
        String roleName = "1SeleniumTest_DontTouch";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUserRolesPage.getAddButton());
        
        Kebab kebab = dTUserRolesPage.getTable().getKebabOnTable(roleName, rolesTableHeaderTitles.get(0), rolesTableHeaderTitles.get(2));
        Assert.assertFalse(kebab == null);
                
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTUserRoleDetailsPage.getTable());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserRoleDetailsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserRoleDetailsPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserRoleDetailsPage.getTooltip());
        Assert.assertTrue(dTUserRoleDetailsPage.getTooltip().isDisplayed());
        
        //Assert the name of the role is correct
        Assert.assertEquals(roleName, dTUserRoleDetailsPage.getName().getText());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/ 
    }
}//end class
