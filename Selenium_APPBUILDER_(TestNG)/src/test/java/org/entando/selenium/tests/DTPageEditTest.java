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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTPageAddPage;
import org.entando.selenium.pages.DTPageEditPage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Edit Page page
 * 
 * @version 1.01
 */
public class DTPageEditTest extends PageTreeTestBase {
    /*
        Pages used on this test
     */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
    
    @Autowired
    public DTPageEditPage dTPageEditPage;
    
    @Autowired
    public DTPageAddPage dTPageAddPage;
    
    /*
        Test
     */
    @Test
    public void pageEdit() throws InterruptedException{
        /*
            Parameters
         */
        //Link menù buttons
        String firstLevelLink = "Page Designer";
        String secondLevelLink = "Page Tree";
        
        //Final page title
        String pageTitle = "Edit";
        
        //pageName to delete
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String pageName = "SeleniumTest" + randomNumber;
        
        //Where create a page for the test
        String branchName = "Home";
        
        //Kebab menù action
        String kebabAction = "Edit";
        
        
        /*
            Actions and asserts
         */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        //Create a page to edit
        Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage, pageName));
        
        
        Kebab kebab = dTPageTreePage.getTable().getKebabOnTable(pageName, headerTitles.get(0), headerTitles.get(3));
        //Assert the item has been found
        Assert.assertFalse(kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        
        Utils.waitUntilAttributeToBeNotEmpty(driver, dTPageEditPage.getEnTitleField(), "value");
        
        //Asserts the page title is the expected one
        Assert.assertEquals(pageTitle, dTPageEditPage.getPageTitle().getText());
        
        //Asserts the fields contains correct text
        Assert.assertEquals(pageName, dTPageEditPage.getEnTitleField().getAttribute("value"));
        Assert.assertEquals(pageName, dTPageEditPage.getItTitleField().getAttribute("value"));
        //Assert.assertEquals(pageName, dTPageEditPage.getEsTitleField().getAttribute("value"));
        Assert.assertTrue(dTPageEditPage.getCodeField().getAttribute("value").contains(pageName + "Code"));
        Assert.assertEquals(super.pageModelName, dTPageEditPage.getPageModel().getFirstSelectedOption().getText());
        
        
        //Asserts fields that needs to be disabled
        Assert.assertFalse(dTPageEditPage.getCodeField().isEnabled());
        Assert.assertFalse(dTPageEditPage.getOwnerGroup().isEnabled());
        
                
        //Asserts the switch buttons status
        Assert.assertTrue(dTPageEditPage.getDisplayedInMenuSwitch().isOn());
        Assert.assertFalse(dTPageEditPage.getSeoSwitch().isOn());
                
        
        //Verify default set of charset and mime type
        Assert.assertEquals(super.defaultCharset, dTPageAddPage.getCharset().getFirstSelectedOption().getText());
        Assert.assertEquals(super.defaultMimeType, dTPageAddPage.getMimeType().getFirstSelectedOption().getText());
        
        
        //Asserts that Save button is enabled
        Assert.assertTrue(dTPageEditPage.getSaveButton().isEnabled());
        
        //Save and come back to the tree page
        dTPageAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getAddButton());
        
        //Delete the created page
        deletePage(dTPageTreePage, pageName);
        
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
