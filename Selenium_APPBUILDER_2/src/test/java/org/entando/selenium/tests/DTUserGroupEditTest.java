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
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.pages.DTUserGroupAddPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import org.entando.selenium.utils.Parallelized;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;


/**
 * This class perform a test of the Group Edit page
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTUserGroupEditTest extends UsersTestBase{
    
    public DTUserGroupEditTest(String browserName, String platformName) {
        super(browserName, platformName);
    }

    /*
        Test
     */
    @Test
    public void test() throws InterruptedException{
        /*
            Pages used on this test
         */    
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTUserGroupsPage dTUserGroupsPage = new DTUserGroupsPage(driver);
        DTUserGroupAddPage dTUserGroupAddPage = new DTUserGroupAddPage(driver);

        /*
            Parameters
         */ 
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Groups";
        
        //Final page title
        String pageTitle = "Edit";
        
        //Kebab action
        String kebabAction = "Edit";
        
        //Field label names
        String nameLabel = "Name";
        String codeLabel = "Code";
        
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
        
        Utils.waitUntilIsVisible(driver, dTUserGroupAddPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserGroupAddPage.getPageTitle().getText());
        
        sleep(500);
        dTUserGroupAddPage.getName().clear();
        
        //Asserts the presence of the HELP button
        dTUserGroupAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserGroupAddPage.getTooltip());
        Assert.assertTrue(dTUserGroupAddPage.getTooltip().isDisplayed());
        
        //Asserts field label names are corrected
        Assert.assertEquals(nameLabel, dTUserGroupAddPage.getNameLabel().getText());
        Assert.assertEquals(codeLabel, dTUserGroupAddPage.getCodeLabel().getText());
        
        //Asserts fields required warning
        Assert.assertTrue(dTUserGroupAddPage.getNameRequiredAsterisk().isDisplayed());
        Assert.assertTrue(dTUserGroupAddPage.getCodeRequiredAsterisk().isDisplayed());
        Assert.assertTrue(dTUserGroupAddPage.getNameFieldError().isDisplayed());
        
        //Asserts info functionality
        dTUserGroupAddPage.getInfoNameButton().click();
        sleep(200);
        Assert.assertTrue(dTUserGroupAddPage.getNameTooltip().isDisplayed());
        dTUserGroupAddPage.getInfoCodeButton().click();
        sleep(200);
        Assert.assertTrue(dTUserGroupAddPage.getCodeTooltip().isDisplayed());
        
        //Asserts submit button is disabled
        Assert.assertFalse(dTUserGroupAddPage.getSaveButton().isEnabled());
                
        //Filling in the fields
        dTUserGroupAddPage.setName(groupName);
        Assert.assertFalse(dTUserGroupAddPage.getCode().isEnabled());
        
        //Asserts submit button is enabled
        Assert.assertTrue(dTUserGroupAddPage.getSaveButton().isEnabled());
        
        //Save and return
        dTUserGroupAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTUserGroupsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserGroupsPage.spinnerTag);
        
        //Assert the presence of the created user group in the User Group table
        List<WebElement> createdUser = dTUserGroupsPage.getTable()
                .findRowList(groupName, groupsTableHeaderTitles.get(0));
        
        Assert.assertTrue(!createdUser.isEmpty());
        
        //Delete the user group created for the test
        Assert.assertTrue(deleteGroup(dTUserGroupsPage, groupName));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/  
        
    }
    
}//end class
