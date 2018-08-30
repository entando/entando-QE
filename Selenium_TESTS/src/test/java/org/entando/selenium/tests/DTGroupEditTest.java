/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTGroupEditPage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.Utils.Kebab;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */
public class DTGroupEditTest extends FunctionalTest {
    
    @Autowired
    DTLoginPage dTLoginPage;
    
    @Autowired
    DTDashboardPage dTDashboardPage;
    
    @Autowired
    DTGroupsPage dtGroupsPage;
    
    @Autowired
    DTGroupEditPage dtGroupEditPage;
    
    @Autowired
    Utils util;
    
    @Test
    public void test(){
        dTLoginPage.logIn("admin", "adminadmin");
        
        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        dTDashboardPage.SelectSecondOrderLink("User Management", "Groups");
        
        Kebab kebab = util.getKebabOnTable(dtGroupsPage.getGroupsTable(), "Code", "administrators", "button");
        kebab.getClickable().click();
        
        util.waitUntilIsVisible(driver, kebab.getActionList());
        util.clickKebabActionOnList(kebab.getActionList(), "Edit");
        
        String pageTitle = "Edit";
        String nameLabel = "Name";
        String codeLabel = "Code";
        String name = "Administrators";
        String code = "administrators";
        String pageName = util.getValue(driver, dtGroupEditPage.getName());
        String pageCode = util.getValue(driver, dtGroupEditPage.getCode());
        
        Assert.assertEquals(pageTitle, dtGroupEditPage.getPageTitle().getText());
        Assert.assertEquals(nameLabel, dtGroupEditPage.getNameLabel().getText());
        Assert.assertEquals(codeLabel, dtGroupEditPage.getCodeLabel().getText());
        
        Assert.assertTrue(dtGroupEditPage.getNameRequired().isDisplayed());
        Assert.assertTrue(dtGroupEditPage.getCodeRequired().isDisplayed());
        
        Assert.assertTrue(dtGroupEditPage.getInfoName().isDisplayed());
        Assert.assertTrue(dtGroupEditPage.getInfoCode().isDisplayed());
        
        Assert.assertEquals(pageName, name);
        Assert.assertEquals(pageCode, code);
        
        Assert.assertTrue(dtGroupEditPage.getSubmit().isEnabled());
    }
    
}
