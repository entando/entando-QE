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
import org.entando.selenium.utils.CategoriesTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Add Categories Page
 * 
 * @version 1.01
 */
public class DTCategoriesAddTest extends CategoriesTestBase{
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
        String pageTitle = "Add";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
        //Click on New Button
        dTCategoriesPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTCategoriesAddPage.getSaveButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTCategoriesAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTCategoriesAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTCategoriesAddPage.getTooltip());
        Assert.assertTrue(dTCategoriesAddPage.getTooltip().isDisplayed());
        
        //Verify "field required" warning
        dTCategoriesAddPage.getEnNameField().click();
        dTCategoriesAddPage.getItNameField().click();
        dTCategoriesAddPage.getCodeField().click();
        dTCategoriesAddPage.getEnNameField().click();
        
        Assert.assertTrue("En Name Field Error is not displayed", 
                dTCategoriesAddPage.getEnNameFieldError().isDisplayed());
        Assert.assertTrue("It Name Field Error is not displayed", 
                dTCategoriesAddPage.getItNameFieldError().isDisplayed());
        Assert.assertTrue("Code Field Error is not displayed", 
                dTCategoriesAddPage.getCodeFieldError().isDisplayed());
        
        //Verify Save button status
        Assert.assertFalse("Save Button is enabled but fields are empty", 
                dTCategoriesAddPage.getSaveButton().isEnabled());
                
        //Compilation of the fields
        dTCategoriesAddPage.setEnNameField(defaultCategoryName);
        dTCategoriesAddPage.setItNameField(defaultCategoryName);
        dTCategoriesAddPage.setCodeField(defaultCategoryName);
        
        //Select the branch
        ExpandableTable table = dTCategoriesAddPage.getTable();
        WebElement row = table.findRowList(defaultBranchName, expectedHeaderTitles.get(0)).get(0);
        row.click();
        
        //Verify Save button status
        Assert.assertTrue("Save Button is disabled but the form is compiled", 
                dTCategoriesAddPage.getSaveButton().isEnabled());
        
        //Save the data
        dTCategoriesAddPage.getSaveButton().click();
        
        //Return back
        Utils.waitUntilIsVisible(driver, dTCategoriesPage.getAddButton());
        Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
        List<WebElement> createdCategory = dTCategoriesPage.getTable()
                .findRowList(defaultCategoryName, expectedHeaderTitles.get(0));
        
        Assert.assertTrue("Can't find the created element in the table",
                !createdCategory.isEmpty());
        
        //Delete the Data Model
        Assert.assertTrue("Unable to delete the category",
              deleteCategory(dTCategoriesPage, defaultCategoryName));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
    }

}//end class
