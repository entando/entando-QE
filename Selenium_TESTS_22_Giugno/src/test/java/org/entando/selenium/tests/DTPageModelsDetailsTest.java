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
import org.entando.selenium.pages.DTPageModelsAddPage;
import org.entando.selenium.pages.DTPageModelsDetailsPage;
import org.entando.selenium.pages.DTPageModelsPage;
import org.entando.selenium.utils.PageModelsTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Page Model Details page
 * 
 * @version 1.01
 */
public class DTPageModelsDetailsTest extends PageModelsTestBase {
     /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageModelsPage dTPageModelsPage;
    
    @Autowired
    public DTPageModelsDetailsPage dTPageModelsDetailsPage;
    
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
        String secondLevelLink = "Fragments";
        
        //Final pages titles
        String pageTitle = "Fragment details";
        
        //Kebab column
        String kebabColumn = "Actions";
        
        //Action menù
        String action = "Details for: ";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getAddButton());
        
        //Create a Fragment
        Assert.assertTrue(addPageModel(dTPageModelsPage, dTPageModelsAddPage));
        
        //Searching the corresponding kebab menù in the table
        Kebab kebab = dTPageModelsPage.getTable().getKebabOnTable(code, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(3));
        Assert.assertFalse(kebab == null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(action + code).click();
        
        Utils.waitUntilIsVisible(driver, dTPageModelsDetailsPage.getPageTitle());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTPageModelsDetailsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTPageModelsDetailsPage.getHelp().click();
        Assert.assertTrue(dTPageModelsDetailsPage.getTooltip().isDisplayed());
        
        //Asserts the edit button is enabled
        Assert.assertTrue("Edit button disabled",
                dTPageModelsDetailsPage.getEditButton().isEnabled());
        
        //Verify the fields content
        Assert.assertEquals("Content of the \"Name\" field different from the expected one",
                code, dTPageModelsDetailsPage.getNameField().getText());
        Assert.assertEquals("Content of the \"Code\" field different from the expected one",
                code.toLowerCase(), dTPageModelsDetailsPage.getCodeField().getText());
        Assert.assertEquals("Content of the \"Json Configuration\" field different from the expected one",
                code, dTPageModelsDetailsPage.getJsonConfigurationField().getText());
        Assert.assertEquals("Content of the \"Template\" field different from the expected one",
                code.toLowerCase(), dTPageModelsDetailsPage.getTemplateField().getText());
        
       
        //Navigate to previous page
        driver.navigate().back();
        
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
              
        //delete the fragment
        Assert.assertTrue(deletePageModel(dTPageModelsPage, code));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
