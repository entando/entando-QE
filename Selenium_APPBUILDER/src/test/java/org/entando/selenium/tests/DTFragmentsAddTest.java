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
import org.entando.selenium.pages.DTFragmentNewPage;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.utils.FragmentsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Add Fragment Page
 * 
 * @version 1.01
 */
public class DTFragmentsAddTest extends FragmentsTestBase  {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFragmentPage dTFragmentPage;
        
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
        String action = "Edit";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTFragmentPage.getNewButton());
        
        //Click on New Button
        dTFragmentPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTFragmentNewPage.getSaveButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTFragmentNewPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTFragmentNewPage.getHelp().click();
        Assert.assertTrue(dTFragmentNewPage.getTooltip().isDisplayed());
        
        Assert.assertFalse("Save button is enabled",
                dTFragmentNewPage.getSaveButton().isEnabled());
        
        //Compilation of the fields
        String codeTest = code + "Add";
        dTFragmentNewPage.setCode(codeTest);
        dTFragmentNewPage.setGUICode(guiCode);
        
        Assert.assertTrue("Save button is disabled",
                dTFragmentNewPage.getSaveButton().isEnabled());
        
        //Save the data
        dTFragmentNewPage.save();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFragmentPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        
        
        //Assert the presence of the added fragment in the fragment table
        List<WebElement> createdFragment = dTFragmentPage.getTable()
                .findRowList(codeTest, expectedHeaderTitles.get(0));
        
        Assert.assertTrue(!createdFragment.isEmpty());
        
        //delete the fragment
        Assert.assertTrue(deleteFragment(dTFragmentPage, codeTest));
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
