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
package org.entando.selenium.utils;

import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.testHelpers.*;
import org.entando.selenium.pages.*;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * This class perform a check of the AppBuilder Environment to execute tests correctly
 * 
 * !!! REMEMBER: Before run this class, set GRID_EXECUTION == false in FunctionalTestBase.java !!!
 * 
 * 
 * 
 * 
 * Details:
 * 
 * 
 * 
 * 
 * • User Group ->
	Name: 1SeleniumTest_DontTouch
	Code: 1seleniumtest_dontto

• Profile Type -> 
	Name: 1SeleniumTest_DontTouch
	Code: SLN

• User Role -> 
	Name: 1SeleniumTest_DontTouch
	Code: 1seleniumtest_role
	
• Languages ->
	Only English and Italian
	
• Users ->
	Name: 1SLNM_DONT_TOUCH
	Profile Type: 1SeleniumTest_DontTouch
	
	Manage Authorization -> Role: 1SeleniumTest_DontTouch
                                Group: 1SeleniumTest_DontTouch
	
• Page Model ->
	Name: 1SLNM_TEST_DONT_TOUCH
	Code: 1SLNM_TEST_DONT_TOUCH
	JSon Configuration: {
                                "frames": [
                                       {
                                              "pos": 0,
                                              "descr": "Test frame",
                                              "mainFrame": false,
                                              "defaultWidget": null,
                                              "sketch": null
                                       }
                                ]
                              }
	Template: <>

• Data Type
	Name: SeleniumTest_DontTouch
	Code: 11S

• Data Type
	Name: SeleniumTest_DontTouch1
	Code: 12S
	
• Data Type
	Name: SeleniumTest_DontTouch2
	Code: 13S

• Widget
	Name: SeleniumTest_DontTouch

• Page (in Page Tree) ->
	Code: SeleniumTest_DontTouch
	Title: SeleniumTest_DontTouch
	Placement: Home
	Owner Group: 1SeleniumTest_DontTouch
	Page Model: 1SLNM_TEST_DONT_TOUCH
	
• Categories
	Name: SeleniumTest_DontTouch
	Code: seleniumtest_donttouch
 
 * 
 * 
 * 
 * 
 * 
 * 
 * @version 1.03
 */

public class EnvironmentChecker extends FunctionalTestBase{
    String userGroupName = "1SeleniumTest_DontTouch";
    String profileTypeName = "1SeleniumTest_DontTouch";
    String roleName = "1SeleniumTest_DontTouch";
    String roleCode = "1seleniumtest_role";
        
