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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTPageAddPage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test to publish/unpublish a page in the page tree
 * 
 * @version 1.01
 */
public class DTPagePublishTest extends PageTreeTestBase{
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
        
        //Expected table header titles
        List<String> expectedHeaderTitles = Arrays.asList("Page tree", "Status", "Displayed in menu", "Actions");
        
        //pageName to delete
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String pageName = "SeleniumTest" + randomNumber;
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        
        //Create a page to publish/unpublish
        Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage, pageName));
        
        
        Kebab kebab = dTPageTreePage.getTable().getKebabOnTable(pageName, expectedHeaderTitles.get(0), expectedHeaderTitles.get(3));
        //Assert the presence of kebab
        Assert.assertFalse(kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Publish").click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getPublishModalButton());
        //Assert the presence of the page name in the message
        Assert.assertTrue(dTPageTreePage.getModalBody().getText().contains(pageName));
        Utils.waitUntilIsClickable(driver, dTPageTreePage.getPublishModalButton());
        sleep(100);
        dTPageTreePage.getPublishModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTPageTreePage.getModalWindowTag());
        
        WebElement cell = dTPageTreePage.getTable().getCell(pageName, expectedHeaderTitles.get(0), expectedHeaderTitles.get(1));
        //Assert the presence of the element
        Assert.assertFalse(cell == null);
        WebElement icon = cell.findElement(DTPageTreePage.statusIconTag);
        
        
        
        //Assert the status icon is green
        Assert.assertTrue(icon.getAttribute("title").equals("Online"));
        
        
        
        kebab = dTPageTreePage.getTable().getKebabOnTable(pageName, expectedHeaderTitles.get(0), expectedHeaderTitles.get(3));
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Assert the Delete function is disabled
        Assert.assertFalse(kebab.getActionsStringList().contains("Delete"));
        
        
        
        //Click on the action
        kebab.getAction("Unpublish").click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getUnpublishModalButton());
        //Assert the presence of the page name in the message
        Assert.assertTrue(dTPageTreePage.getModalBody().getText().contains(pageName));
        Utils.waitUntilIsClickable(driver, dTPageTreePage.getUnpublishModalButton());
        sleep(100);
        dTPageTreePage.getUnpublishModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTPageTreePage.getModalWindowTag());
        
        cell = dTPageTreePage.getTable().getCell(pageName, expectedHeaderTitles.get(0), expectedHeaderTitles.get(1));
        //Assert the presence of the element        
        Assert.assertFalse(cell == null);
        icon = cell.findElement(DTPageTreePage.statusIconTag);
        
        //Assert the status icon is grey
        Assert.assertTrue(icon.getAttribute("title").equals("Draft"));
        
        //Delete the page
        deletePage(dTPageTreePage, pageName);
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
