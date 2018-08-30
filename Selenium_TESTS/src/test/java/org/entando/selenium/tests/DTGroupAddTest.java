/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTGroupAddPage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author leobel
 */
public class DTGroupAddTest extends FunctionalTest {
    
    @Autowired
    DTLoginPage dtLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTGroupsPage dTGroupsPage;
    
    @Autowired
    public DTGroupAddPage dTGroupAddPage;
    
    @Test
    public void test(){
        dtLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        dTDashboardPage.SelectSecondOrderLink("User Management", "Groups");

        dTGroupsPage.getAddButton().click();
        
        String pageTitle = "Add";
        String nameLabel = "Name";
        String codeLabel = "Code";
        
        Assert.assertEquals(pageTitle, dTGroupAddPage.getPageTitle().getText());
        Assert.assertEquals(nameLabel, dTGroupAddPage.getNameLabel().getText());
        Assert.assertEquals(codeLabel, dTGroupAddPage.getCodeLabel().getText());
        Assert.assertTrue(dTGroupAddPage.getNameRequired().isDisplayed());
        Assert.assertTrue(dTGroupAddPage.getCodeRequired().isDisplayed());
        
        dTGroupAddPage.getInfoName().click();
        Assert.assertTrue(dTGroupAddPage.getTooltip().isDisplayed());
        
        dTGroupAddPage.getInfoCode().click();
        Assert.assertTrue(dTGroupAddPage.getTooltip().isDisplayed());
        
        dTGroupAddPage.setName("name");
        dTGroupAddPage.setCode("code");
        
        Assert.assertTrue(dTGroupAddPage.getSubmit().isEnabled());
        
    }
    
}
