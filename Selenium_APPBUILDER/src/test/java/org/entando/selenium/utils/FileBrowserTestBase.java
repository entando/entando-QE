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
package org.entando.selenium.utils;

import static java.lang.Thread.sleep;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTFileBrowserCreateFolderPage;
import org.entando.selenium.pages.DTFileBrowserPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * This class contains some utils methos for the File Browser tests (Helpers)
 * 
 * @version 1.01
 */
public class FileBrowserTestBase extends FunctionalTestBase{
    
    //Expected table header titles
    public final List<String> expectedHeaderTitles = Arrays.asList("up..", "Size", "Last Edit", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);
        
    //Default public folder name
    public final String publicFolder = "public";
    
    //Default protected folder name
    public final String protectedFolder = "protected";
    
    //Default folder to browse name
    public final String browseFolder = "plugins";
    
    //Default create folder name
    public final String createFolderName = "SeleniumFolderDontTouch" + randomNumber;
    
    //Default create file name
    public final String createFileName = "SeleniumTextFileDontTouch" + randomNumber;
    
    //Default content file
    public final String contentFile = 
            "This is a Selenium Test!!! Ne pas toucher, Merçi!";
    
    //Default upload file name
    public final String fileToUploadName = "seleniumTest-File.txt";
     
    //Default upload file path
    public final String fileToUploadPath = "/home/ufficio/";
    
    
    
    
    /**
     * 
     * @param dTFileBrowserPage
     * @param dTFileBrowserCreateFolderPage
     * @param folderName
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean createFolder (DTFileBrowserPage dTFileBrowserPage,
            DTFileBrowserCreateFolderPage dTFileBrowserCreateFolderPage, 
            String folderName) throws InterruptedException{
        dTFileBrowserPage.getCreateFolderButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserCreateFolderPage.getSaveButton());
        
        //Set the fields
        dTFileBrowserCreateFolderPage.setFolderName(createFolderName);
        
        //Save the data
        dTFileBrowserCreateFolderPage.getSaveButton().click();
        
        
        //Wait loading prev. page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getAlertMessage());
        dTFileBrowserPage.getCloseMessageButton().click();
        
        sleep(500);
        
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getTableBody());
        
        //Assert the presence of the created page in the "page tree" table
        List<WebElement> createdFolder = dTFileBrowserPage.getTable()
                .findRowList(createFolderName, 0);
        
        return(!createdFolder.isEmpty());
    }
    
    
    
    /**
     * 
     * @param dTFileBrowserPage
     * @param fileName
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteFile (DTFileBrowserPage dTFileBrowserPage, String fileName) throws InterruptedException{
        Kebab kebab = dTFileBrowserPage.getTable().getKebabOnTable(
                fileName, 0, 3);
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("File not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTFileBrowserPage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", fileName));
        Assert.assertTrue("Delete confirm message not contains the file name",
                dTFileBrowserPage.getModalBody().getText().contains(fileName));
        Utils.waitUntilIsClickable(driver, dTFileBrowserPage.getDeleteModalButton());
        sleep(100);
        dTFileBrowserPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTFileBrowserPage.modalWindowTag);
        Utils.waitUntilIsDisappears(driver, kebab.getClickable());
        
        //Verify the alert message
        Assert.assertTrue("Alert Message has not displayed",
                dTFileBrowserPage.getAlertMessage().isDisplayed());
        Assert.assertTrue("Invalid Alert Message content. Expected contains \"...successfully created\"",
                dTFileBrowserPage.getAlertMessageContent().contains("successfully deleted"));
        dTFileBrowserPage.getCloseMessageButton().click();
        
        /** Debug code **/ Logger.getGlobal().info("delete folder return true");
        return true;
    }
}
