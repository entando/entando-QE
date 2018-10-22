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
import org.entando.selenium.pages.DTUserProfileTypePage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

 /**
 * This class perform a test of the Add Profile Type
 * 
 * @version 1.01
 */
public class DTUserProfileTypeAddTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserProfileTypePage dTUserProfileTypePage;
    
    @Autowired
    public DTUserProfileTypeAddPage dTUserProfileTypeAddPage;
    
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
        String pageTitle = "Add";
                
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
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getAddButton());
        
        dTUserProfileTypePage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserProfileTypeAddPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserProfileTypeAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserProfileTypeAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserProfileTypeAddPage.getTooltip());
        Assert.assertTrue(dTUserProfileTypeAddPage.getTooltip().isDisplayed());
        
        //Verify "field required" warning
        dTUserProfileTypeAddPage.getNameField().click();
        dTUserProfileTypeAddPage.getCodeField().click();
        dTUserProfileTypeAddPage.getNameField().click();
        Assert.assertTrue(dTUserProfileTypeAddPage.getNameFieldError().isDisplayed());
        Assert.assertTrue(dTUserProfileTypeAddPage.getCodeFieldError().isDisplayed());
        
        //Compilation of the page
        dTUserProfileTypeAddPage.setNameField(profileTypeName);
        dTUserProfileTypeAddPage.setCodeField(profileTypeCode);
        
        //Save and return
        dTUserProfileTypeAddPage.getSaveButton().click();
        
        //Loading next step
        Utils.waitUntilIsVisible(driver, dTUserProfileTypeAddPage.getAddButton());
        sleep(400);
        
        dTUserProfileTypeAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTUserProfileTypePage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserProfileTypePage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getTableBody());
        
        //Assert the presence of the created profile type in the Profile type table
        List<WebElement> createdUser = dTUserProfileTypePage.getTable()
                .findRowList(profileTypeName, rolesTableHeaderTitles.get(0));
        
        Assert.assertTrue(!createdUser.isEmpty());
        
        //Delete the Profile type created for the test
        Assert.assertTrue(deleteProfileType(dTUserProfileTypePage, profileTypeName));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/        
    }
        
}
