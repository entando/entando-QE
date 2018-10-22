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
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTUserAddPage;
import org.entando.selenium.pages.DTUserGroupAddPage;
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.pages.DTUserProfileTypeAddPage;
import org.entando.selenium.pages.DTUserProfileTypePage;
import org.entando.selenium.pages.DTUserRoleAddPage;
import org.entando.selenium.pages.DTUserRolesPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * This class contains some utils methos for the Users tests (Helpers)
 * 
 * @version 1.01
 */
public class UsersTestBase extends BrowsableTableTestTypology{
    //Expected users table header titles
    public final List<String> usersTableHeaderTitles = Arrays.asList("Username", "Full Name", "Email", "Status", "Actions");
    
    //Expected roles table header titles
    public final List<String> rolesTableHeaderTitles = Arrays.asList("Name", "Code", "Actions");
    
    //Expected profile type table header titles
    public final List<String> profileTypeTableHeaderTitles = Arrays.asList("Name", "Code", "Status",  "Actions");
    
    //Expected groups table header titles
    public final List<String>groupsTableHeaderTitles = Arrays.asList("Name", "Code", "Actions");
    
    //Undefined option of Select
    public final String undefinedOption = "Choose an option";
        
    //Default profileType
    public final String profileType = "1SeleniumTest_DontTouch";
    
    //Default status
    public final Boolean status = true;
    
    //Default minimum password length
    public final double minPasswordLength = 8;
    
    //Default User Group
    public final String userGroup = "1SeleniumTest_DontTouch";
            
    //Default User Role
    public final String userRole = "1SeleniumTest_DontTouch";
    
    //Profile code to set
    public final String profileTypeCode = "QZX";
    
    
    /**
     * This is a helper method that creates a user to be used in these tests
     * @param dTUsersPage Users list page to start
     * @param dTUserAddPage Add User Page
     * @param username Username to set
     * @param password Password to set
     * @param profileType Profile Type
     * @param status Status On or Off
     * @return true if the user has been created successfully, else false 
     */
    public boolean addUser(DTUsersPage dTUsersPage, DTUserAddPage dTUserAddPage, String username, 
            String password, String profileType, Boolean status){
        
        dTUsersPage.getAddButton().click();
        Utils.waitUntilIsVisible(driver, dTUserAddPage.getPageTitle());
        //Compilation of the page
        dTUserAddPage.setUsernameField(username);
        dTUserAddPage.setPasswordField(password);
        dTUserAddPage.setPasswordConfirmField(password);
        dTUserAddPage.getProfileTypeSelect().selectByVisibleText(profileType);
        if(status){
            dTUserAddPage.getStatusSwitch().setOn();
        }else{
            dTUserAddPage.getStatusSwitch().setOff();
        }
        
        //Save and come back to the Users list
        Assert.assertTrue(dTUserAddPage.getSaveButton().isEnabled());
        dTUserAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);        
        
        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUsersPage.getTable().findRowList(username, usersTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
    }
    
    
    
    /**
     * This is a helper method that creates a standard user to be used in these tests
     * @param dTUsersPage Users list page to start
     * @param dTUserAddPage Add User Page
     * @param username Username to set
     * @return true if the user has been created successfully, else false 
     */
    public boolean addUser(DTUsersPage dTUsersPage, DTUserAddPage dTUserAddPage, String username){
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String password = Double.toString(Math.pow(10, minPasswordLength-1) + randomNumber);
        
        return this.addUser(dTUsersPage, dTUserAddPage, username, password, this.profileType, this.status);
    }
    
    
    
