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
import org.entando.selenium.pages.DTDataTypesAddPage;
import org.entando.selenium.pages.DTDataTypesPage;
import org.entando.selenium.utils.DataTypesTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Delete Data Type function
 * 
 * @version 1.01
 */
public class DTDataTypesDeleteTest extends DataTypesTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTDataTypesPage dTDataTypesPage;
    
    @Autowired
    public DTDataTypesAddPage dTDataTypesAddPage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
                Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Data";
        String secondLevelLink = "Data Types";        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getNewButton());
        
        //Create a Data Type
        Assert.assertTrue(addDataType(dTDataTypesPage, dTDataTypesAddPage));
                
        //Delete the Data Type
        Assert.assertTrue(deleteDataType(dTDataTypesPage, dataTypeCode));
        
        sleep(500);
        
        //Assert the element is not present in the table
        List<WebElement> createdFragment = dTDataTypesPage.getTable()
                .findRowList(dataTypeCode, expectedHeaderTitles.get(1));
        
        Assert.assertTrue("Data type has not been deleted",
                createdFragment.isEmpty());
                
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }   
}//end class
