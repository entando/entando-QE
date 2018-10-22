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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTCategoriesAddPage;
import org.entando.selenium.pages.DTCategoriesDetailsPage;
import org.entando.selenium.pages.DTCategoriesPage;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.utils.CategoriesTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Details Category Page
 * 
 * @version 1.01
 */
public class DTCategoriesDetailsTest extends CategoriesTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTCategoriesPage dTCategoriesPage;
    
    @Autowired
    public DTCategoriesAddPage dTCategoriesAddPage;
    
    @Autowired
    public DTCategoriesDetailsPage dTCategoriesDetailsPage;

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
        String pageTitle = "Details";
        
        //Kebab Action
        String action = "Details";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
        //Click on Details kebab menù
        Kebab kebab = dTCategoriesPage.getTable().getKebabOnTable(defaultCategoryNameAlreadyPresent,
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
        Assert.assertEquals(pageTitle, dTCategoriesDetailsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTCategoriesDetailsPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTCategoriesDetailsPage.getTooltip());
        Assert.assertTrue(dTCategoriesDetailsPage.getTooltip().isDisplayed());
        
        //Assert fields content correctness
        dTCategoriesDetailsPage.getInfoButton().click();
        Utils.waitUntilIsVisible(driver, dTCategoriesDetailsPage.getItField());
        
        Assert.assertTrue("Code Field not contains the right Category Code",
                defaultCategoryNameAlreadyPresent.equalsIgnoreCase(
                        dTCategoriesDetailsPage.getCodeField().getText()));
        Assert.assertTrue("En Field not contains the right En Category Name",
                defaultCategoryNameAlreadyPresent.equalsIgnoreCase(
                        dTCategoriesDetailsPage.getEnField().getText().substring(2)));
        Assert.assertTrue("It Field not contains the right It Category Name",
                defaultCategoryNameAlreadyPresent.equalsIgnoreCase(
                dTCategoriesDetailsPage.getItField().getText().substring(2)));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
    }
    
}//end class
