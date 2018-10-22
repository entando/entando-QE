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
import org.entando.selenium.pages.DTPageAddPage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.openqa.selenium.WebElement;


/**
 * This class contains some utils methos for the Page Tree tests (Helpers)
 * 
 * @version 1.01
 */
public class PageTreeTestBase extends ExpandableTableTestTypology{
    //Expected table header titles
    public final List<String> headerTitles = Arrays.asList("Page tree", "Status", "Displayed in menu", "Actions");
    //Owner group name
    public final String ownerGroupName = "1SeleniumTest_DontTouch";
    //Owner group code
    public final String ownerGroupCode = "aaaa";
    //Join Group name
    public final String joinGroupName = "1SeleniumTest_DontTouch";
    //Page model name
    public final String pageModelName = "1SLNM_TEST_DONT_TOUCH";
    //Default Charset
    public final String defaultCharset = "utf-8";
    //Default MimeType
    public final String defaultMimeType = "text/html";

    /**
     * This is a helper method that creates a standard page to be used in these tests
     * @param dTPageTreePage Page tree to start
     * @param dTPageTreeAddPage Add page
     * @param pageName The name of the page to create
     * @param branchName The name of the branch in witch create the page
     * @return true if the page has been created successfully, else false 
     * @throws java.lang.InterruptedException 
     */
    public boolean addPage(DTPageTreePage dTPageTreePage, DTPageAddPage dTPageTreeAddPage, String pageName, String branchName) 
            throws InterruptedException
    {
        dTPageTreePage.getAddButton().click();
        Utils.waitUntilIsVisible(driver, dTPageTreeAddPage.getPageTitle());
        driver.get(driver.getCurrentUrl());
        Utils.waitUntilIsVisible(driver, dTPageTreeAddPage.getTableElement());
        
        //Compiling the fields
        dTPageTreeAddPage.setEnTitleField(pageName);
        dTPageTreeAddPage.setItTitleField(pageName);
        //dTPageTreeAddPage.setEsTitleField(pageName);
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        dTPageTreeAddPage.setCodeField(pageName + "Code" + randomNumber);
        dTPageTreeAddPage.getOwnerGroup().selectByVisibleText(ownerGroupName);
        dTPageTreeAddPage.getPageModel().selectByVisibleText(pageModelName);
        dTPageTreeAddPage.addJoinGroup(joinGroupName);
        
        //Select the branch
        ExpandableTable table = dTPageTreeAddPage.getTable();
        WebElement row = table.findRowList(branchName, headerTitles.get(0)).get(0);
        row.click();
        
        sleep(100);
        
        //Return back
        Assert.assertTrue("Save Button is not enabled after compiling",
                 dTPageTreeAddPage.getSaveButton().isEnabled());
        dTPageTreeAddPage.getSaveButton().click();
        //sleep(2000);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getAddButton());
        //driver.get(driver.getCurrentUrl());
        //Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        List<WebElement> createdPage = dTPageTreePage.getTable().findRowList(pageName, headerTitles.get(0));
        if(createdPage.isEmpty())
            return false;
        else
            return true;
    }
    
    
    
    /**
     * This is a helper method that creates a standard page to be used in these tests
     * @param dTPageTreePage Page tree to start
     * @param dTPageTreeAddPage Add page
     * @param pageName The name of the page to create
     * @return true if the page has been created successfully, else false 
     * @throws java.lang.InterruptedException 
     */
    public boolean addPage(DTPageTreePage dTPageTreePage, DTPageAddPage dTPageTreeAddPage, String pageName) 
            throws InterruptedException
    {
        return (this.addPage(dTPageTreePage, dTPageTreeAddPage, pageName, "Home"));
    }
    
    
    
    /**
     * This is a helper method that delete a page to be used in these tests
     * @param dTPageTreePage
     * @param pageNameToDelete
     * @return  true if the page has been deleted, else false 
     * @throws java.lang.InterruptedException 
     */
    public boolean deletePage(DTPageTreePage dTPageTreePage, String pageNameToDelete) throws InterruptedException{
        //more pages can be found with the same name
        List<Kebab> foundedKebabs = dTPageTreePage.getTable().getKebabsOnTable(pageNameToDelete, headerTitles.get(0), headerTitles.get(3));
        if(foundedKebabs.isEmpty())
        {
            /** Debug code **/ Logger.getGlobal().info("delete page return false");
            return false;
        }
        //for each page found, click on the delete action
        for(Kebab kebab : foundedKebabs){
            //Click on kebab menù
            kebab.getClickable().click();
            /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
            Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
            //Click on the action
            kebab.getAction("Delete").click();
            /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
            Utils.waitUntilIsVisible(driver, dTPageTreePage.getDeleteModalButton());
            /** Debug code **/ Logger.getGlobal().info(dTPageTreePage.getModalBody().getText());
            /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", pageNameToDelete));
            Assert.assertTrue(dTPageTreePage.getModalBody().getText().contains(pageNameToDelete));
            Utils.waitUntilIsClickable(driver, dTPageTreePage.getDeleteModalButton());
            sleep(100);
            dTPageTreePage.getDeleteModalButton().click();
            Utils.waitUntilIsDisappears(driver, DTPageTreePage.modalWindowTag);
            Utils.waitUntilIsDisappears(driver, kebab.getClickable());
        }
        
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        /** Debug code **/ Logger.getGlobal().info("delete page return true");
        return true;
    }
    
    
    
}
