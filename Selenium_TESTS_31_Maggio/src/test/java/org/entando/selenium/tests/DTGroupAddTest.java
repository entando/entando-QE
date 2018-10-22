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

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTGroupAddPage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Data Type page
 * 
 * @version 1.01
 */

public class DTGroupAddTest extends FunctionalTestBase {
    /*
        Pages used on this test
     */
    @Autowired
    DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTGroupsPage dTGroupsPage;
    
    @Autowired
    public DTGroupAddPage dTGroupAddPage;

    /*
        Test
     */
    @Test
    public void test(){
        /*
            Parameters
         */
        //Login credentials
        String username = "admin";
        String password = "adminadmin";
        
        //Link menù buttons
        String firstLevelLink = "User Management";
        String secondLevelLink = "Groups";
        
        //Final page title
        String pageTitle = "Fragments";
        
        //Field label names
        String nameLabel = "Name";
        String codeLabel = "Code";
                
        /*
            Actions and asserts
         */
        //Login
        dTLoginPage.logIn(username, password);
        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        dTGroupsPage.getAddButton().click();
        
        //Asserts field label names are corrected
        Assert.assertEquals(pageTitle, dTGroupAddPage.getPageTitle().getText());
        Assert.assertEquals(nameLabel, dTGroupAddPage.getNameLabel().getText());
        Assert.assertEquals(codeLabel, dTGroupAddPage.getCodeLabel().getText());
        
        //Asserts fields required
        Assert.assertTrue(dTGroupAddPage.getNameRequired().isDisplayed());
        Assert.assertTrue(dTGroupAddPage.getCodeRequired().isDisplayed());
        
        //Asserts fields functionality info
        dTGroupAddPage.getInfoName().click();
        Assert.assertTrue(dTGroupAddPage.getTooltip().isDisplayed());
        
        dTGroupAddPage.getInfoCode().click();
        Assert.assertTrue(dTGroupAddPage.getTooltip().isDisplayed());
        
        //Filling in the fields
        dTGroupAddPage.setName("name");
        dTGroupAddPage.setCode("code");
        
        //Asserts submit button is enabled
        Assert.assertTrue(dTGroupAddPage.getSubmit().isEnabled());
    }
    
}
