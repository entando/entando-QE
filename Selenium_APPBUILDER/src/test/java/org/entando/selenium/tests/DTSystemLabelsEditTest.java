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
import org.entando.selenium.pages.DTLabelsAndLanguagesPage;
import org.entando.selenium.pages.DTSystemLabelsAddPage;
import org.entando.selenium.pages.DTSystemLabelsPage;
import org.entando.selenium.testHelpers.SystemLabelsTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Label Edit page
 * 
 * @version 1.01
 */
public class DTSystemLabelsEditTest extends SystemLabelsTestBase{
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
        
        //Final page title
        String pageTitle = "Edit";
               
        //Action menù
        String action = "Edit";
        
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
        
        //Create a label
        Assert.assertTrue(addLabel(dTSystemLabelsPage, dTSystemLabelsAddPage));
        
        //Searching the corresponding kebab menù in the table
        Kebab kebab = dTSystemLabelsPage.getTable().getKebabOnTable(code, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(2));
        Assert.assertFalse(kebab == null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(action).click();
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("The PAGE TITLE isn't the expected one",
                pageTitle, dTSystemLabelsAddPage.getPageTitle().getText());
        
        sleep(600);
        
        //Verify fields
        Assert.assertFalse("Code Field is enabled",
                dTSystemLabelsAddPage.getCodeField().isEnabled());
        Assert.assertEquals("Invalid Code field content", 
                code, 
                dTSystemLabelsAddPage.getCodeField().getAttribute("value"));
        Assert.assertEquals("Invalid En Name field content", 
                code, 
                dTSystemLabelsAddPage.getEnNameField().getAttribute("value"));
        Assert.assertEquals("Invalid It Name field content", 
                code, 
                dTSystemLabelsAddPage.getItNameField().getAttribute("value"));
        
        //Save the data
        dTSystemLabelsAddPage.getSaveButton().click();
        
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTSystemLabelsPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTSystemLabelsPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTSystemLabelsPage.getTableBody());
        
        List<WebElement> createdDataModel = dTSystemLabelsPage.getTable()
                .findRowList(code, expectedHeaderTitles.get(0));
        
        Assert.assertTrue("Can't find the created element in the table",
                !createdDataModel.isEmpty());
        
        //Delete the Data Model
        Assert.assertTrue("Unable to delete the label",
              deleteLabel(dTSystemLabelsPage, code));
                
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/        
    }

}//end class