    /**
     * This is a helper method that delete a user to be used in these tests
     * @param dTUsersPage
     * @param username
     * @return true if the user has been deleted, else false 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteUser(DTUsersPage dTUsersPage, String username) throws InterruptedException{
        Kebab kebab = dTUsersPage.getTable().getKebabOnTable(username, usersTableHeaderTitles.get(0), usersTableHeaderTitles.get(4));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("User not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        Utils.waitUntilIsVisible(driver, dTUsersPage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTUsersPage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", username));
        Assert.assertTrue(dTUsersPage.getModalBody().getText().contains(username));
        Utils.waitUntilIsClickable(driver, dTUsersPage.getDeleteModalButton());
        sleep(100);
        dTUsersPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);
                
        Utils.waitUntilIsVisible(driver, dTUsersPage.getTableBody());
        /** Debug code **/ Logger.getGlobal().info("delete page return true");        
        return true;
    }
    
    
    
    /**
     * This is a helper method that creates a standard role to be used in these tests
     * @param dTUserRolesPage User roles list page to start
     * @param dTUserRoleAddPage Add User Role Page
     * @param roleName roleName to set
     * @return true if the user has been created successfully, else false 
     */
    public boolean addRole(DTUserRolesPage dTUserRolesPage, DTUserRoleAddPage dTUserRoleAddPage, String roleName){
        dTUserRolesPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserRoleAddPage.getPageTitle());
        
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
        
        //Save and return
        dTUserRoleAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserRolesPage.getPageTitle());
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTUserRolesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserRolesPage.spinnerTag);        
        
        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUserRolesPage.getTable().findRowList(roleName, rolesTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
    }
    
    
    
    /**
     * This is a helper method that delete a role to be used in these tests
     * @param dTUserRolesPage
     * @param roleName
     * @return true if the role has been deleted, else false 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteRole(DTUserRolesPage dTUserRolesPage, String roleName) throws InterruptedException{
        Kebab kebab = dTUserRolesPage.getTable().getKebabOnTable(roleName, rolesTableHeaderTitles.get(0), rolesTableHeaderTitles.get(2));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("Role not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        Utils.waitUntilIsVisible(driver, dTUserRolesPage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTUserRolesPage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", roleName));
        Assert.assertTrue(dTUserRolesPage.getModalBody().getText().contains(roleName.toLowerCase().substring(0, 10)));
        Utils.waitUntilIsClickable(driver, dTUserRolesPage.getDeleteModalButton());
        sleep(100);
        dTUserRolesPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
        /** Debug code **/ Logger.getGlobal().info("delete role return true");        
        return true;
    }
    
    
    
    /**
     * 
     * @param dTUserProfileTypePage
     * @param dTUserProfileTypeAddPage
     * @param profileTypeName
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean addProfileType(DTUserProfileTypePage dTUserProfileTypePage,
            DTUserProfileTypeAddPage dTUserProfileTypeAddPage, String profileTypeName) throws InterruptedException
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
        Utils.waitUntilIsPresent(driver, dTUserProfileTypePage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserProfileTypePage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getTableBody());
        
        //Assert the presence of the created profile type in the Profile type table
        List<WebElement> createdUser = dTUserProfileTypePage.getTable()
                .findRowList(profileTypeName, rolesTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
    }
    
    
    
    /**
     * 
     * @param dTUserProfileTypePage
     * @param profileTypeName
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteProfileType(DTUserProfileTypePage dTUserProfileTypePage,
            String profileTypeName) throws InterruptedException
    {
        Kebab kebab = dTUserProfileTypePage.getTable().getKebabOnTable(profileTypeName, rolesTableHeaderTitles.get(0), rolesTableHeaderTitles.get(2));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("Profile Type not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        
        Utils.waitUntilIsVisible(driver, dTUserProfileTypePage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTUserProfileTypePage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", profileTypeName));
        Assert.assertTrue(dTUserProfileTypePage.getModalBody().getText().contains(profileTypeCode));
        Utils.waitUntilIsClickable(driver, dTUserProfileTypePage.getDeleteModalButton());
        sleep(100);
        dTUserProfileTypePage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
        /** Debug code **/ Logger.getGlobal().info("delete Profile Type return true");   
        return true;
    }
    
    
    
    /**
     * This is a helper method that creates a standard role to be used in these tests
     * @param dTUserGroupsPage User roles list page to start
     * @param dTUserGroupAddPage Add User Role Page
     * @param groupName roleName to set
     * @return true if the user has been created successfully, else false 
     */
    public boolean addGroup(DTUserGroupsPage dTUserGroupsPage, DTUserGroupAddPage dTUserGroupAddPage, String groupName){
        dTUserGroupsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserGroupAddPage.getPageTitle());
        
        //Compilation of the page
        dTUserGroupAddPage.setName(groupName);
        
        //Save and return
        dTUserGroupAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getPageTitle());
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTUserGroupsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUserGroupsPage.spinnerTag);        
        
        //Assert the presence of the created user in the Users table
        List<WebElement> createdUser = dTUserGroupsPage.getTable().findRowList(groupName, rolesTableHeaderTitles.get(0));
        
        return(!createdUser.isEmpty());
    }
    
    
        
    /**
     * This is a helper method that delete a role to be used in these tests
     * @param dTUserGroupsPage
     * @param groupName
     * @return true if the role has been deleted, else false 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteGroup(DTUserGroupsPage dTUserGroupsPage, String groupName) throws InterruptedException{
        Kebab kebab = dTUserGroupsPage.getTable().getKebabOnTable(groupName, groupsTableHeaderTitles.get(0), rolesTableHeaderTitles.get(2));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("group not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        Utils.waitUntilIsVisible(driver, dTUserGroupsPage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTUserGroupsPage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", groupName));
        Assert.assertTrue(dTUserGroupsPage.getModalBody().getText().contains(groupName.toLowerCase()));
        Utils.waitUntilIsClickable(driver, dTUserGroupsPage.getDeleteModalButton());
        sleep(100);
        dTUserGroupsPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTUsersPage.modalWindowTag);
        /** Debug code **/ Logger.getGlobal().info("delete role return true");        
        return true;
    }
    
    
}//end class
