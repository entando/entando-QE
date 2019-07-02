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

import org.entando.selenium.pages.DTUserAddPage;
import org.entando.selenium.pages.DTUserManageAuthorityPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * This class performs a test to add a user and add the Administrator role to it
 */
public class STAddTestUserTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public STDashboardPage dTDashboardPage;

    @Autowired
    public DTUsersPage dTUsersPage;

    @Autowired
    public DTUserAddPage dTUserAddPage;

    @Autowired
    public STAppBuilderLoginPage sTAppBuilderLoginPage;

    @Autowired
    public DTUserManageAuthorityPage dTUserManageAuthorityPage;

    protected void login() throws InterruptedException {
        driver.manage().window().maximize();
        sTAppBuilderLoginPage.logIn(SmokeTestUser.ADMIN);
        navigateToUserTable();
    }

    @Test
    public void runTest() throws InterruptedException {
        try {
            login();
            cleanupDanglingTestUser();
            addSmokeTestUser();
            addAdministratorAuthorization();
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
            printLog(LogType.BROWSER);
            printLog(LogType.CLIENT);
            throw e;
        }finally{
            ScreenPrintSaver.save(driver);
        }
    }

    private void printLog(String browser) {
        System.out.println("Printing log for " + browser);
        LogEntries logEntries = driver.manage().logs().get(browser);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

    private void addAdministratorAuthorization() {
        navigateToUserTable();
        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUsersPage.getTable().findRowList(SmokeTestUser.SMOKE_TEST_USER.getUsername(), UsersTestBase.usersTableHeaderTitles.get(0));
        Assert.assertFalse(createdUser.isEmpty());

        //Verify "Status" is "Active"
        WebElement cell = dTUsersPage.getTable().getCell(SmokeTestUser.SMOKE_TEST_USER.getUsername(), UsersTestBase.usersTableHeaderTitles.get(0), super.usersTableHeaderTitles.get(3));
        Assert.assertEquals(" Active", cell.getText());

        Kebab kebab = dTUsersPage.getTable().getKebabOnTable(SmokeTestUser.SMOKE_TEST_USER.getUsername(), usersTableHeaderTitles.get(0), usersTableHeaderTitles.get(4));
        Assert.assertFalse("User not found!", kebab == null);

        //Click on kebab menù
        kebab.getClickable().click();
        WaitUntil.isVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Manage authorization for: " + SmokeTestUser.SMOKE_TEST_USER.getUsername()).click();


        WaitUntil.isVisible(driver, dTUserManageAuthorityPage.getPageTitle());

        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Authorizations for " + SmokeTestUser.SMOKE_TEST_USER.getUsername(), dTUserManageAuthorityPage.getPageTitle().getText());

        //Asserts the presence of the labels
        assertTrue(dTUserManageAuthorityPage.getGroupLabel().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getRoleLabel().isDisplayed());

        //Asserts the presence of the buttons
        assertTrue(dTUserManageAuthorityPage.getAddButton().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getSaveButton().isDisplayed());


        //Add a authorization
        dTUserManageAuthorityPage.getUserGroup().selectByVisibleText("Administrators");
        dTUserManageAuthorityPage.getUserRole().selectByVisibleText("Administrator");
        dTUserManageAuthorityPage.getAddButton().click();

        //Asserts the presence of a authorization
        Assert.assertEquals(1, dTUserManageAuthorityPage.getTable().tableSize());

        dTUserManageAuthorityPage.getSaveButton().click();
        waitForUsersTableOnUsersPage();

    }

    private void addSmokeTestUser() throws InterruptedException {
        navigateToUserTable();
        WaitUntil.isVisible(driver, dTUsersPage.getAddButton());

        dTUsersPage.getAddButton().click();

        WaitUntil.isVisible(driver, dTUserAddPage.getPageTitle());

        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Add", dTUserAddPage.getPageTitle().getText());


        //Compilation of the page
        dTUserAddPage.setUsernameField(SmokeTestUser.SMOKE_TEST_USER.getUsername());
        dTUserAddPage.setPasswordField(SmokeTestUser.SMOKE_TEST_USER.getPassword());
        dTUserAddPage.setPasswordConfirmField(SmokeTestUser.SMOKE_TEST_USER.getPassword());
        WaitUntil.isVisible(driver, dTUserAddPage.getProfileTypeSelect().getWrappedElement());
        selectDefaultProfileTypeWhenAvailable();
        dTUserAddPage.getStatusSwitch().setOn();


        //Save and come back to the Users list
        Assert.assertTrue(dTUserAddPage.getSaveButton().isEnabled());
        dTUserAddPage.getSaveButton().click();
        waitForUsersTableOnUsersPage();
    }

    private void selectDefaultProfileTypeWhenAvailable() {
        new FluentWait<>(dTUserAddPage.getProfileTypeSelect())
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(300))
                .until(o -> {
                    o.selectByVisibleText("Default user profile");
                    return true;
                });
    }

    private void cleanupDanglingTestUser() throws InterruptedException {
        try {
            //Navigation to the page
            navigateToUserTable();
            WaitUntil.isVisible(driver, dTUsersPage.getAddButton());
            ScreenPrintSaver.save(driver);

            Kebab kebab = dTUsersPage.getTable().getKebabOnTable(SmokeTestUser.SMOKE_TEST_USER.getUsername(), usersTableHeaderTitles.get(0), usersTableHeaderTitles.get(4));
            //Click on kebab menù
            kebab.getClickable().click();
            WaitUntil.isVisible(driver, kebab.getAllActionsMenu());
            //Click on the action
            kebab.getAction("Delete").click();
            WaitUntil.isVisible(driver, dTUsersPage.getDeleteModalButton());
            Assert.assertTrue(dTUsersPage.getModalBody().getText().contains(SmokeTestUser.SMOKE_TEST_USER.getUsername()));
            Utils.waitUntilIsClickable(driver, dTUsersPage.getDeleteModalButton());
            //This code will only be called during local development so we can afford the waits
//            sleep(500);
            dTUsersPage.getDeleteModalButton().click();
            Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
//            sleep(500);
            waitForUsersTableOnUsersPage();
        } catch (Throwable e) {
            Logger.getGlobal().info("No previous test user to delete: " + e);

        }
    }

    private void navigateToUserTable() {
        dTDashboardPage.selectTab("Dashboard");
        WaitUntil.urlEndingWith(driver, "/dashboard");
        dTDashboardPage.selectSecondOrderLink("User Management", "Users");
        waitForUsersTableOnUsersPage();
    }

    private void waitForUsersTableOnUsersPage() {
        WaitUntil.urlEndingWith(driver, "/user");
        WaitUntil.isVisible(driver, dTUsersPage.getUsersTable());
    }

}//end class

