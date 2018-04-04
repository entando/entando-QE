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
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author leobel
 */
public class DTGroupAddTest extends FunctionalTest {
    
    @Test
    public void test(){
        DTLoginPage dtLoginPage = new DTLoginPage(driver);
        dtLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
        dtDashboardPage.SelectSecondOrderLink("Configuration", "Groups");

        //Utils util = new Utils();
        DTGroupsPage dtGroupsPage = new DTGroupsPage(driver);
        dtGroupsPage.getAddButton().click();
        
        String pageTitle = "Add";
        String nameLabel = "Name";
        String codeLabel = "Code";
        
        DTGroupAddPage dtGroupAddPage = new DTGroupAddPage(driver);
        
        Assert.assertEquals(pageTitle, dtGroupAddPage.getPageTitle().getText());
        Assert.assertEquals(nameLabel, dtGroupAddPage.getNameLabel().getText());
        Assert.assertEquals(codeLabel, dtGroupAddPage.getCodeLabel().getText());
        Assert.assertTrue(dtGroupAddPage.getNameRequired().isDisplayed());
        Assert.assertTrue(dtGroupAddPage.getCodeRequired().isDisplayed());
        
        dtGroupAddPage.getInfoName().click();
        Assert.assertTrue(dtGroupAddPage.getTooltip().isDisplayed());
        
        dtGroupAddPage.getInfoCode().click();
        Assert.assertTrue(dtGroupAddPage.getTooltip().isDisplayed());
        
        dtGroupAddPage.setName("name");
        dtGroupAddPage.setCode("code");
        
        Assert.assertTrue(dtGroupAddPage.getSubmit().isEnabled());
        
    }
    
}
