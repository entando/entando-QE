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
import org.entando.selenium.pages.DTDatabasePage;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.Parallelized;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * This class perform a test of the Reload Configuration Page
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTDatabaseTest extends FunctionalTestBase{
    
    public DTDatabaseTest(String browserName, String platformName) {
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
        DTDatabasePage dTDatabasePage = new DTDatabasePage(driver);
        
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Configuration";
        String secondLevelLink = "Database";
        
        //Final page title
        String pageTitle = "Database";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTDatabasePage.getPageTitle().getText());        
        
        //Asserts the presence of the HELP button
        dTDatabasePage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTDatabasePage.getTooltip());
        Assert.assertTrue(dTDatabasePage.getTooltip().isDisplayed());
        
        //Verify the button is enabled
        Assert.assertTrue("Backup button is disabled",
                dTDatabasePage.getBackupButton().isEnabled());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
