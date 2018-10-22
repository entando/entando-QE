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
    public final List<String> expectedHeaderTitles = Arrays.asList("","","",
            "","","","up ..", "Size", "Last edit", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);
    
    //Default first part of the folder name
    public final String folderPrefixName = "Folder\n";
    
    //Default first part of the folder name
    public final String filePrefixName = "File\n";
    
    //Default public folder name
    public final String publicFolder = folderPrefixName + "public";
    
    //Default protected folder name
    public final String protectedFolder = folderPrefixName + "protected";
    
    //Default folder to browse name
    public final String browseFolder = folderPrefixName + "plugins";
    
    //Default create folder name
    public final String createFolderName = "SeleniumFolderDontTouch";
    
    //Default create file name
    public final String createFileName = "SeleniumTextFileDontTouch";
    
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
     */
    public boolean createFolder (DTFileBrowserPage dTFileBrowserPage,
            DTFileBrowserCreateFolderPage dTFileBrowserCreateFolderPage, 
            String folderName){
        dTFileBrowserPage.getCreateFolderButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserCreateFolderPage.getSaveButton());
        
        //Set the fields
        dTFileBrowserCreateFolderPage.setFolderName(createFolderName);
        
        //Save the data
        dTFileBrowserCreateFolderPage.getSaveButton().click();
        
        //Wait loading prev. page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getTableBody());
        
        //Assert the presence of the created page in the "page tree" table
        List<WebElement> createdFolder = dTFileBrowserPage.getTable()
                .findRowList(folderPrefixName + createFolderName, 1);
        
        return(!createdFolder.isEmpty());
    }
    
    
    
    /**
     * 
     * @param dTFileBrowserPage
     * @param fileName
     * @return 
     */
    public boolean deleteFile (DTFileBrowserPage dTFileBrowserPage, String fileName){
        Kebab kebab = dTFileBrowserPage.getTable().getKebabOnTable(
                fileName, 1, 4);
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
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getDeleteButton());
        
        dTFileBrowserPage.getDeleteButton().click();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFileBrowserPage.getUploadButton());
        
        return true;
    }
}
