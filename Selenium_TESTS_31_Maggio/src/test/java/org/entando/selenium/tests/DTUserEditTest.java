/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.pages.DTUserEditPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */

public class DTUserEditTest extends FunctionalTestBase {
    
    @Autowired
    public DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUsersPage dTUsersPage;
    
    @Autowired
    public DTUserEditPage dTUserEditPage;
    
    @Autowired
    public Utils util;
    
    @Test
    public void runTest(){
        dTLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        dTDashboardPage.SelectSecondOrderLink("User Management", "Users");
        
        String user = "admin";
        Utils.Kebab kebab = Utils.getKebabOnTable(dTUsersPage.getUsersTable(), "Username", user, "button");
        kebab.getClickable().click();
        
        Utils.waitUntilIsVisible(driver, kebab.getActionList());
        
        Utils.clickKebabActionOnList(kebab.getActionList(), "Edit");
        
        String pageTitle = "Edit";
        
        Assert.assertEquals(pageTitle, dTUserEditPage.getPageTitle().getText());
        Assert.assertTrue(dTUserEditPage.getSaveButton().isDisplayed());
    }
    
}
