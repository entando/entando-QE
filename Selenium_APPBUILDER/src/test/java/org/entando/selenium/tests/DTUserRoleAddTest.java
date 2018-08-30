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
 * This class perform a test of the Add User Role
 * 
 * @version 1.01
 */
public class DTUserRoleAddTest extends UsersTestBase {
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
        
        //Final page title
        String pageTitle = "Add";
                
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
        
        dTUserRolesPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserRoleAddPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserRoleAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserRoleAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserRoleAddPage.getTooltip());
        Assert.assertTrue(dTUserRoleAddPage.getTooltip().isDisplayed());
        
        //Verify "field required" warning
        dTUserRoleAddPage.getNameField().click();
        dTUserRoleAddPage.getCodeField().click();
        dTUserRoleAddPage.getNameField().click();
        Assert.assertTrue(dTUserRoleAddPage.getNameFieldError().isDisplayed());
        Assert.assertTrue(dTUserRoleAddPage.getCodeFieldError().isDisplayed());
        
        Assert.assertFalse(dTUserRoleAddPage.getSaveButton().isEnabled());
        
        //Verify switches
        dTUserRoleAddPage.getAllSwitches().forEach((switchButton) -> {
            Assert.assertFalse(switchButton.isOn());
        });
        
        //Compilation of the page
        dTUserRoleAddPage.setNameField(roleName);
        
        //Change Switch status
        dTUserRoleAddPage.getContentEditingSwitch().setOn();
        dTUserRoleAddPage.getAccessToAdminAreaSwitch().setOn();
        dTUserRoleAddPage.getOperationsOnPagesSwitch().setOn();
        dTUserRoleAddPage.getViewUsersAndProfilesSwitch().setOn();
        dTUserRoleAddPage.getUserProfileEditingSwitch().setOn();
        dTUserRoleAddPage.getCommentEditingSwitch().setOn();
        dTUserRoleAddPage.getGestioneWebDynamicFormsSwitch().setOn();
        
        Assert.assertTrue(dTUserRoleAddPage.getSaveButton().isEnabled());
        
        //Save and return
        dTUserRoleAddPage.getSaveButton().click();
        
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTUserRolesPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTUserRolesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserRolesPage.spinnerTag);        
        //Utils.waitUntilIsVisible(driver, dTUserRolesPage.getTableBody());
        //sleep(1000);
        
        //Assert the presence of the created role in the Roles table
        List<WebElement> createdUser = dTUserRolesPage.getTable()
                .findRowList(roleName, rolesTableHeaderTitles.get(0));
        
        Assert.assertTrue(!createdUser.isEmpty());
        
        //Delete the role created for the test
        Assert.assertTrue(deleteRole(dTUserRolesPage, roleName));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/        
    }
}
