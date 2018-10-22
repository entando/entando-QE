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
import java.text.MessageFormat;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserProfileTypeAddPage;
import org.entando.selenium.pages.DTUserProfileTypePage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

 /**
 * This class perform a test of the Delete Profile Type
 * 
 * @version 1.01
 */
public class DTUserProfileTypeDeleteTest extends UsersTestBase{
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
                
        //Kebab action
        String kebabAction = "Delete";
                
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
        
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTUserProfileTypePage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", profileTypeName));
        Assert.assertTrue(dTUserProfileTypePage.getModalBody().getText().contains(profileTypeCode));
        Utils.waitUntilIsClickable(driver, dTUserProfileTypePage.getDeleteModalButton());
        sleep(100);
        dTUserProfileTypePage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
        
        
        //Assert the element is not present in the table
        /*Debug code*/ Logger.getGlobal().log(Level.INFO, "Profile Type: {0}", profileTypeName);
        List<WebElement> foundedPages = dTUserProfileTypePage.getTable()
                .findRowList(profileTypeName, usersTableHeaderTitles.get(0));
        Assert.assertTrue(foundedPages.isEmpty());
        
                
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/ 
    }
}
