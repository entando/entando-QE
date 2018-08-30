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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTFragmentNewPage;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.pages.DTFragmentsDetailsPage;
import org.entando.selenium.utils.FragmentsTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Fragment Details page
 * 
 * @version 1.01
 */
public class DTFragmentsDetailsTest extends FragmentsTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFragmentPage dTFragmentPage;
    
    @Autowired
    public DTFragmentsDetailsPage dTFragmentDetailsPage;
    
    @Autowired
    public DTFragmentNewPage dTFragmentNewPage;

    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException{
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Fragments";
        
        //Final pages titles
        String pageTitle = "Fragment details";
        
        //Kebab column
        String kebabColumn = "Actions";
        
        //Action menù
        String action = "Details for: ";
        
        //Expected tables header titles
        List<String> expectedFragmentTableHeaderTitles = 
                Arrays.asList("Code", "Widget Type", "Plugin code");
        List<String> expectedWidgetTypeReferenceTableHeaderTitles = 
                Arrays.asList("Code", "Name");
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTFragmentPage.getNewButton());
        
        //Create a Fragment
        String testCode = code + "Edit";
        Assert.assertTrue(addFragment(dTFragmentPage, dTFragmentNewPage, testCode));
        
        //Searching the corresponding kebab menù in the table
        Kebab kebab = dTFragmentPage.getTable().getKebabOnTable(testCode, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(3));
        Assert.assertFalse(kebab == null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(action + testCode).click();
        
        Utils.waitUntilIsVisible(driver, dTFragmentDetailsPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTFragmentDetailsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTFragmentDetailsPage.getHelp().click();
        Assert.assertTrue(dTFragmentDetailsPage.getTooltip().isDisplayed());
        
        //Asserts the edit button is enabled
        Assert.assertTrue("Edit Button is disabled",
                dTFragmentDetailsPage.getEditButton().isEnabled());
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals("Fragment Table Header Titles incorrect",
                expectedFragmentTableHeaderTitles,
                dTFragmentDetailsPage.getDetailFragmentTable().getHeaderTitlesList());
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals("Widget Type Reference Table Header Titles incorrect",
                expectedWidgetTypeReferenceTableHeaderTitles, 
                dTFragmentDetailsPage.getWidgetTypeReferenceTable().getHeaderTitlesList());
        
        //Navigate to previous page
        driver.navigate().back();
        
        Utils.waitUntilIsVisible(driver, dTFragmentPage.getSearchButton());
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
              
        //delete the fragment
        Assert.assertTrue(deleteFragment(dTFragmentPage, testCode));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
