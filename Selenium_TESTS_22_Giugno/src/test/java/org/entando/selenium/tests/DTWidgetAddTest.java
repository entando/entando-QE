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
import org.entando.selenium.pages.DTWidgetAddPage;
import org.entando.selenium.pages.DTWidgetPage;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.WidgetsTestBase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Add Widgets Page
 * 
 * @version 1.01
 */
public class DTWidgetAddTest extends WidgetsTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
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
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getNewButton());
        
        //Click on New Button
        dTWidgetPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTWidgetAddPage.getPageTitle());
                
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTWidgetAddPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTWidgetPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTWidgetAddPage.getTooltip());
        Assert.assertTrue(dTWidgetAddPage.getTooltip().isDisplayed());
        
        //Verify "field required" warning
        dTWidgetAddPage.getCodeField().click();
        dTWidgetAddPage.getEnTitleField().click();
        dTWidgetAddPage.getItTitleField().click();
        dTWidgetAddPage.getGroup().click();
        dTWidgetAddPage.getCodeField().click();
        
        Assert.assertTrue("Code Field Error is not displayed", 
                dTWidgetAddPage.getCodeFieldError().isDisplayed());
        Assert.assertTrue("EnTitle Field Error is not displayed", 
                dTWidgetAddPage.getEnTitleFieldError().isDisplayed());
        Assert.assertTrue("ItTitle Field Error is not displayed", 
                dTWidgetAddPage.getItTitleFieldError().isDisplayed());
        Assert.assertTrue("Group Select Error is not displayed", 
                dTWidgetAddPage.getGroupSelectError().isDisplayed());
        
        Assert.assertFalse("Save Button is enabled", 
                dTWidgetAddPage.getSaveButton().isEnabled());
        
        
        //Compilation of the fields
        dTWidgetAddPage.setCodeField(codeAlreadyPresent);
        dTWidgetAddPage.setEnTitleField(codeAlreadyPresent);
        dTWidgetAddPage.setItTitleField(codeAlreadyPresent);
        dTWidgetAddPage.getGroupSelect().selectByVisibleText(group);
        dTWidgetAddPage.setCustomUI(customUI);
        
        //Save the data
        dTWidgetAddPage.getSaveButton().click();
        
        //Verify Error Message
        Utils.waitUntilIsVisible(driver, dTWidgetAddPage.getAlertMessage());
        Assert.assertTrue("The error message not contains the code", 
                dTWidgetAddPage.getAlertMessageContent().contains(codeAlreadyPresent.toLowerCase()));
        dTWidgetAddPage.getCloseAlertMessageButton().click();
        
        //Compilation of the fields
        dTWidgetAddPage.setCodeField(code);
        dTWidgetAddPage.setEnTitleField(enTitle);
        dTWidgetAddPage.setItTitleField(itTitle);
        dTWidgetAddPage.getGroupSelect().selectByVisibleText(group);
        dTWidgetAddPage.setCustomUI(customUI);
        
        //Save the data
        dTWidgetAddPage.getSaveButton().click();
                
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTWidgetPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTWidgetPage.spinnerTag);
        
        //Verify the success message
        Assert.assertEquals("Success message content not valid",
                addSuccessMessage, dTWidgetPage.getAlertMessageContent());
        
        //Delete the Widget
        Assert.assertTrue("Unable to delete the widget", deleteWidget(dTWidgetPage, code));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
