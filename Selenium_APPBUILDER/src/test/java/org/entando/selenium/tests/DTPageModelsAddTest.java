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
import org.entando.selenium.pages.DTPageModelsAddPage;
import org.entando.selenium.pages.DTPageModelsPage;
import org.entando.selenium.utils.PageModelsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Delete Page Model function
 * 
 * @version 1.01
 */
public class DTPageModelsAddTest extends PageModelsTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageModelsPage dTPageModelsPage;
        
    @Autowired
    public DTPageModelsAddPage dTPageModelsAddPage;

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
        String secondLevelLink = "Page Models";
        
        //Final pages titles
        String pageTitle = "Add";
        
        //Kebab column
        String kebabColumn = "Actions";
        
        
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getAddButton());
        
        //Click on New Button
        dTPageModelsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTPageModelsAddPage.getSaveButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTPageModelsAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTPageModelsAddPage.getHelp().click();
        Assert.assertTrue(dTPageModelsAddPage.getTooltip().isDisplayed());
        
        Assert.assertFalse("Save button is enabled",
                dTPageModelsAddPage.getSaveButton().isEnabled());
        
        //Verify the Fields errors
        dTPageModelsAddPage.getCodeField().click();
        dTPageModelsAddPage.getNameField().click();
        dTPageModelsAddPage.getJsonConfigurationField().click();
        dTPageModelsAddPage.clearJsonConfigurationField();
        dTPageModelsAddPage.getTemplateField().click();
        dTPageModelsAddPage.getCodeField().click();
        Assert.assertTrue(dTPageModelsAddPage.getCodeErrorField().isDisplayed());
        Assert.assertTrue(dTPageModelsAddPage.getJsonConfigurationErrorField().isDisplayed());
        Assert.assertTrue(dTPageModelsAddPage.getNameErrorField().isDisplayed());
        Assert.assertTrue(dTPageModelsAddPage.getTemplateErrorField().isDisplayed());
        
        Assert.assertFalse("Save button is enabled but the required fields are empty",
                dTPageModelsAddPage.getSaveButton().isEnabled());
        
        
        //Compilation of the fields
        dTPageModelsAddPage.setCodeField(code);
        dTPageModelsAddPage.setNameField(code);
        dTPageModelsAddPage.setJsonConfigurationField(jsonConfiguration);
        dTPageModelsAddPage.setTemplateField(template);
        
        
        Assert.assertTrue("Save button is disabled after the required fields compilation",
                dTPageModelsAddPage.getSaveButton().isEnabled());
        
        //Save the data
        dTPageModelsAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
                
        //Reload the page
        driver.get(driver.getCurrentUrl());
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        
        //Assert the element is present in the table
        List<WebElement> createdPageModels = dTPageModelsPage.getTable()
                .findRowList(code, expectedHeaderTitles.get(0));
        
        Assert.assertFalse(createdPageModels.isEmpty());
        
                
        //Delete the page model
        Assert.assertTrue(deletePageModel(dTPageModelsPage, code));
                
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
