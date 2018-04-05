/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import com.google.inject.Inject;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTGroupDeletePage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author leobel
 */
public class DTGroupDeleteTest extends FunctionalTest{
    
    @Inject
    DTLoginPage dtLoginPage;
    
    @Inject
    DTDashboardPage dTDashboardPage;
    
    @Inject
    DTGroupsPage dtGroupsPage;
    
    @Inject
    DTGroupDeletePage dtGroupDeletePage;
    
    @Inject
    Utils util;
     
    @Test
    public void test(){
        dtLoginPage.logIn("admin", "adminadmin");
        
        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        dTDashboardPage.SelectSecondOrderLink("Configuration", "Groups");
        
        Utils.Kebab kebab = util.getKebabOnTable(dtGroupsPage.getGroupsTable(), 1, "button");
        kebab.getClickable().click();
        
        util.waitUntilVisible(driver, kebab.getActionList());
        util.clickKebabActionOnList(kebab.getActionList(), "Delete");
        
        String title = "Delete";
        String pageTitle = util.getText(dtGroupDeletePage.getPageTitle());
        
        Assert.assertTrue(dtGroupDeletePage.getDeleteDialog().isDisplayed());
        Assert.assertEquals(title, pageTitle);
        Assert.assertTrue(dtGroupDeletePage.getCancel().isDisplayed());
        Assert.assertTrue(dtGroupDeletePage.getDelete().isDisplayed());
    }
}
