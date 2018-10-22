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
import org.entando.selenium.pages.DTUserRestrictionsPage;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

 /**
 * This class perform a test of the User Restrictions Page
 * 
 * @version 1.01
 */
public class DTUserRestrictionsTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired DTUserRestrictionsPage dTUserRestrictionsPage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "User Restrictions";
        
        //Final page title
        String pageTitle = "User Restrictions";
        
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTUserRestrictionsPage.getSaveButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserRestrictionsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTUserRestrictionsPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserRestrictionsPage.getTooltip());
        Assert.assertTrue(dTUserRestrictionsPage.getTooltip().isDisplayed());
        
        sleep(800);
        
        //Assert the switch buttons status
        Assert.assertTrue(dTUserRestrictionsPage.getPasswordAlwaysActiveSwitchButton().isOn());
        Assert.assertTrue(dTUserRestrictionsPage.getEnableGravatarIntegrationSwitchButton().isOn());
        
        //Assert the field are disabled
        Assert.assertFalse(dTUserRestrictionsPage.getMaxMonthsPasswordValid().isEnabled());
        Assert.assertFalse(dTUserRestrictionsPage.getLastAccessPasswordExpirationMonths().isEnabled());
        
        //Assert the content of the fields
        Assert.assertEquals("0", dTUserRestrictionsPage.getMaxMonthsPasswordValid().getAttribute("value"));
        Assert.assertEquals("0", dTUserRestrictionsPage.getLastAccessPasswordExpirationMonths().getAttribute("value"));
        
        //Assert the save button is enabled
        Assert.assertTrue(dTUserRestrictionsPage.getSaveButton().isEnabled());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/ 
        
    }//end class
}
