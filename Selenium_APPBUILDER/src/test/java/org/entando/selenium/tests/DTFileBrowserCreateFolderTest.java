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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTFileBrowserCreateFolderPage;
import org.entando.selenium.pages.DTFileBrowserPage;
import org.entando.selenium.testHelpers.FileBrowserTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Create a folder in File Browser page
 * 
 * @version 1.01
 */
public class DTFileBrowserCreateFolderTest extends FileBrowserTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFileBrowserPage dTFileBrowserPage;
    
    @Autowired
    public DTFileBrowserCreateFolderPage dTFileBrowserCreateFolderPage;
    
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
        
        //Click on public folder
        WebElement link = dTFileBrowserPage.getTable().getLinkOnTable(publicFolder, 0, 0);
        Assert.assertFalse("Can't find " + publicFolder + "in the table",
                link == null);
        link.click();
        
        //Wait loading page
        //Utils.waitUntilIsPresent(driver, dTFileBrowserPage.spinnerTag);
        //Utils.waitUntilIsDisappears(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        
        //Click on Create folder button
        dTFileBrowserPage.getCreateFolderButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserCreateFolderPage.getSaveButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Page title is incorrect", pageTitle, 
                Utils.trimInitialSpaces(dTFileBrowserPage.getPageTitle().getText()));        
        
        //Asserts the presence of the HELP button
        dTFileBrowserPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getTooltip());
        Assert.assertTrue(dTFileBrowserPage.getTooltip().isDisplayed());
        
        //Assert the save button is disabled
        Assert.assertFalse("Save Button is enabled but Folder Name field is empty",
                dTFileBrowserCreateFolderPage.getSaveButton().isEnabled());
        
        //Verify "field required" warning
        dTFileBrowserCreateFolderPage.getFolderName().click();
        dTFileBrowserCreateFolderPage.getHelp().click();
        Assert.assertTrue("Folder Name Field Error is not displayed", 
                dTFileBrowserCreateFolderPage.getFolderNameError().isDisplayed());
        
        //Set the fields
        dTFileBrowserCreateFolderPage.setFolderName(createFolderName);
        
        //Assert the save button is disabled
        Assert.assertTrue("Save Button is disabled but Folder Name field is compiled",
                dTFileBrowserCreateFolderPage.getSaveButton().isEnabled());
        
        //Save the data
        dTFileBrowserCreateFolderPage.getSaveButton().click();
        
        //Wait loading prev. page        
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getAlertMessage());
        
        Assert.assertTrue("Alert Message has not displayed",
                dTFileBrowserPage.getAlertMessage().isDisplayed());
        Assert.assertTrue("Invalid Alert Message content. Expected contains \"...successfully created\"",
                dTFileBrowserPage.getAlertMessageContent().contains("successfully created"));
        dTFileBrowserPage.getCloseMessageButton().click();
        
        sleep(500);
        
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getTableBody());
        sleep(1000);
        //Assert the presence of the created folder in the file browser table
        List<WebElement> createdFolder = dTFileBrowserPage.getTable()
                .findRowList(createFolderName, 0);
        Assert.assertFalse("Created folder is not present in the file browser table",
                createdFolder.isEmpty());
        
        //Delete the folder
        Assert.assertTrue("Folder has not been deleted",
                deleteFile(dTFileBrowserPage, createFolderName));
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/        
    }
}//end class
