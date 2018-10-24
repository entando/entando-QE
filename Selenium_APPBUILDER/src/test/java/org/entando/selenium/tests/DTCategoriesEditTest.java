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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTCategoriesAddPage;
import org.entando.selenium.pages.DTCategoriesPage;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.testHelpers.CategoriesTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Edit Categories Page
 * 
 * @version 1.01
 */
public class DTCategoriesEditTest extends CategoriesTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTCategoriesPage dTCategoriesPage;
    
    @Autowired
    public DTCategoriesAddPage dTCategoriesAddPage;

    /*
        Test
    */  
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Configuration";
        String secondLevelLink = "Categories";
        
        //Final page title
        String pageTitle = "Edit";
        
        //Kebab Action
        String action = "Edit";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
        //Create a category
        Assert.assertTrue(addCategory(dTCategoriesPage, dTCategoriesAddPage));
        
        Kebab kebab = dTCategoriesPage.getTable().getKebabOnTable(defaultCategoryName,
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(1));
        Assert.assertFalse("Element not found on data types table", kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(action).click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTCategoriesAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTCategoriesAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTCategoriesAddPage.getTooltip());
        Assert.assertTrue(dTCategoriesAddPage.getTooltip().isDisplayed());
        sleep(500);
        
        //Verify fields
        Assert.assertFalse("Code Field is enabled",
                dTCategoriesAddPage.getCodeField().isEnabled());
        Assert.assertEquals("Invalid Code field content", 
                defaultCategoryName, 
                dTCategoriesAddPage.getCodeField().getAttribute("value"));
        Assert.assertEquals("Invalid En Name field content", 
                defaultCategoryName, 
                dTCategoriesAddPage.getEnNameField().getAttribute("value"));
        Assert.assertEquals("Invalid It Name field content", 
                defaultCategoryName, 
                dTCategoriesAddPage.getItNameField().getAttribute("value"));
        
        //Save the data
        dTCategoriesAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTCategoriesPage.getTableBody());
        
        List<WebElement> createdCategory = dTCategoriesPage.getTable()
                .findRowList(defaultCategoryName, expectedHeaderTitles.get(0));
        
        Assert.assertTrue("Edited Category is not present in the table",
                !createdCategory.isEmpty());
        
        //Delete the Category
        Assert.assertTrue("Unable to delete the data model",
              deleteCategory(dTCategoriesPage, defaultCategoryName));
        
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
    }
    
}//end class
