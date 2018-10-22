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
import org.entando.selenium.pages.DTDashboardPage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTWidgetPage;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.WidgetsTestBase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

 /**
 * This class perform a test of the Widgets Page
 * 
 * @version 1.01
 */
public class DTWidgetsListTest extends WidgetsTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTWidgetPage dTWidgetPage;
    
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
        String pageTitle = "Widget";
        
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
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTWidgetPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTWidgetPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getTooltip());
        Assert.assertTrue(dTWidgetPage.getTooltip().isDisplayed());
        
        //Asserts the presence of the BUTTON with displayed name as argument
        Assert.assertTrue(dTWidgetPage.getNewButton().getText().equals(button1));

        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(expectedHeaderTitles, dTWidgetPage.getTableHeader());
        
        
        /** Debug code **/
        dTWidgetPage.getTables().keySet().forEach((key) -> {
            Logger.getGlobal().info(key);
        });
        /** End Debug code**/
        
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
    }
}
