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
import org.entando.selenium.utils.FileBrowserTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Browser page
 * 
 * @version 1.01
 */
public class DTFileBrowserTest extends FileBrowserTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFileBrowserPage dTFileBrowserPage;
    
    /*
        Test
    */  
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Settings";
        String secondLevelLink = "File Browser";
        
        //Final page title
        String pageTitle = "File Browser";
        
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
        dTFileBrowserPage.getTable().getLinkOnTable(publicFolder, 1, 1).click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals("Header titles are incorrect",
                expectedHeaderTitles, dTFileBrowserPage.getTable().getHeaderTitlesList());
        
        //Asserts the breadcrumb is corrected
        Assert.assertEquals("The breadcrumb displays incorrect number of crumbs",
                2, dTFileBrowserPage.getBreadcrumb().getNumberOfCrumbs());
        
        //Browse to plugins folder
        dTFileBrowserPage.getTable().getLinkOnTable(browseFolder, 1, 1).click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getCreateFolderButton());
        
        //Asserts the breadcrumb is corrected
        Assert.assertEquals("The breadcrumb displays incorrect number of crumbs",
                3, dTFileBrowserPage.getBreadcrumb().getNumberOfCrumbs());
        
        //Return back to previous folder
        dTFileBrowserPage.getUpButton().click();
        
        //Wait loading page
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
