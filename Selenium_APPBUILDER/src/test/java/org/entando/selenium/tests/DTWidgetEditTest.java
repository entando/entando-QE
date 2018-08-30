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
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTWidgetEditPage;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTWidgetAddPage;
import org.entando.selenium.pages.DTWidgetPage;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Edit Widget Page
 * 
 * @version 1.01
 */
public class DTWidgetEditTest extends WidgetsTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTWidgetEditPage dTWidgetEditPage;
    
    @Autowired
    public DTWidgetPage dTWidgetPage;
    
    @Autowired
    public DTWidgetAddPage dTWidgetAddPage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
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
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTWidgetPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTWidgetPage.spinnerTag);
        
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
