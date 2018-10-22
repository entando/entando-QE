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
import org.entando.selenium.pages.DTLabelsAndLanguagesPage;
import org.entando.selenium.pages.DTSystemLabelsPage;
import org.entando.selenium.utils.SystemLabelsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the System Labels page
 * 
 * @version 1.01
 */
public class DTSystemLabelsListTest extends SystemLabelsTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTLabelsAndLanguagesPage dTLabelsAndLanguagesPage;
    
    @Autowired
    public DTSystemLabelsPage dTSystemLabelsPage;
    
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
        String pageTitle = "Labels and Languages";
               
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        
        //Loading page
        Utils.waitUntilIsPresent(driver, dTLabelsAndLanguagesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTLabelsAndLanguagesPage.spinnerTag);
        
        dTLabelsAndLanguagesPage.getSystemLabelsButton().click();
        
         //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(expectedHeaderTitles, dTSystemLabelsPage.getTable().getHeaderTitlesList());
        
        //Assert the functionality of BROWSABLE TABLE
        Assert.assertTrue(checkBrowsableTable(dTSystemLabelsPage));
        
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
