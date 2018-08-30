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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTPageAddPage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Search function on the Page Tree page
 * 
 * @version 1.01
 */
public class DTPageTreeSearchTest extends PageTreeTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
    
    @Autowired
    public DTPageAddPage dTPageAddPage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Page Designer";
        String secondLevelLink = "Page Tree";
        
        //Final page title
        String pageTitle = "Page Tree";
        
        //pages names to create
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String pageName1 = "SeleniumTest" + randomNumber;
        randomNumber = generator.nextInt(9999);
        String pageName2 = "SeleniumTest" + randomNumber;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
                
        //Adding a first page
        Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage, pageName1));
        
        //Adding a second page
        Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage, pageName2));
        
        //Adding a second page
        Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage, pageName2, pageName1));
        
        //Search the first page
        dTPageTreePage.setSearchField(pageName1);
        dTPageTreePage.getSearchButton().click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getClearResultButton());
        int tableSize = dTPageTreePage.getSimpleTable().tableSize();
        /*Debug Code*/ Logger.getGlobal().info(Integer.toString(tableSize));
        //Verification of the presence of an element
        Assert.assertTrue(tableSize == 1);
        dTPageTreePage.getClearResultButton().click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getSearchButton());
        
        //Search the second page
        dTPageTreePage.setSearchField(pageName2);
        dTPageTreePage.getSearchButton().click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getClearResultButton());
        tableSize = dTPageTreePage.getSimpleTable().tableSize();
        /*Debug Code*/ Logger.getGlobal().info(Integer.toString(tableSize));
        //Verification of the presence of an element
        Assert.assertTrue(tableSize == 2);
        dTPageTreePage.getClearResultButton().click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getSearchButton());
        
        //Search a random page
        randomNumber = generator.nextInt(99999999);
        dTPageTreePage.setSearchField(Integer.toString(randomNumber));
        dTPageTreePage.getSearchButton().click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getClearResultButton());
        Assert.assertTrue(dTPageTreePage.getNoPagesFoundAlert().isDisplayed());
        dTPageTreePage.getClearResultButton().click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getSearchButton());
        
        
        
        //Delete the pages
        Assert.assertTrue(deletePage(dTPageTreePage, pageName2)); 
               
        expandRows(dTPageTreePage, pageName1, super.headerTitles.get(0));
        
        Assert.assertTrue(deletePage(dTPageTreePage, pageName2));
        
        Assert.assertTrue(deletePage(dTPageTreePage, pageName1));
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
