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
import org.entando.selenium.utils.DataTypesTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Edit Data Types Page
 * 
 * @version 1.01
 */
public class DTDataTypesEditTest extends DataTypesTestBase {
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
        String pageTitle = "Edit";

        //Kebab Action
        String action = "Edit";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getNewButton());
        
        //Create a Data Type
        Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage));
        
        Kebab kebab = dTDataTypesPage.getTable().getKebabOnTable(dataTypeCode,
                expectedHeaderTitles.get(1), expectedHeaderTitles.get(3));
        Assert.assertFalse("Element not found on data types table", kebab == null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(action).click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getSaveButton());
                
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTDataTypesAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTDataTypesAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getTooltip());
        Assert.assertTrue(dTDataTypesAddPage.getTooltip().isDisplayed());
        
        //Verify fields
        Assert.assertFalse("Code Field is enabled",
                dTDataTypesAddPage.getCode().isEnabled());
        Assert.assertEquals("Invalid Code field content", 
                dataTypeCode, 
                dTDataTypesAddPage.getCode().getAttribute("value"));
        Assert.assertEquals("Invalid Name field content", 
                dataTypeName, 
                dTDataTypesAddPage.getName().getAttribute("value"));
        
        
        //Save the data
        dTDataTypesAddPage.getSaveButton().click();
                
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTDataTypesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTDataTypesPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getTableBody());
        
        List<WebElement> createdDataType = dTDataTypesPage.getTable()
                .findRowList(dataTypeCode, expectedHeaderTitles.get(1));
        
        Assert.assertTrue(!createdDataType.isEmpty());
        
        //Delete the Data Type
        Assert.assertTrue("Unable to delete the data type",
                deleteDataType(dTDataTypesPage, dataTypeCode));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
    }
}
