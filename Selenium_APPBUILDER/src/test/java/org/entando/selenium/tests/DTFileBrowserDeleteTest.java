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
import org.entando.selenium.utils.FileBrowserTestBase;
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
        String firstLevelLink = "Settings";
        String secondLevelLink = "File Browser";
        
        //Final page title
        String pageTitle = "Delete";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        
        //Click on public folder
        dTFileBrowserPage.getTable().getLinkOnTable(publicFolder, 1, 1).click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        
        //Create a folder
        createFolder(dTFileBrowserPage, dTFileBrowserCreateFolderPage, createFolderName);
        
        //Find Kebab on file list
        Kebab kebab = dTFileBrowserPage.getTable().getKebabOnTable(
                folderPrefixName + createFolderName, 1, 4);
        Assert.assertFalse("Created Folder not found!", kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getDeleteButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Page title is incorrect", pageTitle, 
                Utils.trimInitialSpaces(dTFileBrowserPage.getPageTitle().getText()));        
        
        Assert.assertTrue("Delete message not contains the Folder name",
                dTFileBrowserPage.getDeleteMessage().getText().contains(createFolderName));
        
        Assert.assertEquals("Page Breadcrumb is not displayed correctly", 
                3, dTFileBrowserPage.getPageBreadcrumb().getNumberOfCrumbs());
        
        Assert.assertEquals("The last item of the Page Breadcrumb is not corrected",
                pageTitle, dTFileBrowserPage.getPageBreadcrumb().getLastCrumb());
        
        dTFileBrowserPage.getDeleteButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getTableBody());
        
        //Assert the absence of the created folder in the file browser table
        List<WebElement> createdFolder = dTFileBrowserPage.getTable()
                .findRowList(folderPrefixName + createFolderName, 1);
        Assert.assertTrue("Created folder is still present in the file browser table",
                createdFolder.isEmpty());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/  
    }
}
