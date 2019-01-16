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
package org.entando.selenium.smoketests;

import org.apache.commons.io.FileUtils;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserAddPage;
import org.entando.selenium.pages.DTUserManageAuthorityPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * This class perform a test to add a user
 *
 * @version 1.01
 */
public class STAddTestUserTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;

    @Autowired
    public DTUsersPage dTUsersPage;

    @Autowired
    public DTUserAddPage dTUserAddPage;

    @Autowired
    public STLoginPage sTLoginPage;

    @Autowired
    public DTUserManageAuthorityPage dTUserManageAuthorityPage;

    protected void login() {
        try {
            driver.manage().window().maximize();
            sTLoginPage.logIn("admin", "adminadmin");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        login();
        String username = "mytestuser";
        try {
            //Navigation to the page
            dTDashboardPage.SelectSecondOrderLinkWithSleep("User Management", "Users");
            Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
            Kebab kebab = dTUsersPage.getTable().getKebabOnTable(username, usersTableHeaderTitles.get(0), usersTableHeaderTitles.get(4));
            //Click on kebab menù
            kebab.getClickable().click();
            Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
            //Click on the action
            kebab.getAction("Delete").click();
            Utils.waitUntilIsVisible(driver, dTUsersPage.getDeleteModalButton());
            Assert.assertTrue(dTUsersPage.getModalBody().getText().contains(username));
            Utils.waitUntilIsClickable(driver, dTUsersPage.getDeleteModalButton());
            sleep(500);
            dTUsersPage.getDeleteModalButton().click();
            Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
            //Wait loading page
            //Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
            //Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);

            sleep(2000);
            Utils.waitUntilIsVisible(driver, dTUsersPage.getUsersTable());
        } catch (Throwable e) {
            Logger.getGlobal().info("No provious test user deleted: " + e);
        }

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep("User Management", "Users");
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());

        dTUsersPage.getAddButton().click();

        Utils.waitUntilIsVisible(driver, dTUserAddPage.getPageTitle());

        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Add", dTUserAddPage.getPageTitle().getText());


        //Compilation of the page
        dTUserAddPage.setUsernameField(username);
        dTUserAddPage.setPasswordField("adminadmin");
        dTUserAddPage.setPasswordConfirmField("adminadmin");
        dTUserAddPage.getProfileTypeSelect().selectByVisibleText("Default user profile");
        dTUserAddPage.getStatusSwitch().setOn();


        //Save and come back to the Users list
        Assert.assertTrue(dTUserAddPage.getSaveButton().isEnabled());
        dTUserAddPage.getSaveButton().click();


        sleep(2000);

        Utils.waitUntilIsVisible(driver, dTUsersPage.getUsersTable());


        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUsersPage.getTable().findRowList(username, UsersTestBase.usersTableHeaderTitles.get(0));
        Assert.assertFalse(createdUser.isEmpty());

        //Verify "Status" is "Active"
        WebElement cell = dTUsersPage.getTable().getCell(username, UsersTestBase.usersTableHeaderTitles.get(0), super.usersTableHeaderTitles.get(3));
        Assert.assertEquals(" Active", cell.getText());

        Kebab kebab = dTUsersPage.getTable().getKebabOnTable(username, usersTableHeaderTitles.get(0), usersTableHeaderTitles.get(4));
        Assert.assertFalse("User not found!", kebab == null);

        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Manage authorization for: " + username).click();


        Utils.waitUntilIsVisible(driver, dTUserManageAuthorityPage.getPageTitle());

        //Utils.waitUntilIsPresent(driver, dTUserManageAuthorityPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserManageAuthorityPage.spinnerTag);

        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Authorizations for " + username, dTUserManageAuthorityPage.getPageTitle().getText());

        //Asserts the presence of the labels
        assertTrue(dTUserManageAuthorityPage.getGroupLabel().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getRoleLabel().isDisplayed());

        //Asserts the presence of the buttons
        assertTrue(dTUserManageAuthorityPage.getAddButton().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getSaveButton().isDisplayed());

        SimpleTable table = dTUserManageAuthorityPage.getTable();

        //Add a authorization
        dTUserManageAuthorityPage.getUserGroup().selectByVisibleText("Administrators");
        dTUserManageAuthorityPage.getUserRole().selectByVisibleText("Administrator");
        dTUserManageAuthorityPage.getAddButton().click();

        table = dTUserManageAuthorityPage.getTable();
        //Asserts the presence of a authorization
        Assert.assertEquals(1, table.tableSize());

        dTUserManageAuthorityPage.getSaveButton().click();

        Utils.waitUntilIsVisible(driver, dTUsersPage.getPageTitle());

        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/

    }
}//end class
