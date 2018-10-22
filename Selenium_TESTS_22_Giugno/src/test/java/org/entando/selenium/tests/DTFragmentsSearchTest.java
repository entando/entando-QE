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
import java.util.Random;
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.pages.DTDashboardPage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTFragmentNewPage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Search in the Fragment page
 * 
 * @version 1.01
 */
public class DTFragmentsSearchTest extends FragmentsTestBase {
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
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Fragments";
        
        //Fragments code
        Random generator = new Random();
        int randomNumber = generator.nextInt(8999) + 1000;
        String code1 = "1SLNM_TEST_" + randomNumber;
        randomNumber = generator.nextInt(89999) + 10000;
        String code2 = "1SLNM_TEST_" + randomNumber;
        String code3 = "1SLNM_TEST_FAKE";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        
        //Verify default search form values
        Assert.assertEquals("Default Widget Type value is not \"All\"", "All", 
                dTFragmentPage.getWidgetTypeSelect().getFirstSelectedOption().getText());
        Assert.assertEquals("Default Plugin value is not \"All\"", "All", 
                dTFragmentPage.getPluginSelect().getFirstSelectedOption().getText());
        
        //Create elements to search
        Assert.assertTrue(addFragment(dTFragmentPage, dTFragmentNewPage, code1));
        Assert.assertTrue(addFragment(dTFragmentPage, dTFragmentNewPage, code2));
        
        //Search the first element
        dTFragmentPage.setCodeSearchField(code1);
        dTFragmentPage.getSearchButton().click();
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        int tableSize = dTFragmentPage.getTable().tableSize();
        Assert.assertTrue("First element not found!", tableSize > 0);
        Assert.assertEquals(1, tableSize);
        
        //Search the second element
        dTFragmentPage.setCodeSearchField(code2);
        dTFragmentPage.getSearchButton().click();
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        tableSize = dTFragmentPage.getTable().tableSize();
        Assert.assertTrue("Second element not found!", tableSize > 0);
        Assert.assertEquals(1, tableSize);
        
        //Search a portion of String of 
        dTFragmentPage.setCodeSearchField(code2.substring(0, 10));
        dTFragmentPage.getSearchButton().click();
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        tableSize = dTFragmentPage.getTable().tableSize();
        Assert.assertTrue("Elements not found!", tableSize > 0);
        Assert.assertEquals(2, tableSize);
        
        //Search a invented fragment code
        dTFragmentPage.setCodeSearchField(code3);
        dTFragmentPage.getSearchButton().click();
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        tableSize = dTFragmentPage.getTable().tableSize();
        Assert.assertTrue("Elements not found!", tableSize == 0);
        
        
        //Delete the fragments
        dTFragmentPage.setCodeSearchField(code2.substring(0, 10));
        dTFragmentPage.getSearchButton().click();
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        Assert.assertTrue(deleteFragment(dTFragmentPage, code1));
        Assert.assertTrue(deleteFragment(dTFragmentPage, code2));
               
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
