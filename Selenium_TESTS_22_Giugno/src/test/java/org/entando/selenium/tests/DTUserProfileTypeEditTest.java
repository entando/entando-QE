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
import org.entando.selenium.pages.DTUserProfileTypeAddPage;
import org.entando.selenium.pages.DTUserProfileTypeEditPage;
import org.entando.selenium.pages.DTUserProfileTypePage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

 /**
 * This class perform a test of the Edit Profile Type
 * 
 * @version 1.01
 */
public class DTUserProfileTypeEditTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserProfileTypePage dTUserProfileTypePage;
    
    @Autowired
    public DTUserProfileTypeAddPage dTUserProfileTypeAddPage;
    
    @Autowired
    public DTUserProfileTypeEditPage dTUserProfileTypeEditPage;
    
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
        String secondLevelLink = "Profile types";
        
        //Final page title
        String pageTitle = "Edit";
        
        //Kebab action
        String kebabAction = "Edit";
                
        //Profile type name to set
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String profileTypeName = "1SLNM_TEST_" + randomNumber;
        
        
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getNewButton());
        
        //Create a profile type
        Assert.assertTrue(addProfileType(dTUserProfileTypePage, dTUserProfileTypeAddPage,
                profileTypeName));
                
        
        Kebab kebab = dTUserProfileTypePage.getTable().getKebabOnTable(profileTypeName, rolesTableHeaderTitles.get(0), rolesTableHeaderTitles.get(2));
        Assert.assertFalse(kebab == null);
                
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        
        
        Utils.waitUntilIsVisible(driver, dTUserProfileTypeEditPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserProfileTypeEditPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserProfileTypeEditPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserProfileTypeEditPage.getTooltip());
        Assert.assertTrue(dTUserProfileTypeEditPage.getTooltip().isDisplayed());
        
        //Verify 
        
        //Compilation of the page
        dTUserProfileTypeEditPage.setNameField(profileTypeName);        
        
        
        //Save and return
        dTUserProfileTypeEditPage.getSaveButton().click();
        
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getPageTitle());      
        //Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getTableBody());
        //sleep(1000);
        
        //Assert the presence of the created profile type in the Profile type table
        List<WebElement> createdUser = dTUserProfileTypePage.getTable()
                .findRowList(profileTypeName, rolesTableHeaderTitles.get(0));
        
        Assert.assertTrue(!createdUser.isEmpty());
        
        //Delete the Profile type created for the test
        //Assert.assertTrue(deleteProfileType(dTUserProfileTypePage, profileTypeName));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/        
    }
}//end class
