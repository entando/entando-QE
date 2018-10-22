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
import org.entando.selenium.pages.DTPageModelsAddPage;
import org.entando.selenium.pages.DTPageModelsPage;
import org.entando.selenium.utils.PageModelsTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Delete Page Models function
 * 
 * @version 1.01
 */
public class DTPageModelsDeleteTest extends PageModelsTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageModelsPage dTPageModels;
        
    @Autowired
    public DTPageModelsAddPage dTPageModelsAddPage;

    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException{
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Page Models";
        
        //Final pages titles
        String pageTitle = "Page Models";
        
        //Kebab column
        String kebabColumn = "Actions";
                
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageModels.getAddButton());
        
        //Create a Fragment
        Assert.assertTrue(addPageModel(dTPageModels, dTPageModelsAddPage));
                
        //Delete the role
        Assert.assertTrue(deletePageModel(dTPageModels, code));
        
        //Reload the page
        driver.get(driver.getCurrentUrl());
        
        //Assert the element is not present in the table
        List<WebElement> createdFragment = dTPageModels.getTable()
                .findRowList(code, expectedHeaderTitles.get(0));
        
        Assert.assertTrue(createdFragment.isEmpty());
                
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
