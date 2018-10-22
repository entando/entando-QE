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
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTCategoriesPage;
import org.entando.selenium.utils.CategoriesTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Categories page
 * 
 * @version 1.01
 */
public class DTCategoriesListTest extends CategoriesTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTCategoriesPage dTCategoriesPage;

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
        String pageTitle = "Categories";
        
        //Buttons names
        String button1 = "Add";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Loading page
        Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTCategoriesPage.getPageTitle().getText());        
        
        //Asserts the presence of the HELP button
        dTCategoriesPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTCategoriesPage.getTooltip());
        Assert.assertTrue(dTCategoriesPage.getTooltip().isDisplayed());

        //Asserts the presence of the BUTTON with displayed name as argument
        Assert.assertTrue(dTCategoriesPage.getAddButton().getText().equals(button1));

        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(expectedHeaderTitles, dTCategoriesPage.getTable().getHeaderTitlesList());
        
        //Verification of the opening tree functionality
        expandTable(dTCategoriesPage);
        
        List<WebElement> createdFragment = dTCategoriesPage.getTable()
                .findRowList(defaultCategoryNameBase, expectedHeaderTitles.get(0));
        
        Assert.assertTrue("There is a problem to expand the table",
                !createdFragment.isEmpty());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
