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
import org.entando.selenium.pages.DTDataModelsAddPage;
import org.entando.selenium.pages.DTDataModelsPage;
import org.entando.selenium.utils.DataModelsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Add Data Models Page
 * 
 * @version 1.01
 */
public class DTDataModelsAddTest extends DataModelsTestBase {
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

        //Final page title
        String pageTitle = "Add";

        //Buttons name
        String button1 = "New";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getNewButton());
        
        //Click on New Button
        dTDataModelsPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTDataModelsAddPage.getSaveButton());
                
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTDataModelsAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTDataModelsAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTDataModelsAddPage.getTooltip());
        Assert.assertTrue(dTDataModelsAddPage.getTooltip().isDisplayed());
        
        //Verify "field required" warning
        dTDataModelsAddPage.getCode().click();
        dTDataModelsAddPage.getType().click();
        dTDataModelsAddPage.getCode().click();
        
        Assert.assertTrue("Code Field Error is not displayed", 
                dTDataModelsAddPage.getCodeFieldError().isDisplayed());
        Assert.assertTrue("Type Field Error is not displayed", 
                dTDataModelsAddPage.getTypeSelectError().isDisplayed());
        
        //Verify Save button status
        Assert.assertFalse("Save Button is enabled", 
                dTDataModelsAddPage.getSaveButton().isEnabled());
        
        
        //Compilation of the fields
        dTDataModelsAddPage.setCode(dataModelCode);
        dTDataModelsAddPage.setName(dataModelName);
        dTDataModelsAddPage.setTypeSelect(defaultType);
        dTDataModelsAddPage.setModel(defaultModel);
        dTDataModelsAddPage.setStyleSheet(defaultStyleSheet);
        
        //Save the data
        dTDataModelsAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTDataModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTDataModelsPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getTableBody());
        
        //Verify the alert message
        Assert.assertTrue("Alert Message has not displayed",
                dTDataModelsPage.getAlertMessage().isDisplayed());
        Assert.assertTrue("Invalid Alert Message content",
                dTDataModelsPage.getAlertMessageContent().contains("has been created"));
        dTDataModelsPage.getCloseMessageButton().click();
        
        
        List<WebElement> createdDataModel = dTDataModelsPage.getTable()
                .findRowList(dataModelCode, expectedHeaderTitles.get(2));
        
        Assert.assertTrue(!createdDataModel.isEmpty());
        
        //Delete the Data Model
        Assert.assertTrue("Unable to delete the data model",
              deleteDataModel(dTDataModelsPage, dataModelCode));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
