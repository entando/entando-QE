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
import org.entando.selenium.pages.DTDataTypesAddPage;
import org.entando.selenium.pages.DTDataTypesPage;
import org.entando.selenium.testHelpers.DataTypesTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Add Data Types Page
 * 
 * @version 1.01
 */
public class DTDataTypesAddTest extends DataTypesTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTDataTypesPage dTDataTypesPage;
    
    @Autowired
    public DTDataTypesAddPage dTDataTypesAddPage;
    
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
        String secondLevelLink = "Data Types";

        //Final page title
        String pageTitle = "Add";

        //Buttons name
        String button1 = "New";
        
        
        //A data type with this name and code will be used in this test.
        String dataTypeName = "Selenium_Do_Not_Touch17S";
        String dataTypeCode = "17S";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getNewButton());
        
        //Click on New Button
        dTDataTypesPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getSaveButton());
                
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTDataTypesAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTDataTypesAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getTooltip());
        Assert.assertTrue(dTDataTypesAddPage.getTooltip().isDisplayed());
        
        //Verify "field required" warning
        dTDataTypesAddPage.getCode().click();
        dTDataTypesAddPage.getName().click();
        dTDataTypesAddPage.getCode().click();
        Assert.assertTrue("Code Field Error is not displayed", 
                dTDataTypesAddPage.getCodeFieldError().isDisplayed());
        Assert.assertTrue("Name Field Error is not displayed", 
                dTDataTypesAddPage.getNameFieldError().isDisplayed());
        
        //Verify Save button status
        Assert.assertFalse("Save Button is enabled", 
                dTDataTypesAddPage.getSaveButton().isEnabled());
        
        
        //Inserting values that will result in an error message as the dataType already exists
        dTDataTypesAddPage.setCode(dataTypeCode);
        dTDataTypesAddPage.setName(dataTypeName);
        
        //Save the data
        dTDataTypesAddPage.getSaveButton().click();
        
        //Verify Error Message
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getAlertMessage());
        
        Assert.assertTrue("Error message for DataType - GOT: " +  dTDataTypesAddPage.getAlertMessageContent() + "- EXPECTED string that contains: created successfully", 
                dTDataTypesAddPage.getAlertMessageContent().contains("created succesfully")); //to fix this contains a spelling error to pass
        
        dTDataTypesAddPage.getCloseMessageButton().click();
        
        dTDataTypesAddPage.getSaveButton().click();
        
        
        
        
       
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getNewButton());
        
        
        
       //sleep(3000);
       
       

       
       Utils.waitUntilIsVisible(driver, dTDataTypesPage.getTableBody());
       List<WebElement> createdDataType = dTDataTypesPage.getTable()
                .findRowList(dataTypeCode, expectedHeaderTitles.get(1));
        
        Assert.assertTrue(!createdDataType.isEmpty());
       
       
       
       //Inserting values that will result in a already exists error message
       Utils.waitUntilIsVisible(driver, dTDataTypesPage.getNewButton());
        dTDataTypesPage.getNewButton().click();
           
        
        dTDataTypesAddPage.setCode(dataTypeCode1);
        dTDataTypesAddPage.setName(dataTypeName1);
        
        dTDataTypesAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getAlertMessage());
        
        Assert.assertTrue("Error message for DataType - GOT: " +  dTDataTypesAddPage.getAlertMessageContent() + "- EXPECTED string that contains: already exists", 
                dTDataTypesAddPage.getAlertMessageContent().contains("already exists"));
        
        
        dTDataTypesAddPage.getCloseMessageButton().click();
        
        //sleep(5000);
        
        dTDataTypesAddPage.getDataTypesList().click();
        
        
        
        
        
        
         //Delete the Data Type to clean up
        Assert.assertTrue("Unable to delete the data type",
                deleteDataType(dTDataTypesPage, dataTypeCode));
        
      
        
        

        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}
