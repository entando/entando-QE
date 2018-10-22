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
import org.entando.selenium.pages.DTUserGroupAddPage;
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Group Add page
 * 
 * @version 1.01
 */
public class DTUserGroupAddTest extends UsersTestBase {
    /*
        Pages used on this test
     */    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserGroupsPage dTUserGroupsPage;
    
    @Autowired
    public DTUserGroupAddPage dTUserGroupAddPage;

    /*
        Test
     */
    @Test
    public void test() throws InterruptedException{
        /*
            Parameters
         */ 
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Groups";
        
        //Final page title
        String pageTitle = "Add";
        
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
        
        dTUserGroupsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserGroupAddPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserGroupAddPage.getPageTitle().getText());
        
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
        dTUserGroupAddPage.getName().click();
        dTUserGroupAddPage.getCode().click();
        dTUserGroupAddPage.getName().click();
        Assert.assertTrue(dTUserGroupAddPage.getNameFieldError().isDisplayed());
        Assert.assertTrue(dTUserGroupAddPage.getCodeFieldError().isDisplayed());
        
        //Asserts info xfields functionality
        dTUserGroupAddPage.getInfoNameButton().click();
        Assert.assertTrue(dTUserGroupAddPage.getNameTooltip().isDisplayed());
        dTUserGroupAddPage.getInfoCodeButton().click();
        Assert.assertTrue(dTUserGroupAddPage.getCodeTooltip().isDisplayed());
        
        //Asserts submit button is disabled
        Assert.assertFalse(dTUserGroupAddPage.getSaveButton().isEnabled());
                
        //Filling in the fields
        dTUserGroupAddPage.setName(groupName);
        dTUserGroupAddPage.setCode(groupName);
        
        //Asserts submit button is enabled
        Assert.assertTrue(dTUserGroupAddPage.getSaveButton().isEnabled());
        
        //Save and return
        dTUserGroupAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTUserGroupsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserGroupsPage.spinnerTag);
        
        //Assert the presence of the created role in the Group table
        List<WebElement> createdUser = dTUserGroupsPage.getTable()
                .findRowList(groupName, groupsTableHeaderTitles.get(0));
        
        Assert.assertTrue(!createdUser.isEmpty());
        
        //Delete the group created for the test
        Assert.assertTrue(deleteGroup(dTUserGroupsPage, groupName));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/   
        
    }
    
}
