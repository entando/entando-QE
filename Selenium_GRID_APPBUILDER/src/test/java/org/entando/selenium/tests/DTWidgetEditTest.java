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

import org.entando.selenium.testHelpers.WidgetsTestBase;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTWidgetEditPage;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTWidgetAddPage;
import org.entando.selenium.pages.DTWidgetPage;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


/**
 * This class perform a test of the Edit Widget Page
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTWidgetEditTest extends WidgetsTestBase {
    
    //Constructor
    public DTWidgetEditTest(String browserName, String platformName) {
        super(browserName, platformName);
    }
    
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Pages used on this test
        */
        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTWidgetEditPage dTWidgetEditPage = new DTWidgetEditPage(driver);
        DTWidgetPage dTWidgetPage = new DTWidgetPage(driver);
        DTWidgetAddPage dTWidgetAddPage = new DTWidgetAddPage(driver);
    
        /*
                Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Widgets";
       
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getNewButton());
        
        //Add a Standard Widget to edit
        Assert.assertTrue("Unable to add a widget",
                addWidget(dTWidgetPage, dTWidgetAddPage));
        
        //refresh the page
        driver.navigate().refresh();
        
        //Edit the widget
        SimpleTable table = new SimpleTable(dTWidgetPage.getTables().get("user"));
        WebElement cell = table.getCell(code, expectedHeaderTitles.get(0), 
                expectedHeaderTitles.get(0));
        Assert.assertTrue(cell != null);
        cell.findElement(By.xpath(".//a")).click();
        //Wait loading the page
        Utils.waitUntilIsVisible(driver, dTWidgetEditPage.getPageTitle());
        Utils.waitUntilAttributeToBeNotEmpty(driver, dTWidgetEditPage.getEnTitleField(),
                "value");
        
        dTWidgetEditPage.getEnTitleField().clear();
        dTWidgetEditPage.getItTitleField().click();
        Assert.assertTrue("En Title field error not displayed",
                dTWidgetEditPage.getEnTitleFieldError().isDisplayed());
        Assert.assertFalse("Save Button is enabled", 
                dTWidgetEditPage.getSaveButton().isEnabled());
        dTWidgetEditPage.setEnTitleField(code);
        
        dTWidgetEditPage.getSaveButton().click();
        
        //Wait loading page
        try
        {
            Utils.waitUntilIsPresent(driver, dTWidgetPage.spinnerTag);
        }
        catch(TimeoutException | NoSuchElementException exception)
        {
            Assert.assertTrue("The Spinner did not appear after save the changes", false);
        }
        
        try
        {
            Utils.waitUntilIsDisappears(driver, dTWidgetPage.spinnerTag);
        }
        catch(TimeoutException exception)
        {
            Assert.assertTrue("The Spinner did not disappear after save the changes", false);
        }
        
        //Verify the success message
        Assert.assertEquals("Success message content not valid",
                editSuccessMessage, dTWidgetPage.getAlertMessageContent());
        dTWidgetPage.getCloseAlertMessageButton().click();
        
        //Delete the Widget
        Assert.assertTrue("Unable to delete the widget", 
                deleteWidget(dTWidgetPage, code));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
