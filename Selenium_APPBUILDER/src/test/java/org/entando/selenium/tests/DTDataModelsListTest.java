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
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTDataModelsAddPage;
import org.entando.selenium.pages.DTDataModelsPage;
import org.entando.selenium.testHelpers.DataModelsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.SoftAssert;

/**
 * This class perform a test of the Data Type page
 * 
 * @version 1.01
 */
public class DTDataModelsListTest extends DataModelsTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTDataModelsPage dTDataModelsPage;
    
    @Autowired
    public DTDataModelsAddPage dtDataModelsAddPage;

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
        
        //Final page title
        String pageTitle = "Data Models";
        
        //Buttons name
        String button1 = "New";
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTDataModelsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTDataModelsPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getTooltip());
        Assert.assertTrue(dTDataModelsPage.getTooltip().isDisplayed());
        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertEquals(false, dTDataModelsPage.getTooltip().getText().equals("datamodel.help"), "tooltip is wrong");
        softAssertion.assertAll();
        //Assert.assertFalse("The help content is wrong", );
      
                
        //Asserts the presence of the BUTTON with displayed name as argument
        Assert.assertTrue(dTDataModelsPage.getNewButton().getText().equals(button1));
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(expectedHeaderTitles, dTDataModelsPage.getTable().getHeaderTitlesList());
        
        
        for(int i=1; i<5; i++){
            deleteDataModel(dTDataModelsPage, String.valueOf(i));
        }
        
        
        
        for(int i=1; i<5; i++){
        Assert.assertTrue(addDataModel(dTDataModelsPage, dtDataModelsAddPage, String.valueOf(i), "model_name"+String.valueOf(i), "SeleniumTest_DontTouch"));
        Assert.assertTrue("Browsable table functionality wrong",
                checkBrowsableTable(dTDataModelsPage));
        
         }
        
        
        //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue("Browsable table functionality wrong",
                checkBrowsableTable(dTDataModelsPage));
        
        
        for(int i=1; i<5; i++){
            Assert.assertTrue(deleteDataModel(dTDataModelsPage, String.valueOf(i)));
            
            Assert.assertTrue("Browsable table functionality wrong",
                checkBrowsableTable(dTDataModelsPage));
        }
        
         //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue("Browsable table functionality wrong",
                checkBrowsableTable(dTDataModelsPage));
        
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
