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
import org.entando.selenium.pages.DTPageAddPage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test to add a page in the page tree
 * 
 * @version 1.01
 */
public class DTPageAddTest extends PageTreeTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
    
    @Autowired
    public DTPageAddPage dTPageAddPage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Page Designer";
        String secondLevelLink = "Page Tree";
        
        //Final page title
        String pageTitle = "Add";
                
        //The name of the branch
        String branchName = "Home";
        
        //Undefined option of Select
        String undefinedOption = "Choose an option";
        
        
        //Field parameters
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String pageName = "SeleniumTest" + randomNumber;
        String enTitle = pageName;
        String itTitle = pageName;
        String esTitle = pageName;
        String codeField = pageName;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        dTPageTreePage.getAddButton().click();
                
        //Asserts the PAGE TITLE is the expected one
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getMimeTypeHelpButton());
        driver.get(driver.getCurrentUrl());
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getPageTitle());
        Assert.assertEquals(pageTitle, dTPageAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTPageAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getTooltip());
        Assert.assertTrue(dTPageAddPage.getTooltip().isDisplayed());
        
        
        //Verify "field required" warning
        dTPageAddPage.getEnTitleField().click();
        dTPageAddPage.getItTitleField().click();
        Assert.assertTrue(dTPageAddPage.getEnTitleFieldError().isDisplayed());
        dTPageAddPage.getCodeField().click();
        Assert.assertTrue(dTPageAddPage.getItTitleFieldError().isDisplayed());
        dTPageAddPage.getOwnerGroup().selectByVisibleText(super.ownerGroupName);
        dTPageAddPage.getOwnerGroup().selectByVisibleText(undefinedOption);
        Assert.assertTrue(dTPageAddPage.getCodeFieldError().isDisplayed());
        dTPageAddPage.getPageModel().selectByVisibleText(super.pageModelName);
        dTPageAddPage.getPageModel().selectByVisibleText(undefinedOption);
        Assert.assertTrue(dTPageAddPage.getOwnerGroupError().isDisplayed());
        dTPageAddPage.getItTitleField().click();
        Assert.assertTrue(dTPageAddPage.getPageModelError().isDisplayed());
        dTPageAddPage.getEnTitleField().click();
        //Assert.assertTrue(dTPageAddPage.getEsTitleFieldError().isDisplayed());
        
        
        //Verify the asterisc presence and help button and message
        Assert.assertTrue(dTPageAddPage.getEnTitleAsterisk().isDisplayed());
        Assert.assertTrue(dTPageAddPage.getItTitleAsterisk().isDisplayed());
        Assert.assertTrue(dTPageAddPage.getEnTitleAsterisk().isDisplayed());
        Assert.assertTrue(dTPageAddPage.getCodeAsterisk().isDisplayed());
        dTPageAddPage.getCodeHelpButton().click();
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getCodeHelpPopOver());
        Assert.assertTrue(dTPageAddPage.getCodeHelpPopOver().getText().equals("Insert page code"));
        Assert.assertTrue(dTPageAddPage.getOwnerGroupAsterisk().isDisplayed());
        Assert.assertTrue(dTPageAddPage.getPageModelAsterisk().isDisplayed());
        dTPageAddPage.getPageModelHelpButton().click();
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getPageModelHelpPopOver());
        Assert.assertTrue(dTPageAddPage.getPageModelHelpPopOver().getText().equals("Select a page model"));
        dTPageAddPage.getDisplayedInMenuHelpButton().click();
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getDisplayedInMenuHelpPopOver());
        Assert.assertTrue(dTPageAddPage.getDisplayedInMenuHelpPopOver().getText().equals("Show this page in menu"));
        dTPageAddPage.getSeoHelpButton().click();
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getSeoHelpPopOver());
        Assert.assertTrue(dTPageAddPage.getSeoHelpPopOver().getText().equals("Activate SEO on page"));
        dTPageAddPage.getCharsetHelpButton().click();
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getCharsetHelpPopOver());
        Assert.assertTrue(dTPageAddPage.getCharsetHelpPopOver().getText().equals("Interpret a sequence of byte as representation of characters.The Default is set for the machine in use"));
        dTPageAddPage.getMimeTypeHelpButton().click();
        Utils.waitUntilIsVisible(driver, dTPageAddPage.getMimeTypeHelpPopOver());
        Assert.assertTrue(dTPageAddPage.getMimeTypeHelpPopOver().getText().equals("Identify the type of information (imagine, text..) that Entando gives back to the asking browser"));
        sleep(500);
        
        
        
        //Verify Switches
        Assert.assertTrue(dTPageAddPage.getDisplayedInMenuSwitch().isOn());
        Assert.assertFalse(dTPageAddPage.getSeoSwitch().isOn());
        dTPageAddPage.getDisplayedInMenuSwitch().setOff();
        dTPageAddPage.getDisplayedInMenuSwitch().setOn();
        sleep(500);
        dTPageAddPage.getSeoSwitch().setOn();
        dTPageAddPage.getSeoSwitch().setOff();
        Assert.assertTrue(dTPageAddPage.getDisplayedInMenuSwitch().isOn());
        Assert.assertFalse(dTPageAddPage.getSeoSwitch().isOn());
        
        
        //Verify default set of charset and mime type
        Assert.assertEquals(super.defaultCharset, dTPageAddPage.getCharset().getFirstSelectedOption().getText());
        Assert.assertEquals(super.defaultMimeType, dTPageAddPage.getMimeType().getFirstSelectedOption().getText());
        
        
        //Verify Save buttons are disable
        Assert.assertFalse(dTPageAddPage.getSaveButton().isEnabled());
        Assert.assertFalse(dTPageAddPage.getSaveAndConfigureButton().isEnabled());
                
        
        //Compilation of the page
        dTPageAddPage.setEnTitleField(enTitle);
        dTPageAddPage.setItTitleField(itTitle);
        //dTPageAddPage.setEsTitleField(esTitle);
        dTPageAddPage.setCodeField(codeField);
        
        
        //Selection of the branch
        ExpandableTable table = dTPageAddPage.getTable();
        WebElement row = table.findRowList(branchName, super.headerTitles.get(0)).get(0);
        row.click();
        
        
        //Selection of the groups
        dTPageAddPage.getOwnerGroup().selectByVisibleText(super.ownerGroupName);
        dTPageAddPage.getPageModel().selectByVisibleText(super.pageModelName);
        
        
        //Test of the add and delete of join groups
        dTPageAddPage.addJoinGroup(super.joinGroupName);
        List<String> joinGroupList = dTPageAddPage.getJoinGroupAddedList();
        Assert.assertEquals(1, joinGroupList.size());
        Assert.assertTrue(joinGroupList.contains(super.joinGroupName));
        dTPageAddPage.deleteJoinGroup(super.joinGroupName);
        
        joinGroupList = dTPageAddPage.getJoinGroupAddedList();
        Assert.assertTrue(joinGroupList.isEmpty());
        
        //Add a join group
        dTPageAddPage.addJoinGroup(super.joinGroupName);
        
        
        //Save and come back to the tree page
        Assert.assertTrue(dTPageAddPage.getSaveButton().isEnabled());
        dTPageAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getAddButton());
        
        //Assert the presence of the created page in the "page tree" table
        List<WebElement> createdPage = dTPageTreePage.getTable().findRowList(enTitle, super.headerTitles.get(0));
        Assert.assertFalse(createdPage.isEmpty());
        
        //Verify "displayed in menu" is "Yes"
        WebElement cell = dTPageTreePage.getTable().getCell(pageName, super.headerTitles.get(0), super.headerTitles.get(2));
        Assert.assertTrue(cell.getText().equals("Yes"));
        
        //Delete the created page
        deletePage(dTPageTreePage, pageName);
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