    /*
        Test checker
     */
    @Test
    public void runTest() throws InterruptedException{
        
        //Login
        login();
        
        checkLanguages();
        checkUserGroup();
        checkProfileType();
        checkUserRole();
        checkUser();
        checkPageModel();
       checkDataType();
        
        checkPage();
        checkCategories();
        checkWidget();
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
    
    
    private void checkCategories() throws InterruptedException
    {
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTCategoriesPage dTCategoriesPage = new DTCategoriesPage(driver);
        DTCategoriesAddPage dTCategoriesAddPage = new DTCategoriesAddPage(driver);
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("Configuration", "Categories");
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
        //Assert the presence of the page
        List<WebElement> categories = dTCategoriesPage.getTable().
                findRowList("SeleniumTest_DontTouch", 
                        CategoriesTestBase.expectedHeaderTitles.get(0));
        if(categories.isEmpty())
        {
            //Create a category         
            Assert.assertTrue(addCategory(dTCategoriesPage, dTCategoriesAddPage,
                    "SeleniumTest_DontTouch", "root"));
        }
        sleep(300);        
    }
    
    
        
    private void checkPage() throws InterruptedException
    {
        /*
         * Set Page
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTPageTreePage dTPageTreePage = new DTPageTreePage(driver);
        DTPageAddPage dTPageAddPage = new DTPageAddPage(driver);
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("Page Designer", "Page Tree");
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getAddButton());
        sleep(300);
        
        //Assert the presence of the page
        List<WebElement> pages = dTPageTreePage.getTable().
                findRowList("SeleniumTest_DontTouch", 
                        PageTreeTestBase.headerTitles.get(0));
        if(pages.isEmpty())
        {
            //Create a page model            
            Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage,
                    "SeleniumTest_DontTouch", "Home"));
        }
        sleep(300);
    }
    
    
    
    private void checkWidget() throws InterruptedException
    {
        /*
         * Set Widget
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTWidgetPage dTWidgetPage = new DTWidgetPage(driver);
        DTWidgetAddPage dTWidgetAddPage = new DTWidgetAddPage(driver);
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("UX Patterns", "Widgets");
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTWidgetPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTWidgetPage.spinnerTag);
    
        try {
         SimpleTable table = new SimpleTable(dTWidgetPage.getTables().get("user"));
            List<WebElement> widgets = table.findRowList("  SeleniumTest_DontTouch", 
                        WidgetsTestBase.expectedHeaderTitles.get(0));
        }
        catch(NullPointerException e)
{
    sleep(300);
    Assert.assertTrue(addWidget(dTWidgetPage, dTWidgetAddPage,"SeleniumTest_DontTouch"));
    sleep(300);
} 
        SimpleTable table = new SimpleTable(dTWidgetPage.getTables().get("user"));
        List<WebElement> widgets = table.findRowList("  SeleniumTest_DontTouch", 
                        WidgetsTestBase.expectedHeaderTitles.get(0));
        
           if(widgets.isEmpty())
        {
            //Create a page model            
            Assert.assertTrue(addWidget(dTWidgetPage, dTWidgetAddPage,
                    "SeleniumTest_DontTouch"));
        }
        
        
        
    }
    
    
    
    private void checkDataType() throws InterruptedException
    {
        /*
         * Set Data Type
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTDataTypesPage dTDataTypesPage = new DTDataTypesPage(driver);
        DTDataTypesAddPage dTDataTypesAddPage = new DTDataTypesAddPage(driver);
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("Data", "Data Types");
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTDataTypesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTDataTypesPage.spinnerTag);
        //Utils.waitUntilIsVisible(driver, dTDataTypesPage.getTableBody());
        
        Boolean isElementPresent = driver.findElements(By.cssSelector("table > tbody")).size()!= 0;
        
        if (isElementPresent == true){
            List<WebElement> dataTypes = dTDataTypesPage.getTable().
                findRowList("SeleniumTest_DontTouch", 
                        DataTypesTestBase.expectedHeaderTitles.get(0));
        if(dataTypes.isEmpty())
        {
            //Create a Data Type           
            Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage,
                    "SeleniumTest_DontTouch", "11S"));
        }
        sleep(300);
        dataTypes = dTDataTypesPage.getTable().
                findRowList("SeleniumTest_DontTouch1", 
                        DataTypesTestBase.expectedHeaderTitles.get(0));
        if(dataTypes.isEmpty())
        {
            //Create a Data Type            
            Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage,
                    "SeleniumTest_DontTouch1", "12S"));
        }
        sleep(300);
        dataTypes = dTDataTypesPage.getTable().
                findRowList("SeleniumTest_DontTouch2", 
                        DataTypesTestBase.expectedHeaderTitles.get(0));
        if(dataTypes.isEmpty())
        {
            //Create a page model            
            Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage,
                    "SeleniumTest_DontTouch2", "13S"));
        }
        sleep(300);
            
        } else {
            
            //Create a Data Type           
            Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage,
                    "SeleniumTest_DontTouch", "11S"));
            
            Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage,
                    "SeleniumTest_DontTouch1", "12S"));
            
            Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage,
                    "SeleniumTest_DontTouch2", "13S"));
            
        }
    
    }
    
    
    
    private void checkPageModel() throws InterruptedException
    {
        /*
         * Set Page Model
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTPageModelsPage dTPageModelsPage = new DTPageModelsPage(driver);
        DTPageModelsAddPage dTPageModelsAddPage = new DTPageModelsAddPage(driver);
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("UX Patterns", "Page Models");
        //Wait loading results
        //Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        //Assert the presence of the page model
        List<WebElement> pageModels = dTPageModelsPage.getTable().
                findRowList("1SLNM_TEST_DONT_TOUCH", PageModelsTestBase.expectedHeaderTitles.get(0));
        
        if(pageModels.isEmpty())
        {
            //Create a page model            
            Assert.assertTrue(addPageModel(dTPageModelsPage, dTPageModelsAddPage,
                    "1SLNM_TEST_DONT_TOUCH"));
        }
        sleep(300);
    }
    
    
    
    private void checkUser() throws InterruptedException
    {
        /*
         * Set User
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTUsersPage dTUsersPage = new DTUsersPage(driver);
        DTUserAddPage dTUserAddPage = new DTUserAddPage(driver);
        String username = "1SLNM_DONT_TOUCH";
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("User Management", "Users");
        //Wait loading results
        //Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
        
        //Assert the presence of the user in the Users table
        
        Utils.waitUntilIsVisible(driver, dTUsersPage.getUsersTable());
        
        List<WebElement> users = dTUsersPage.getTable().
                findRowList(username, UsersTestBase.usersTableHeaderTitles.get(0));
        
        if(users.isEmpty())
        {
            //Create a user
            addUser(dTUsersPage, dTUserAddPage, username);
            
            
            Kebab kebab = dTUsersPage.getTable().getKebabOnTable(username, 
                    UsersTestBase.usersTableHeaderTitles.get(0), UsersTestBase.usersTableHeaderTitles.get(4));
            Assert.assertFalse("User not found!", kebab == null);
            
            //Click on kebab menù
            kebab.getClickable().click();
            //Debug code  Logger.getGlobal().info("Kebab clicked");
            Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
            //Click on the action
            String kebabAction = "Manage authorization for: " + username;
            kebab.getAction(kebabAction).click();
            
            DTUserManageAuthorityPage dTUserManageAuthorityPage = new DTUserManageAuthorityPage(driver);
            Utils.waitUntilIsVisible(driver, dTUserManageAuthorityPage.getPageTitle());
            //Utils.waitUntilIsPresent(driver, dTUserManageAuthorityPage.spinnerTag);
            //Utils.waitUntilIsDisappears(driver, dTUserManageAuthorityPage.spinnerTag);
             
            
            //Add a authorization
            dTUserManageAuthorityPage.getUserGroup().selectByVisibleText(profileTypeName);
            dTUserManageAuthorityPage.getUserRole().selectByVisibleText(roleName);
            dTUserManageAuthorityPage.getAddButton().click();
            dTUserManageAuthorityPage.getSaveButton().click(); 
            //Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
            //Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
            
        } 
        sleep(300);
    }
    
    
    
    private void checkUserRole() throws InterruptedException
    {
        /*
         * Set User Role
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTUserRolesPage dTUserRolesPage = new DTUserRolesPage(driver);
        DTUserRoleAddPage dTUserRoleAddPage = new DTUserRoleAddPage(driver);
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("User Management", "Roles");
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTUserRolesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserRolesPage.spinnerTag);        
        
        //Assert the presence of the user role
        List<WebElement> roles = dTUserRolesPage.getTable().
                findRowList(roleName, UsersTestBase.rolesTableHeaderTitles.get(0));
        
        if(roles.isEmpty())
        {
            //Create a role
            Assert.assertTrue(addRole(dTUserRolesPage, dTUserRoleAddPage,
                    roleName, roleCode));
        }
        sleep(300);
    }
    
    
    
    private void checkProfileType() throws InterruptedException
    {
        /*
         * Set Profile Type
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTUserProfileTypePage dTUserProfileTypePage = new DTUserProfileTypePage(driver);
        DTUserProfileTypeAddPage dTUserProfileTypeAddPage = 
                new DTUserProfileTypeAddPage(driver);
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("User Management", "Profile types");
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTUserProfileTypePage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserProfileTypePage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getTableBody());
        
        //Assert the absence of the Profile Type
        List<WebElement> createdProfileType = dTUserProfileTypePage.getTable()
                .findRowList(profileTypeName, UsersTestBase.groupsTableHeaderTitles.get(0));
        if(createdProfileType.isEmpty())
        {
            //Create a group
            Assert.assertTrue(addProfileType(dTUserProfileTypePage,
                    dTUserProfileTypeAddPage, profileTypeName, "SLN"));
        }
        sleep(300);
    }
    
    
    
    private void checkUserGroup() throws InterruptedException
    {
        /*
         * Set User Group
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTUserGroupsPage dTUserGroupsPage = new DTUserGroupsPage(driver);
        DTUserGroupAddPage dTUserGroupAddPage = new DTUserGroupAddPage(driver);
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink("User Management", "Groups");
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getPageTitle());
        //Utils.waitUntilIsPresent(driver, dTUserGroupsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserGroupsPage.spinnerTag);
        
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getGroupsTable());
        //Assert the absence of the user group in the User Group table
        List<WebElement> createdUserGroup = dTUserGroupsPage.getTable()
                .findRowList(userGroupName, UsersTestBase.groupsTableHeaderTitles.get(0));
        if(createdUserGroup.isEmpty())
        {
            //Create a group
            Assert.assertTrue(addGroup(dTUserGroupsPage,
                    dTUserGroupAddPage, userGroupName));
        }
        sleep(300);
    }
    
    
    
    private void checkLanguages() throws InterruptedException
    {
        /*
         * Set Languages
         */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTLabelsAndLanguagesPage dTLabelsAndLanguagesPage = new DTLabelsAndLanguagesPage(driver);
        
        //Navigation to the page
  
        dTDashboardPage.SelectSecondOrderLink("Configuration", "Labels and Languages");
        
        //Loading page
        //Utils.waitUntilIsPresent(driver, dTLabelsAndLanguagesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTLabelsAndLanguagesPage.spinnerTag);
        
        List<String> expectedHeaderTitles = 
            Arrays.asList("Code", "Name", "Actions");
        
        //Verify the presence of the languages in the table
        SimpleTable table = dTLabelsAndLanguagesPage.getSimpleTable();
        
        WebElement itButton = table.getButtonOnTable(
                "Italian",
                expectedHeaderTitles.get(1), expectedHeaderTitles.get(2));
        
        if (!((table.tableSize() == 2)
            &&(itButton != null)))
        {
            if (!(table.tableSize() == 1))
            {
                //Delete all languages
                List<WebElement> allTrashButtons =
                        table.getAllButtonsOnColumn(expectedHeaderTitles.get(2));

                for(WebElement trashButton : allTrashButtons)
                {
                    trashButton.click();
                    Utils.waitUntilIsVisible(driver, dTLabelsAndLanguagesPage.getDeleteModalButton());
                    Utils.waitUntilIsClickable(driver, dTLabelsAndLanguagesPage.getDeleteModalButton());
                    sleep(100);
                    dTLabelsAndLanguagesPage.getDeleteModalButton().click();
                    Utils.waitUntilIsDisappears(driver, DTLabelsAndLanguagesPage.modalWindowTag);
                    sleep(400);
                }
            }
            
            driver.get(driver.getCurrentUrl());
            //Loading page
            //Utils.waitUntilIsPresent(driver, dTLabelsAndLanguagesPage.spinnerTag);
            //Utils.waitUntilIsDisappears(driver, dTLabelsAndLanguagesPage.spinnerTag);
            
            dTLabelsAndLanguagesPage.getLanguageSelect().selectByVisibleText("it – Italian");
            dTLabelsAndLanguagesPage.getAddButton().click();
            sleep(600);
        }
    }
    
    
        
    /*
     * 
     * 
     * HELPERS METHODS
     * 
     * 
     */
    
    
    
    public boolean addPageModel(DTPageModelsPage dTPageModelsPage, 
            DTPageModelsAddPage dTPageModelsAddPage, String code) throws InterruptedException{
        //The code to insert in the fields
        String jsonConfiguration = "{\n" +
"  \"frames\": [\n" +
"    {\n" +
"      \"pos\": 0,\n" +
"      \"descr\": \"Test frame\",\n" +
"      \"mainFrame\": false,\n" +
"      \"defaultWidget\": null,\n" +
"      \"sketch\": null\n" +
"    }\n" +
"  ]\n" +
"}\n";
        String template = "<>";
    
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getAddButton());
        
        //Click on New Button
        dTPageModelsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTPageModelsAddPage.getSaveButton());
        
