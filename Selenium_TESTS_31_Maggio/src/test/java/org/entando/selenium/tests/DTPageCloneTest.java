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
import org.entando.selenium.pages.DTPageClonePage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test to clone a page in the page tree
 * 
 * @version 1.01
 */
public class DTPageCloneTest extends PageTreeTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
    
    @Autowired
    public DTPageAddPage dTPageAddPage;
    
    @Autowired
    public DTPageClonePage dTPageClonePage;
    
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
        String pageTitle = "Clone";
        
        //pageName to delete
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String pageName = "SeleniumTest" + randomNumber;
        
        //Where create a page for the test
        String branchName = "Home";
        
        //Kebab menù action
        String kebabAction = "Clone";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        //Create a page to clone
        Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage, pageName));
        
        Kebab kebab = dTPageTreePage.getTable().getKebabOnTable(pageName, headerTitles.get(0), headerTitles.get(3));
        //Assert the item has been found
        Assert.assertFalse(kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        
        Utils.waitUntilIsVisible(driver, dTPageClonePage.getSaveAndConfigureButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTPageClonePage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTPageAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTPageClonePage.getTooltip());
        Assert.assertTrue(dTPageAddPage.getTooltip().isDisplayed());
        
        //Assert the Saves buttons are disables
        Assert.assertFalse(dTPageClonePage.getSaveButton().isEnabled());
        Assert.assertFalse(dTPageClonePage.getSaveAndConfigureButton().isEnabled());
        
        randomNumber = generator.nextInt(9999);
        String newPageName = pageName + "Clone";
        String newCode = newPageName + "" + randomNumber;
        //Set the new page cloned name and code
        dTPageClonePage.setEnTitleField(newPageName);
        dTPageClonePage.setItTitleField(newPageName);
        dTPageClonePage.setEsTitleField(newPageName);
        dTPageClonePage.setCodeField(newCode);
        
        //Select the branch
        ExpandableTable table = dTPageAddPage.getTable();
        WebElement row = table.getRowsList().get(0);
        row.click();
        
        //Save click
        dTPageAddPage.getSaveButton().click();
        
        //Back to Tree page
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getSearchButton());
        
        
        //Assert the presence of the cloned page in the tree table
        List<WebElement> createdPage = dTPageTreePage.getTable().findRowList(newPageName, super.headerTitles.get(0));
        Assert.assertFalse(createdPage.isEmpty());
        
        //Delete the page
        deletePage(dTPageTreePage, pageName);
        deletePage(dTPageTreePage, newPageName);
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    
    }
}
