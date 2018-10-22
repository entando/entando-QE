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
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.pages.DTDashboardPage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Fragment page
 * 
 * @version 1.01
 */
public class DTFragmentsListTest extends FragmentsTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFragmentPage dTFragmentPage;

    /*
        Test
    */  
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Fragments";
        
        //Final page title
        String pageTitle = "Fragments";
        
        //Buttons names
        String button1 = "New";
        
        //Alert message
        String updatedAlertMessage = "The settings have been updated";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTFragmentPage.getPageTitle().getText());        
        
        //Asserts the presence of the HELP button
        dTFragmentPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTFragmentPage.getTooltip());
        Assert.assertTrue(dTFragmentPage.getTooltip().isDisplayed());

        //Asserts the presence of the BUTTON with displayed name as argument
        Assert.assertTrue(dTFragmentPage.getNewButton().getText().equals(button1));

        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(expectedHeaderTitles, dTFragmentPage.getTable().getHeaderTitlesList());
        
        Assert.assertTrue("List Tab isn't active",
                dTFragmentPage.getListButton().getAttribute("class").contains("active"));
        Assert.assertFalse("Settings Tab is active",
                dTFragmentPage.getSettingsButton().getAttribute("class").contains("active"));
        
        //Switch on Settings Tab
        dTFragmentPage.getSettingsButton().click();
        sleep(500);
        
        Assert.assertFalse("List Tab is active",
                dTFragmentPage.getListButton().getAttribute("class").contains("active"));
        Assert.assertTrue("Settings Tab isn't active",
                dTFragmentPage.getSettingsButton().getAttribute("class").contains("active"));
        
        Assert.assertTrue("Save button is not present",
                dTFragmentPage.getSaveButton().isDisplayed());
        
        Assert.assertTrue("Switch button is off",
                dTFragmentPage.getEnableEditingSwitch().isOn());
        
        dTFragmentPage.getSaveButton().click();
        
        Assert.assertEquals("Alert message is incorrect", updatedAlertMessage,
                dTFragmentPage.getAlertMessage().getText());
        
        dTFragmentPage.getCloseMessageButton().click();
        dTFragmentPage.getListButton().click();
        
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        
        //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue(checkBrowsableTable(dTFragmentPage));
        
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
