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
import org.entando.selenium.testHelpers.PageModelsTestBase;
import org.entando.selenium.utils.Parallelized;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;


/**
 * This class perform a test of the Page Model Add page
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTPageModelsEditTest extends PageModelsTestBase{
    
    public DTPageModelsEditTest(String browserName, String platformName) {
        super(browserName, platformName);
    }

    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException{
        /*
            Pages used on this test
        */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTPageModelsPage dTPageModelsPage = new DTPageModelsPage(driver);
        DTPageModelsAddPage dTPageModelsAddPage = new DTPageModelsAddPage(driver);

        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Page Models";
        
        //Final pages titles
        String pageTitle = "Edit";
        
        //Kebab column
        String kebabColumn = "Actions";
        
        //Action menù
        String action = "Edit";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getAddButton());
        
        //Create a PageModels
        Assert.assertTrue(addPageModel(dTPageModelsPage, dTPageModelsAddPage));
        
        //Searching the corresponding kebab menù in the table
        Kebab kebab = dTPageModelsPage.getTable().getKebabOnTable(code, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(2));
        Assert.assertFalse(kebab == null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(action).click();
        
        //Wait loading page
        sleep(500);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTPageModelsAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTPageModelsAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTPageModelsAddPage.getTooltip());
        Assert.assertTrue(dTPageModelsAddPage.getTooltip().isDisplayed());
        
        
        
        //Verify fields
        Assert.assertFalse("The code field is enabled",
                dTPageModelsAddPage.getCodeField().isEnabled());
        
        Assert.assertEquals("Content of the \"Code\" field different from the expected one",
                code,
                dTPageModelsAddPage.getCodeField().getAttribute("value"));
        
        Assert.assertEquals("Content of the \"Name\" field different from the expected one",
                code,
                dTPageModelsAddPage.getNameField().getAttribute("value"));
        
        /*Assert.assertEquals("Content of the \"Json Configuration\" field different from the expected one",
                jsonConfiguration,
                dTPageModelsAddPage.getJsonConfigurationField().getAttribute("value"));
        
        Assert.assertEquals("Content of the \"Template\" field different from the expected one",
                template,
                dTPageModelsAddPage.getTemplateField().getAttribute("value"));*/
        
                
        //Save the changes and come back to prev. page
        dTPageModelsAddPage.getSaveButton().click();
        
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        //Assert the presence of the edited fragment in the table
        List<WebElement> createdPageModels = dTPageModelsPage.getTable()
                .findRowList(code, expectedHeaderTitles.get(0));
        
        Assert.assertTrue(!createdPageModels.isEmpty());
        
        //delete the page model
        Assert.assertTrue(deletePageModel(dTPageModelsPage, code));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
