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
import org.entando.selenium.pages.DTFileBrowserPage;
import org.entando.selenium.pages.DTFileBrowserUploadPage;
import org.entando.selenium.testHelpers.FileBrowserTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Upload a file in File Browser page
 * 
 * @version 1.01
 */
public class DTFileBrowserUploadFileTest extends FileBrowserTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFileBrowserPage dTFileBrowserPage;
    
    @Autowired
    public DTFileBrowserUploadPage dTFileBrowserUploadPage;
    
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
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        
        //Click on public folder
        dTFileBrowserPage.getTable().getLinkOnTable(publicFolder, 0, 0).click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFileBrowserPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        
        //Click on Upload file button
        dTFileBrowserPage.getUploadButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserUploadPage.getSaveButton());
        
        //Assert the save button is disabled
        Assert.assertFalse("Save Button is enabled but Fields are empty",
                dTFileBrowserUploadPage.getSaveButton().isEnabled());
        
        //Set the fields
        dTFileBrowserUploadPage.setFilePath(fileToUploadPath + fileToUploadName);
        
        //Assert the save button is enabled
        Assert.assertTrue("Save Button is disabled but Folder Name field is compiled",
                dTFileBrowserUploadPage.getSaveButton().isEnabled());
        
        //Save the data
        dTFileBrowserUploadPage.getSaveButton().click();
        
        //Wait loading prev. page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getAlertMessage());
        
        Assert.assertTrue("Alert Message has not displayed",
                dTFileBrowserPage.getAlertMessage().isDisplayed());
        Assert.assertTrue("Invalid Alert Message content. Expected contains \"...complete\"",
                dTFileBrowserPage.getAlertMessageContent().contains("complete"));
        dTFileBrowserPage.getCloseMessageButton().click();
        
        sleep(500);
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getTableBody());
        
        //Assert the presence of the uploaded file in the file browser table
        List<WebElement> createdFolder = dTFileBrowserPage.getTable()
                .findRowList(fileToUploadName, 0);
        Assert.assertFalse("Uploaded file is not present in the file browser table",
                createdFolder.isEmpty());
        
        //Delete the file
        Assert.assertTrue("File has not been deleted",
                deleteFile(dTFileBrowserPage, fileToUploadName));
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/ 
        
    }
}
