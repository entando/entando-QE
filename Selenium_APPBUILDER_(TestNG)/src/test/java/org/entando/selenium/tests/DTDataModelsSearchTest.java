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
import org.entando.selenium.pages.DTDataModelsAddPage;
import org.entando.selenium.pages.DTDataModelsPage;
import org.entando.selenium.utils.DataModelsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Search in the Data Models page
 * 
 * @version 1.01
 */
public class DTDataModelsSearchTest extends DataModelsTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTDataModelsPage dTDataModelsPage;
    
    @Autowired
    public DTDataModelsAddPage dTDataModelsAddPage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
                Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Data";
        String secondLevelLink = "Data Models";
        
        Random generator = new Random();
        int randomNumber1 = generator.nextInt(9999);
        int randomNumber2 = generator.nextInt(9999);
        //Data Model name
        String dataModelName1 = "1SLNM_TEST_" + randomNumber1;
        //Data Model name
        String dataModelName2 = "1SLNM_TEST_" + randomNumber2;
        
        //Data Model Type
        String dataModelType1 = "SeleniumTest_DontTouch1";
        String dataModelType2 = "SeleniumTest_DontTouch2";
        
        //Data Model Code
        String dataModelCode1 = "1";
        String dataModelCode2 = "2";
        
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getNewButton());
        
        //Create two Data Models with the same type
        Assert.assertTrue(addDataModel(dTDataModelsPage, dTDataModelsAddPage,
                dataModelCode1, dataModelName1, dataModelType1));
        
        Assert.assertTrue(addDataModel(dTDataModelsPage, dTDataModelsAddPage,
                dataModelCode2, dataModelName2, dataModelType1));
        
        //Search tests 01
        dTDataModelsPage.setTypeSearchSelect(dataModelType1);
        dTDataModelsPage.getSearchButton().click();
        Utils.waitUntilIsPresent(driver, dTDataModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTDataModelsPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        int tableSize = dTDataModelsPage.getTable().tableSize();
        Assert.assertEquals("Elements founds: " + tableSize + " Expected: 2",
                2, tableSize);
        
        //Search tests 02
        dTDataModelsPage.setTypeSearchSelect(dataModelType2);
        dTDataModelsPage.getSearchButton().click();
        Utils.waitUntilIsPresent(driver, dTDataModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTDataModelsPage.spinnerTag);
        //Assert the presence of the serched element in the results table
        tableSize = dTDataModelsPage.getTable().tableSize();
        Assert.assertEquals("Elements founds: " + tableSize + " Expected: 0",
                0, tableSize);
        
        //Clear results
        dTDataModelsPage.setTypeSearchSelect("All");
        dTDataModelsPage.getSearchButton().click();
        
        //Delete the Data Models
        Assert.assertTrue("Unable to delete the data model 1",
              deleteDataModel(dTDataModelsPage, dataModelCode1));
        //Utils.waitUntilIsPresent(driver, dTDataModelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTDataModelsPage.spinnerTag);
        Assert.assertTrue("Unable to delete the data model 2",
              deleteDataModel(dTDataModelsPage, dataModelCode2));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
