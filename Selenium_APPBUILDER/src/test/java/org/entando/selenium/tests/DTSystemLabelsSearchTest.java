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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTLabelsAndLanguagesPage;
import org.entando.selenium.pages.DTSystemLabelsAddPage;
import org.entando.selenium.pages.DTSystemLabelsPage;
import org.entando.selenium.testHelpers.SystemLabelsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Search in the Labels page
 * 
 * @version 1.01
 */
public class DTSystemLabelsSearchTest extends SystemLabelsTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTLabelsAndLanguagesPage dTLabelsAndLanguagesPage;
    
    @Autowired
    public DTSystemLabelsPage dTSystemLabelsPage;
    
    @Autowired
    public DTSystemLabelsAddPage dTSystemLabelsAddPage;
    
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
        String secondLevelLink = "Labels and Languages";
        
        //labels name
        Random generator = new Random();
        int randomNumber = generator.nextInt(8999) + 1000;
        String labelBaseName = "1SLNM_TEST_";
        String labelName1 = labelBaseName + "SEARCH_1" + randomNumber;
        String labelName2 = labelBaseName + "SEARCH_2" + randomNumber;
        String labelNameFake = labelBaseName + "FAKE";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Loading page
        //Utils.waitUntilIsPresent(driver, dTLabelsAndLanguagesPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTLabelsAndLanguagesPage.spinnerTag);
        
        dTLabelsAndLanguagesPage.getSystemLabelsButton().click();
        
        //Create the labels
        Assert.assertTrue("Unable to add a label (1)",
                addLabel(dTSystemLabelsPage, dTSystemLabelsAddPage, labelName1));
        Assert.assertTrue("Unable to add a label (1)",
                addLabel(dTSystemLabelsPage, dTSystemLabelsAddPage, labelName2));
        
        //sleep(3000);
        
        Utils.waitUntilIsVisible(driver, dTSystemLabelsPage.getTableBody());
        
        //Search tests 01
        dTSystemLabelsPage.setSearchField(labelBaseName);
        dTSystemLabelsPage.getSearchButton().click();
        //Utils.waitUntilIsPresent(driver, dTSystemLabelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTSystemLabelsPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        int tableSize = dTSystemLabelsPage.getTable().tableSize();
        Assert.assertTrue("Elements founds: " + tableSize + " Expected: >2",
                tableSize >= 2);
        
        //Search tests 02
        dTSystemLabelsPage.setSearchField(labelName1);
        dTSystemLabelsPage.getSearchButton().click();
        //Utils.waitUntilIsPresent(driver, dTSystemLabelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTSystemLabelsPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        tableSize = dTSystemLabelsPage.getTable().tableSize();
        Assert.assertEquals("Elements founds: " + tableSize + " Expected: 1",
                1, tableSize);
        
        //Search tests 03
        dTSystemLabelsPage.setSearchField(labelName2);
        dTSystemLabelsPage.getSearchButton().click();
        //Utils.waitUntilIsPresent(driver, dTSystemLabelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTSystemLabelsPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        tableSize = dTSystemLabelsPage.getTable().tableSize();
        Assert.assertEquals("Elements founds: " + tableSize + " Expected: 1",
                1, tableSize);
        
        //Search tests 04
        dTSystemLabelsPage.setSearchField(labelNameFake);
        dTSystemLabelsPage.getSearchButton().click();
        //Utils.waitUntilIsPresent(driver, dTSystemLabelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTSystemLabelsPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        tableSize = dTSystemLabelsPage.getTable().tableSize();
        Assert.assertEquals("Elements founds: " + tableSize + " Expected: 0",
                0, tableSize);
        
        //Clear results
        dTSystemLabelsPage.setSearchField("");
        dTSystemLabelsPage.getSearchButton().click();
        
        //Delete the labels
        Assert.assertTrue("Unable to delete the label (1)",
              deleteLabel(dTSystemLabelsPage, labelName1));
        Assert.assertTrue("Unable to delete the label (2)",
              deleteLabel(dTSystemLabelsPage, labelName2));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
