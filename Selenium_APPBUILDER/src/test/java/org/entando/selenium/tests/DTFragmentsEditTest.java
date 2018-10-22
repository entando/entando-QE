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

import org.entando.selenium.testHelpers.FragmentsTestBase;
import static java.lang.Thread.sleep;
import java.util.List;
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTFragmentEditPage;
import org.entando.selenium.pages.DTFragmentNewPage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Fragment Edit page
 * 
 * @version 1.01
 */
public class DTFragmentsEditTest extends FragmentsTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFragmentPage dTFragmentPage;
    
    @Autowired
    public DTFragmentEditPage dTFragmentEditPage;
    
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
        String pageTitle = "Edit";
                
        //Action menù
        String action = "Edit";
        
        
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
        kebab.getAction(action + " " + testCode).click();
        
        Utils.waitUntilIsVisible(driver, dTFragmentEditPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTFragmentEditPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTFragmentEditPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTFragmentEditPage.getTooltip());
        Assert.assertTrue(dTFragmentEditPage.getTooltip().isDisplayed());
        
        //Compiling the FORM fields
        dTFragmentEditPage.setGUICode(guiCode + " Edit by Selenium");
        
        //Asserts code field is disabled
        dTFragmentEditPage.getCode().click();
        Assert.assertFalse("Code Field is enabled",
                dTFragmentEditPage.getCode().isEnabled());
                
        //Save the changes and come back to prev. page
        dTFragmentEditPage.save();
        
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        
        //Assert the presence of the edited fragment in the table
        List<WebElement> createdFragment = dTFragmentPage.getTable()
                .findRowList(testCode, expectedHeaderTitles.get(0));
        
        Assert.assertTrue(!createdFragment.isEmpty());
        
        //delete the fragment
        Assert.assertTrue(deleteFragment(dTFragmentPage, testCode));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