        //Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        //Compilation of the fields
        
        dTPageModelsAddPage.clearJsonConfigurationFieldNew();
    
        
        dTPageModelsAddPage.setCodeField(code);
        dTPageModelsAddPage.setNameField(code);
        
       
        dTPageModelsAddPage.setJsonConfigurationField(jsonConfiguration);
        dTPageModelsAddPage.setTemplateField(template);
        
        //Save the data
        sleep(3000);
        
        dTPageModelsAddPage.getSaveButton().click();
        
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getMessage());
        dTPageModelsPage.getCloseMessageButton().click();
                
        //Reload the page
        driver.get(driver.getCurrentUrl());
        //Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        
        //Assert the element is present in the table
        List<WebElement> createdPageModels = dTPageModelsPage.getTable()
                .findRowList(code, PageModelsTestBase.expectedHeaderTitles.get(0));
        
        return (!createdPageModels.isEmpty());
    }
    
    
    
    public boolean addUser(DTUsersPage dTUsersPage, DTUserAddPage dTUserAddPage, 
            String username)
    {        
        dTUsersPage.getAddButton().click();
        Utils.waitUntilIsVisible(driver, dTUserAddPage.getPageTitle());
        //Compilation of the page
        dTUserAddPage.setUsernameField(username);
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        double minPasswordLength = 8;
        String password = Double.toString(Math.pow(10, minPasswordLength-1) + randomNumber);
        dTUserAddPage.setPasswordField(password);
        dTUserAddPage.setPasswordConfirmField(password);
        Boolean status = true;
        dTUserAddPage.getProfileTypeSelect().selectByVisibleText(profileTypeName);
        if(status){
            dTUserAddPage.getStatusSwitch().setOn();
        }else{
            dTUserAddPage.getStatusSwitch().setOff();
        }
        
        //Save and come back to the Users list
        Assert.assertTrue(dTUserAddPage.getSaveButton().isEnabled());
        dTUserAddPage.getSaveButton().click();
        
        
        
        
        
        try {
            
            sleep(2000);
            Utils.waitUntilIsVisible(driver, dTUsersPage.getUsersTable());
        } catch (InterruptedException ex) {
            Logger.getLogger(EnvironmentChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        List<WebElement> createdUser = dTUsersPage.getTable().findRowList(username, 
                UsersTestBase.usersTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
        
    }
    
    
    
    public boolean addRole(DTUserRolesPage dTUserRolesPage, 
            DTUserRoleAddPage dTUserRoleAddPage, String roleName, String roleCode){
        dTUserRolesPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserRoleAddPage.getPageTitle());
        
        //Compilation of the page
        dTUserRoleAddPage.setNameField(roleName);
        dTUserRoleAddPage.setCodeField(roleCode);
        
        //Change Switch status
        dTUserRoleAddPage.getContentEditingSwitch().setOn();
        dTUserRoleAddPage.getAccessToAdminAreaSwitch().setOn();
        dTUserRoleAddPage.getOperationsOnPagesSwitch().setOn();
        dTUserRoleAddPage.getViewUsersAndProfilesSwitch().setOn();
        dTUserRoleAddPage.getUserProfileEditingSwitch().setOn();
        dTUserRoleAddPage.getCommentEditingSwitch().setOn();
        dTUserRoleAddPage.getGestioneWebDynamicFormsSwitch().setOn();
        
        //Save and return
        dTUserRoleAddPage.getSaveButton().click();
        
        try {
            sleep(2000);
            Utils.waitUntilIsVisible(driver, dTUserRolesPage.getRolesTable() );
        } catch (InterruptedException ex) {
            Logger.getLogger(EnvironmentChecker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTUserRolesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserRolesPage.spinnerTag);        
        
        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUserRolesPage.getTable().
                findRowList(roleName, UsersTestBase.rolesTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
    }
    
    
    
    public boolean addProfileType(DTUserProfileTypePage dTUserProfileTypePage,
            DTUserProfileTypeAddPage dTUserProfileTypeAddPage, String profileTypeName,
            String profileTypeCode) throws InterruptedException
    {
        dTUserProfileTypePage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserProfileTypeAddPage.getPageTitle());
        
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
        //Utils.waitUntilIsPresent(driver, dTUserProfileTypePage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserProfileTypePage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getTableBody());
        
        //Assert the presence of the created profile type in the Profile type table
        List<WebElement> createdUser = dTUserProfileTypePage.getTable()
                .findRowList(profileTypeName, UsersTestBase.rolesTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
    }
    
    
    
    public boolean addGroup(DTUserGroupsPage dTUserGroupsPage, 
            DTUserGroupAddPage dTUserGroupAddPage, String groupName){
        dTUserGroupsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserGroupAddPage.getPageTitle());
        
        //Compilation of the page
        dTUserGroupAddPage.setName(groupName);
        dTUserGroupAddPage.setCode(groupName.substring(0,20).toLowerCase());
        
        //Save and return
        dTUserGroupAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getPageTitle());
        
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTUserGroupsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTUserGroupsPage.spinnerTag);        
        
        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUserGroupsPage.getTable().
                findRowList(groupName, UsersTestBase.rolesTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
    }
    
    
    
    public boolean addDataType(DTDataTypesPage dTDataTypesPage,
            DTDataTypesAddPage dTDataTypesAddPage, String dataTypeName,
            String dataTypeCode ) throws InterruptedException{
        //Click on New Button
        dTDataTypesPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getSaveButton());
        dTDataTypesAddPage.setCode(dataTypeCode);
        dTDataTypesAddPage.setName(dataTypeName);
        
        //Save the data
        dTDataTypesAddPage.getSaveButton().click();
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getTypeSelect());
        sleep(500);
        dTDataTypesAddPage.getSaveButton().click();
        
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTDataTypesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTDataTypesPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getTableBody());
        
        List<WebElement> createdDataType = dTDataTypesPage.getTable()
                .findRowList(dataTypeCode, 
                        DataTypesTestBase.expectedHeaderTitles.get(1));
        
        return (!createdDataType.isEmpty());
    }
    
    
    
    public boolean addWidget(DTWidgetPage dTWidgetPage,
            DTWidgetAddPage dTWidgetAddPage, String code){
        //Click on New Button
        dTWidgetPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTWidgetAddPage.getPageTitle());
        //Compilation of the fields
        dTWidgetAddPage.setCodeField(code);
        dTWidgetAddPage.setEnTitleField(code);
        dTWidgetAddPage.setItTitleField(code);
        dTWidgetAddPage.getGroupSelect().selectByVisibleText(userGroupName);
        dTWidgetAddPage.setCustomUI("<>");
        
        //Save the data
        dTWidgetAddPage.getSaveButton().click();
                
        //Wait loading page
        //Utils.waitUntilIsVisible(driver, dTWidgetPage.getPageTitle());
        //Utils.waitUntilIsPresent(driver, dTWidgetPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTWidgetPage.spinnerTag);
        
        dTWidgetPage.getCloseAlertMessageButton().click();
        
        return true;
    }
    
    
    
    public boolean addPage(DTPageTreePage dTPageTreePage, DTPageAddPage dTPageTreeAddPage,
            String pageName, String branchName) throws InterruptedException
    {
        sleep(500);
        dTPageTreePage.getAddButton().click();
        /** Debug code **/ Logger.getGlobal().info("Add page clicked");
        Utils.waitUntilIsVisible(driver, dTPageTreeAddPage.getTableElement());
        /** Debug code **/ Logger.getGlobal().info("Table founded");
        driver.get(driver.getCurrentUrl());
        /** Debug code **/ Logger.getGlobal().info("Reload page");
        Utils.waitUntilIsVisible(driver, dTPageTreeAddPage.getTableElement());
        /** Debug code **/ Logger.getGlobal().info("Table already founded");
        
        //Compiling the fields
        dTPageTreeAddPage.setEnTitleField(pageName);
        dTPageTreeAddPage.setItTitleField(pageName);
        //dTPageTreeAddPage.setEsTitleField(pageName);
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        dTPageTreeAddPage.setCodeField("SeleniumTest_DontTouch");
        dTPageTreeAddPage.getOwnerGroup().selectByVisibleText(userGroupName);
        dTPageTreeAddPage.getPageModel().selectByVisibleText("1SLNM_TEST_DONT_TOUCH");
        dTPageTreeAddPage.addJoinGroup(userGroupName);
        
        //Select the branch
        ExpandableTable table = dTPageTreeAddPage.getTable();
        WebElement row = table.findRowList(branchName, 
                PageTreeTestBase.headerTitles.get(0)).get(0);
        row.click();
        
        sleep(100);
        
        //Return back
        Assert.assertTrue("Save Button is not enabled after compiling",
                 dTPageTreeAddPage.getSaveButton().isEnabled());
        dTPageTreeAddPage.getSaveButton().click();
        //sleep(2000);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getAddButton());
        //driver.get(driver.getCurrentUrl());
        //Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        List<WebElement> createdPage = dTPageTreePage.getTable().
                findRowList(pageName, PageTreeTestBase.headerTitles.get(0));
        if(createdPage.isEmpty())
            return false;
        else
            return true;
    }
    
    
    
    public boolean addCategory(DTCategoriesPage dTCategoriesPage, DTCategoriesAddPage dTCategoriesAddPage,
            String categoryName, String branchName){
        dTCategoriesPage.getAddButton().click();
        Utils.waitUntilIsVisible(driver, dTCategoriesAddPage.getTableWebElement());
        Utils.waitUntilIsVisible(driver, dTCategoriesAddPage.getEnNameField());
        
        //Compiling the fields
        dTCategoriesAddPage.setEnNameField(categoryName);
        dTCategoriesAddPage.setItNameField(categoryName);
        
        //Select the branch
        ExpandableTable table = dTCategoriesAddPage.getTable();
        WebElement row = table.findRowList(branchName, 
                CategoriesTestBase.expectedHeaderTitles.get(0)).get(0);
        row.click();
        
        //Return back
        Assert.assertTrue("Save Button is disabled", dTCategoriesAddPage.getSaveButton().isEnabled());
        dTCategoriesAddPage.getSaveButton().click();
       
        
        Utils.waitUntilIsVisible(driver, dTCategoriesPage.getAddButton());
       // Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
       List<WebElement> createdCategory = dTCategoriesPage.getTable()
                .findRowList(categoryName, 
                        CategoriesTestBase.expectedHeaderTitles.get(0));
        if(createdCategory.isEmpty())
            return false;
        else
            return true; 
    }
}


