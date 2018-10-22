/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.pages.DTUserManageAuthorityPage;
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

public class DTUserManageAuthorizationTest extends FunctionalTestBase {
    
    @Autowired
    public DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUsersPage dTUsersPage;
    
    @Autowired
    public DTUserManageAuthorityPage dTUserManageAuthorityPage;
    
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
        
        Utils.clickKebabActionOnList(kebab.getActionList(), "Manage autorization for: " + user);
        
        String authorityTitle = "Authorizations for admin";
        
        Assert.assertEquals(authorityTitle, dTUserManageAuthorityPage.getPageTitle().getText());
        assertTrue(dTUserManageAuthorityPage.getGroupLabel().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getRoleLabel().isDisplayed());
        Assert.assertEquals(2, dTUserManageAuthorityPage.getAuthorizationControls().size());
        assertTrue(dTUserManageAuthorityPage.getAddButton().isDisplayed());
        assertTrue(dTUserManageAuthorityPage.getSaveButton().isDisplayed());
        
        int authorizations = dTUserManageAuthorityPage.getAuthorizations();
        
        dTUserManageAuthorityPage.setGroupAndRole(1, 1);
        dTUserManageAuthorityPage.getAddButton().click();
        
        Assert.assertEquals(authorizations + 1, dTUserManageAuthorityPage.getAuthorizations());
    }
}
