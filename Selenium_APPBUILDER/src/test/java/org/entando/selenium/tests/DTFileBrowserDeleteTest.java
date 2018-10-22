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
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Delete a file/folder in File Browser page
 * 
 * @version 1.01
 */
public class DTFileBrowserDeleteTest extends FileBrowserTestBase{
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
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        
        //Create a folder
        Assert.assertTrue("Unable to create folder",
                createFolder(dTFileBrowserPage, dTFileBrowserCreateFolderPage, createFolderName));
        
        //Find Kebab on file list
        Kebab kebab = dTFileBrowserPage.getTable().getKebabOnTable(
                createFolderName, 0, 3);
        Assert.assertFalse("Created Folder not found!", kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getDeleteModalButton());
        Assert.assertTrue("Delete confirm message not contains the folder name",
                dTFileBrowserPage.getModalBody().getText().contains(createFolderName));
        Utils.waitUntilIsClickable(driver, dTFileBrowserPage.getDeleteModalButton());
        sleep(100);
        dTFileBrowserPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTFileBrowserPage.modalWindowTag);
        Utils.waitUntilIsDisappears(driver, kebab.getClickable());
        
        //Verify the alert message
        Assert.assertTrue("Alert Message has not displayed",
                dTFileBrowserPage.getAlertMessage().isDisplayed());
        Assert.assertTrue("Invalid Alert Message content. Expected contains \"...successfully deleted\"",
                dTFileBrowserPage.getAlertMessageContent().contains("successfully deleted"));
        dTFileBrowserPage.getCloseMessageButton().click();
        
        
        //Assert the absence of the created folder in the file browser table
        List<WebElement> createdFolder = dTFileBrowserPage.getTable()
                .findRowList(createFolderName, 0);
        Assert.assertTrue("Created folder is still present in the file browser table",
                createdFolder.isEmpty());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/  
    }
}
