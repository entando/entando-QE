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
import org.entando.selenium.pages.DTFileBrowserPage;
import org.entando.selenium.testHelpers.FileBrowserTestBase;
import org.entando.selenium.utils.Parallelized;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;


/**
 * This class perform a test of the Browser page
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTFileBrowserTest extends FileBrowserTestBase{
    
    public DTFileBrowserTest(String browserName, String platformName) {
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
        DTFileBrowserPage dTFileBrowserPage = new DTFileBrowserPage(driver);
                
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Configuration";
        String secondLevelLink = "File browser";
        
        //Final page title
        String pageTitle = "File browser";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Page title is incorrect", pageTitle, 
                Utils.trimInitialSpaces(dTFileBrowserPage.getPageTitle().getText()));        
        
        //Asserts the presence of the HELP button
        dTFileBrowserPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getTooltip());
        Assert.assertTrue(dTFileBrowserPage.getTooltip().isDisplayed());
        
        //Asserts the breadcrumb is corrected
        Assert.assertEquals("The breadcrumb displays incorrect number of crumbs",
                1, dTFileBrowserPage.getBreadcrumb().getNumberOfCrumbs());
        
        //Click on public folder
        WebElement link = dTFileBrowserPage.getTable().getLinkOnTable(publicFolder, 0, 0);
        Assert.assertFalse("Can't find " + publicFolder + "in the table",
                link == null);
        link.click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals("Header titles are incorrect",
                expectedHeaderTitles, dTFileBrowserPage.getTable().getHeaderTitlesList());
        
        //Asserts the breadcrumb is corrected
        Assert.assertEquals("The breadcrumb displays incorrect number of crumbs",
                2, dTFileBrowserPage.getBreadcrumb().getNumberOfCrumbs());
        
        //Browse to plugins folder
        link = dTFileBrowserPage.getTable().getLinkOnTable(browseFolder, 0, 0);
        Assert.assertFalse("Can't find " + browseFolder + "in the table",
                link == null);
        link.click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getCreateFolderButton());
        
        //Asserts the breadcrumb is corrected
        Assert.assertEquals("The breadcrumb displays incorrect number of crumbs",
                3, dTFileBrowserPage.getBreadcrumb().getNumberOfCrumbs());
        
        //Return back to previous folder
        dTFileBrowserPage.getUpButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getCreateFolderButton());
        
        //Asserts the breadcrumb is corrected
        Assert.assertEquals("The breadcrumb displays incorrect number of crumbs",
                2, dTFileBrowserPage.getBreadcrumb().getNumberOfCrumbs());
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}//end class
