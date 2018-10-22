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
import org.entando.selenium.pages.DTUserGroupAddPage;
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import static org.entando.selenium.testHelpers.UsersTestBase.groupsTableHeaderTitles;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Group Delete page
 * 
 * @version 1.01
 */
public class DTUserGroupDeleteTest extends UsersTestBase{
    
    @Autowired
    DTDashboardPage dTDashboardPage;
    
    @Autowired
    DTUserGroupsPage dTUserGroupsPage;
    
    @Autowired
    DTUserGroupAddPage dTUserGroupAddPage;
     
    @Test
    public void test() throws InterruptedException{
        /*
            Parameters
         */ 
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Groups";
       
        //Kebab action
        String kebabAction = "Delete";
        
        //Group name to set
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String groupName = "1SLNM_TEST_" + randomNumber;

        
        /*
            Actions and asserts
         */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getAddButton());
        
        //Create a group
        Assert.assertTrue(addGroup(dTUserGroupsPage, dTUserGroupAddPage, groupName));
        
        Kebab kebab = dTUserGroupsPage.getTable().getKebabOnTable(groupName, 
                groupsTableHeaderTitles.get(0), groupsTableHeaderTitles.get(2));
        Assert.assertFalse(kebab == null);
                
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTUserGroupsPage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", groupName));
        Assert.assertTrue(dTUserGroupsPage.getModalBody().getText().contains(groupName.toLowerCase()));
        Utils.waitUntilIsClickable(driver, dTUserGroupsPage.getDeleteModalButton());
        sleep(100);
        dTUserGroupsPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
        
        //Wait loading table
        sleep(500);
        
        //Assert the absence of the created user group in the User Group table
        List<WebElement> createdUser = dTUserGroupsPage.getTable()
                .findRowList(groupName, groupsTableHeaderTitles.get(0));
        
        Assert.assertTrue(createdUser.isEmpty());
                
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
       
    }
}
