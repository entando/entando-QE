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
import org.entando.selenium.pages.DTUserRoleEditPage;
import org.entando.selenium.pages.DTUserRolesPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Edit User Role
 * 
 * @version 1.01
 */
public class DTUserRoleEditTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserRolesPage dTUserRolesPage;
    
    @Autowired
    public DTUserRoleEditPage dTUserRoleEditPage;
    
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
        String pageTitle = "Edit";
        
        //Kebab action
        String kebabAction = "Edit";
                
        //Role name to set
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String roleName = "1SLNM_TEST_" + randomNumber;
        String roleNameChanged = roleName + randomNumber;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUserRolesPage.getAddButton());
        
        //Create a role
        Assert.assertTrue(addRole(dTUserRolesPage, dTUserRoleAddPage, roleName));
        
        Kebab kebab = dTUserRolesPage.getTable().getKebabOnTable(roleName, rolesTableHeaderTitles.get(0), rolesTableHeaderTitles.get(2));
        Assert.assertFalse(kebab == null);
                
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        
        Utils.waitUntilIsVisible(driver, dTUserRoleEditPage.getUserEditingSwitchWebElement());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserRoleEditPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserRoleEditPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserRoleEditPage.getTooltip());
        Assert.assertTrue(dTUserRoleEditPage.getTooltip().isDisplayed());
        
        //Verify fields
        String realValue = dTUserRoleEditPage.getNameField().getAttribute("value");
        Assert.assertEquals(roleName, realValue);
        Assert.assertFalse(dTUserRoleEditPage.getCodeField().isEnabled());
        
        dTUserRoleEditPage.setNameField(roleNameChanged);
        
        
        //Verify active switches
        Assert.assertTrue(dTUserRoleEditPage.getContentEditingSwitch().isOn());
        Assert.assertTrue(dTUserRoleEditPage.getAccessToAdminAreaSwitch().isOn());
        Assert.assertTrue(dTUserRoleEditPage.getOperationsOnPagesSwitch().isOn());
        Assert.assertTrue(dTUserRoleEditPage.getViewUsersAndProfilesSwitch().isOn());
        Assert.assertTrue(dTUserRoleEditPage.getUserProfileEditingSwitch().isOn());
        Assert.assertTrue(dTUserRoleEditPage.getCommentEditingSwitch().isOn());
        Assert.assertTrue(dTUserRoleEditPage.getGestioneWebDynamicFormsSwitch().isOn());
        
        Assert.assertTrue(dTUserRoleEditPage.getSaveButton().isEnabled());
        
        //Save and return
        dTUserRoleEditPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTUserRolesPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTUserRolesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserRolesPage.spinnerTag);
        
        //Assert the presence of the edited role in the Users table
        List<WebElement> createdUser = dTUserRolesPage.getTable()
                .findRowList(roleNameChanged, rolesTableHeaderTitles.get(0));
        
        Assert.assertTrue(!createdUser.isEmpty());
        
        //delete the role
        Assert.assertTrue(deleteRole(dTUserRolesPage, roleNameChanged));
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/ 
    }    
}//end class
